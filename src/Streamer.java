import SD2.Endpoint;
import SD2.PortalPrx;
import SD2.PortalPrxHelper;
import SD2.StreamInfo;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by iamfuzzeh on 11/21/16.
 */
public class Streamer {

    private static final int PSIZE = 2048;
    private static int PID;
    private static PortalPrx portal;
    private static StreamInfo streaminfo;
    private static Ice.Communicator ic;

    private static int vlcport = 10010;
    private static int serverport = 7000;

    public static void main(String args[]) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                shutdownHook();
            }
        }, "Shutdown-thread"));


        String avconvstreamfilecmd[] = avconvConfigPrompt();
        System.out.println(runCommand(avconvstreamfilecmd));

        registerAtPortal();

        final ThreadedServer server = new ThreadedServer(serverport);

        Thread runserver = new Thread(){
            public void run(){
                server.main(null);
            }
        };

        runserver.setDaemon(true);
        runserver.start();

        try {
            Socket tcpsocket = new Socket(streaminfo.endp.ip, vlcport);
            BufferedInputStream bis = new BufferedInputStream(tcpsocket.getInputStream());
            int bytes;
            while ((bytes = bis.read()) != -1) {
                try{
                    server.broadcast(bytes);
                }catch (IOException e){
                    System.out.println("error: " + e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void shutdownHook() {
        System.out.println("In shutdown hook");
        portal.closeStreamer(streaminfo);
        if (ic != null) {
            // Clean up
            //
            try {
                ic.destroy();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private static void registerAtPortal() {
        ic = null;
        try {
            ic = Ice.Util.initialize();
            Ice.ObjectPrx base = ic.stringToProxy("Portal:default -p 10000");
            portal = PortalPrxHelper.checkedCast(base);
            if (portal == null) {
                throw new Error("Invalid proxy");
            }
            portal.registerStreamer(streaminfo);

        } catch (Ice.LocalException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    private static String[] generateKeywords(String title) {
        String[] keywords = title.split(" ");
        return keywords;
    }

    private static String[] avconvConfigPrompt() {

        String path;
        String videnc;
        String audenc;
        String audbit;
        String strict = " -strict experimental";
        String streaming = " -f mpegts";
        String listen = "?listen";
        String streamname = "unnamed stream";
        String address = "127.0.0.1";
        String protocol;
        String resolution;
        String vidbit;

        Scanner in = new Scanner(System.in);
        String line;
        File file;

        System.out.println("streaming name? (default is teststream, press enter to skip)");
        if( !(line = in.nextLine()).isEmpty() ){
            streamname = line;
        }


        do {
            System.out.println("file ?");
            path = in.nextLine();
            file = new File(path);
        } while (!file.exists());

        System.out.println("video encoder? (default is libx264, press enter to skip)\n" +
                "1) libx264\n" +
                "2) libx265");
        switch (in.nextLine()) {
            case "2": {
                videnc = "libx265";
            }
            default: {
                videnc = "libx264";
            }
        }

        System.out.println("video bitrate? (default is 500k, press enter to skip)\n" +
                "1) 500k\n" +
                "2) 1000k");
        switch (in.nextLine()) {
            case "2": {
                vidbit = "1000k";
            }
            default: {
                vidbit = "500k";
            }
        }
        System.out.println("audio encoder? (default is aac, press enter to skip)\n" +
                "1) aac\n" +
                "2) libmp3lame");
        switch (in.nextLine()) {
            case "2": {
                audenc = "libmp3lame";
            }
            default: {
                audenc = "aac";
            }
        }

        System.out.println("audio bitrate ? (default is 32k, press enter to skip)\n" +
                "1) 32k\n" +
                "2) 64k\n" +
                "3) 128k");
        switch (in.nextLine()) {
            case "2": {
                audbit = "64k";
            }
            case "3": {
                audbit = "128k";
            }
            default: {
                audbit = "32k";
            }
        }

        System.out.println("resolution? (default is vga, press enter to skip)\n" +
                "1) vga\n" +
                "2) svga\n" +
                "3) hd720\n" +
                "4) hd1080");
        switch (in.nextLine()) {
            case "2": {
                resolution = "svga";
            }
            case "3": {
                resolution = "hd720";
            }
            case "4": {
                resolution = "hd1080";
            }
            default: {
                resolution = "vga";
            }
        }

        System.out.println("address? (default is 127.0.0.1, press enter to skip)");
        if (!(line = in.nextLine()).isEmpty()) {
            address = line;
        }
        System.out.println("port? (default is " + "10000, press enter to skip)");
        if (!(line = in.nextLine()).isEmpty()) {
            vlcport = Integer.parseInt(line);
        }

        System.out.println("serverport? (default is " + "7000, press enter to skip)");
        if (!(line = in.nextLine()).isEmpty()) {
            serverport = Integer.parseInt(line);
        }

        System.out.println( "tcp/udp? (default is tcp, press enter to skip)" +
                            "1) tcp\n" +
                            "2) udp");
        switch (in.nextLine()) {
            case "2": {
                protocol = "udp";
            }
            default: {
                protocol = "tcp";
            }
        }


        Endpoint endpoint = new Endpoint(protocol, address, serverport);
        String[] keywords = generateKeywords(streamname);

        streaminfo = new StreamInfo(streamname, endpoint, resolution, vidbit, keywords);

        String[] cmd = {"avconv", "-i", path, "-c:v", videnc, "-b:v", vidbit, "-c:a", audenc, "-b:a", audbit,
                "-s", resolution, "-strict", "experimental", "-f", "mpegts", protocol+"://"+address+":"+Integer.toString(vlcport)+"?listen"};
        return cmd;
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
