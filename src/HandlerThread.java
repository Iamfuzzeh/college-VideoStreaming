import java.io.*;
import java.net.Socket;

/**
 * Created by iamfuzzeh on 12/30/16.
 */
public class HandlerThread extends Thread {

    protected Socket clientsocket;

    public HandlerThread(Socket clientsocket) {
        this.clientsocket = clientsocket;
    }

    InputStream in = null;
    BufferedReader buf = null;
    DataOutputStream out = null;

    public void run() {
    }

    public void update(int bytes) throws IOException {
            out = new DataOutputStream(clientsocket.getOutputStream());
            out.write(bytes);
            out.flush();
    }

}
