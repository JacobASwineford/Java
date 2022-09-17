package Misc.Server.NumberServer;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * Server listens for a client connection and sends back a unique ID number.
 *
 * @author Drue Coles
 */
public class NumberServer {
    public static final int PORT = 1234;

    public static void main(String[] args) throws IOException {
        System.out.println("Number server: starting.");
        ServerSocket ss = new ServerSocket(PORT);
        int number = 1;
        while (true) {
            try (Socket socket = ss.accept()) {
                System.out.printf("Number server: sending %d to client.%n", number);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                Scanner in = new Scanner(socket.getInputStream());
                String msg = in.nextLine();
                System.out.println("recieved: " + msg);
                out.println(msg);
            }
        }
    }
}
