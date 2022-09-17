package Misc.Server.Echo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client-side for the Echo server. This client inputs a line from the keyboard,
 * and it gets echoed back to the user via the server.
 *
 * @author Jacob Swineford
 */
public class EchoClient {

    public static void main(String[] args) throws IOException {
        final String host = "Localhost";
        System.out.println("Echo client starting.");
        try (Socket socket = new Socket(host, EchoServer.PORT)) {
            System.out.println("Echo client connected.");
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());
            Scanner keyboard = new Scanner(System.in);
            while (true) {
                System.out.print("Client: ");
                String msg = keyboard.nextLine();
                out.println(msg);
                if (msg.equals(".")) {
                    break;
                }
                String echo = in.nextLine();
                System.out.println(echo);
            }
            System.out.println("Echo client terminating.");
        }
    }
}
