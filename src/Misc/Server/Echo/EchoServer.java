package Misc.Server.Echo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server-side for the Echo server. This server waits for a client connection
 * and assigns the server's work to a <code> Connection </code> object. This object is
 * then executed by a thread pool, where the number of threads in defined as a static
 * variable in the <code> ThreadPool </code> class.
 *
 * @author Jacob Swineford
 */
public class EchoServer {

    static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("Echo Server Started.");
        ThreadPool pool = new ThreadPool();
        System.out.println("Thread Pool created.");
        System.out.println("Awaiting clients.");
        while (true) {
            Socket socket = ss.accept();
            System.out.println("Client " + socket.getInetAddress() + " Connected.");
            Connection c = new Connection(socket);
            pool.addConnection(c);
        }
    }
}
