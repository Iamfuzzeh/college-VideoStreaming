import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iamfuzzeh on 12/30/16.
 */
public class ThreadedServer {

    private static int PORT = 0;
    private static ServerSocket serversocket = null;
    private static Socket socket = null;
    private static Set<HandlerThread> clients = new HashSet<HandlerThread>();

    ThreadedServer(int PORT) {
        this.PORT = PORT;
    }

    ThreadedServer(){
        this.PORT = 0;
    }


    public static void main(String args[]) {
        try {
            serversocket = new ServerSocket(PORT);
            System.out.println("server running in port: " + serversocket.getLocalPort() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                socket = serversocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HandlerThread ht = new HandlerThread(socket);
            ht.start();
            clients.add(ht);

            System.out.println("adding new client at socket: " + socket);
        }
    }

    public static void broadcast(int bytes) throws IOException {
        for (HandlerThread ht : clients) {
            try {
                ht.update(bytes);
            }catch (IOException e){
                System.out.println("removing client from list");
                clients.remove(ht);
                return;
            }
        }
    }

    public static int getPort(){
        return serversocket.getLocalPort();
    }
}
