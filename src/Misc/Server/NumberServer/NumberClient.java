package Misc.Server.NumberServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Establishes a socket connection with NumberServer and receives a unique integer as a
 * reward.
 *
 * @author Drue Coles
 */
public class NumberClient {
    public static void main(String[] args) throws IOException {
        final String host = "Localhost";  // 127.0.0.1
        try (Socket socket = new Socket(host, NumberServer.PORT)) {
            Scanner in = new Scanner(socket.getInputStream());
            Scanner keyboard = new Scanner(System.in);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Enter a sentence: ");
            out.println(keyboard.nextLine());

            String message = in.nextLine();
            System.out.println("Echo: " + message);
        }
    }
}

