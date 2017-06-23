import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by iamfuzzeh on 1/2/17.
 */

public class LocalStream {

    static String LOCALHOST = "localhost";
    static int INPORT = 10010;
    static int OUTPORT = 10020;
    static Socket insocket = null;
    static Socket outsocket = null;
    static ServerSocket serversocket;
    static DataOutputStream out;

    public static void main(String args[]){

        try {
            serversocket = new ServerSocket(OUTPORT);

        }catch (IOException e ){
            e.printStackTrace();
        }


        while(outsocket==null){
            try{
                outsocket = serversocket.accept();
                out = new DataOutputStream(outsocket.getOutputStream());
            }catch (IOException e ){
                e.printStackTrace();
            }
        }

    }

    public static void update(int bytes) throws IOException{
        try {
            out = new DataOutputStream(outsocket.getOutputStream());
            while (bytes != -1) {
                try{
                    out.write(bytes);
                    out.flush();
                }catch (IOException e){
                    System.out.println("error: " + e.getMessage());
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
