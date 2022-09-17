package Misc.Server.Echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Connection to the echo server, managed by the <code> ThreadPool </code>
 * class by one of it's threads.
 *
 * @author Jacob Swineford
 */
public class Connection implements Runnable {

    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    Connection(Socket socket) {
        this.socket = socket;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Something went wrong for trying to establish" +
                    " in and out streams for " + socket.getInetAddress() + e);
        }
    }

    @Override
    public void run() {
        try {
            while (in.hasNextLine()) {
                String message = in.nextLine();
                if (message.equals(".")) {
                    break;
                }
                out.println("Server: " + message);
                out.flush();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client " + socket.getInetAddress() + " has disconnected.");
            in.close();
            out.close();
        }
    }
}
