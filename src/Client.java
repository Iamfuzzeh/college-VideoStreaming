import SD2.PortalPrx;
import SD2.PortalPrxHelper;
import SD2.StreamInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 * Created by iamfuzzeh on 12/30/16.
 */
public class Client {

    static String LOCALHOST = "localhost";
    static int OUTPORT = 10020;
    static Socket insocket = null;
    static Socket outsocket = null;
    static ServerSocket serversocket;
    static DataOutputStream out;
    private static Ice.Communicator ic = null;
    private static PortalPrx portal = null;

    public static void main(String args[]) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                shutdownHook();
            }
        }, "Shutdown-thread"));

        if(args.length>0){
            OUTPORT =  Integer.parseInt(args[0]);
        }

        initializePortalConnection();
        runCLI();

    }


    private static void shutdownHook() {
        System.out.println("In shutdown hook");
        if (ic != null) {
            try {
                ic.destroy();
            } catch (Exception e) {
                System.out.println("error with ICE shutdown");
            }
        }
    }


    private static void initializePortalConnection() {
        try {
            ic = Ice.Util.initialize();
            Ice.ObjectPrx base = ic.stringToProxy("Portal:default -p 10000");
            portal = PortalPrxHelper.checkedCast(base);
            if (portal == null) {
                throw new Error("Invalid proxy");
            }
        } catch (Exception e) {
            System.out.println("error with ICE initialization");
        }
    }


    private static void printStreamerList(StreamInfo[] sl){
        for (StreamInfo streaminfo : sl){
            System.out.println(streaminfo.name +" at " + streaminfo.endp.ip + ":" + streaminfo.endp.port);
        }
    }


    private static void getStreamAndStream(int inport) {
        try {
            insocket = new Socket(LOCALHOST, inport);
            BufferedInputStream bis = new BufferedInputStream(insocket.getInputStream());
            int bytes;
            out = new DataOutputStream(outsocket.getOutputStream());
            while ((bytes = bis.read()) != -1) {
                try {
                    out.write(bytes);
                    out.flush();
                } catch (IOException e) {
                    System.out.println("error: " + e.getMessage());
                    return;
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    private static void acceptConnection() throws IOException {
        while (outsocket == null) {
            try {
                outsocket = serversocket.accept();
                out = new DataOutputStream(outsocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static int createLocalServer() {
        try {
            serversocket = new ServerSocket(OUTPORT);
        } catch (IOException e) {
            System.out.println("error creating localsocket, trying another");
            OUTPORT++;
            createLocalServer();
        }
        return serversocket.getLocalPort();
    }

    private static void runCLI() {

        String line;
        String cmd[];
        Scanner in = new Scanner(System.in);

        System.out.println("");

        do{
            cmd = parse(in.nextLine());

            if(cmd.length > 1 ){
                if(cmd.length == 2 && cmd[0].equalsIgnoreCase("stream") && cmd[1].equalsIgnoreCase("list")){
                    StreamInfo[] si = portal.getStreamList();
                    if(si.length == 0){
                        System.out.println("There are currently no streamers active.");
                    }else{
                        printStreamerList(si);
                    }
                }
                if(cmd.length > 2){
                    if(cmd[1].equalsIgnoreCase("search")){
                        displaySortedStreams(portal.getStreamList(), cmd);
                    }
                    else if(cmd[1].equalsIgnoreCase("play")){
                        searchForAndPlayStream(cmd);
                    }
                }
            }
        }while(!cmd[0].equalsIgnoreCase("quit") || !cmd[0].equalsIgnoreCase("exit"));
    }

    private static void displaySortedStreams(StreamInfo[] streamList, String[] cmd) {

        String[] keywordarray = Arrays.copyOfRange(cmd, 2, cmd.length);

        int[] points = new int[streamList.length];

        int i=0;

        for(StreamInfo si : streamList){
            points[i] = numberOfKeywordMatches(si.keywords, keywordarray);
            points[i] += numberOfNameMatches(si.name, keywordarray);
            i++;
        }

        //order them

        int len = points.length;
        int temp;
        StreamInfo stmp = null;

        for (i = 0; i < len; i++)
        {
            for (int j = i + 1; j < len; j++)
            {
                if (points[i] < points[j])
                {
                    temp = points[i];
                    points[i] = points[j];
                    points[j] = temp;

                    stmp = streamList[i];
                    streamList[i] = streamList[j];
                    streamList[j] = stmp;
                }
            }
        }

        for (i=0;i< len; i++){
            if (points[i] == 0){
                break;
            }
        }

        StreamInfo[] newstreamlist = Arrays.copyOfRange(streamList, 0, i);
        printStreamerList(newstreamlist);
    }

    private static int numberOfNameMatches(String name, String[] keywordarray) {
        String[] splitname = name.split(" ");
        int points=0;
        for(String k1 : keywordarray){
            for(String k2 : splitname){
                if(k1.equalsIgnoreCase(k2)){
                    points++;
                }
            }
        }
        return points;
    }

    private static int numberOfKeywordMatches(String[] keywords, String[] keywordarray) {
        int points=0;
        for(String k1 : keywordarray){
            for(String k2 : keywords){
                if(k1.equalsIgnoreCase(k2)){
                    points++;
                }
            }
        }
        return points;
    }


    private static void searchForAndPlayStream(String[] cmd) {
        StreamInfo[] si = portal.getStreamList();

        String[] streamnamearray = Arrays.copyOfRange(cmd, 2, cmd.length);
        StringBuilder builder = new StringBuilder();

        for (String string : streamnamearray) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(string);
        }

        String streamname = builder.toString();

        StreamInfo stream = null;
        for(StreamInfo s : si){
            if(s.name.equalsIgnoreCase(streamname)){
                System.out.println("streamname: " + streamname + " at " + s.endp.ip+":"+s.endp.port);
                stream = s;
                break;
            }
        }

        if(stream != null){

            Thread serverthread = new Thread(){
                public void run(){
                    createLocalServer(); //returns port
                    try {
                        acceptConnection();

                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            };
            serverthread.setDaemon(true);
            serverthread.start();

            //String[] vlcmd = {"vlc", "tcp://127.0.0.1:10020"};
            //runCommand(vlcmd);

            try {
                Process process = Runtime.getRuntime().exec("vlc tcp://127.0.0.1:" + Integer.toString(OUTPORT));
            } catch (IOException e) {
                System.out.println("error with vlc execution");
                return;
            }

            try {
                Thread.sleep(2000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                return;
            }

            getStreamAndStream(stream.endp.port);

        }else{
            System.out.println("Stream not found.");
        }

    }

    private static String[] parse(String line){
        return line.split(" ");
    }


    private static String runCommand(String[] cmd) {
        String result = "";

        try {
            ProcessBuilder pb = new ProcessBuilder(cmd).inheritIO();
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            result = builder.toString();

            return result;
        } catch (Exception e) {
            System.out.println("exception runCommand:" + e.getMessage());
        }
        return result;
    }

}
