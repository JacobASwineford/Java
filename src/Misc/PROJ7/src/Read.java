package Misc.PROJ7.src;

import java.util.Scanner;

/**
 * Prints the output that shows up on the given thread. This behavior is mainly used
 * for multiple clients to communicate with one server.
 *
 * @author Jacob Swineford
 */
public class Read extends Thread {

    private Scanner serverInput;

    Read(Scanner sc) {
        serverInput = sc;
    }
    public void run() { // reads all messages from server

        while (true) {
            if (serverInput.hasNextLine()) {
                System.out.println(serverInput.nextLine());
            }
        }
    }
}
