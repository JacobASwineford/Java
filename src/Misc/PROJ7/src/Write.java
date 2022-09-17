package Misc.PROJ7.src;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Writes a message from the keyboard to the given thread. This behavior is mainly
 * used for multiple clients to communicate with one server.
 *
 * @author Jacob Swineford
 */
public class Write extends Thread {

    private Scanner userInput;
    private PrintWriter clientOutput;

    Write(PrintWriter pw) {
        clientOutput = pw;
        userInput = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            clientOutput.println(userInput.nextLine()); // message to server
        }
    }
}
