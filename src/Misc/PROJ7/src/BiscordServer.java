package Misc.PROJ7.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class that defines the protocol for the Biscord server. In this protocol,
 * the client defines a temporary username for chatting, and is given a list
 * of commands to interact with the server. The ClientHandler thread dictates
 * that multiple clients can use this service at once.
 *
 * There is a discrepancy I should point out.
 *    The 'disconnect' command is necessary for the server to remove clients from
 *    the client map, but the application can be closed before this. pms can be sent
 *    to "logged" users, but those pms can't be read unless the recipient also has
 *    the program up.
 *
 * @author Jacob Swineford
 */
public class BiscordServer {

    private static HashMap<String, Socket> clientMap = new HashMap<>();
    private static LinkedList<String> globalChat = new LinkedList<>();

    public static void main(String[] args) throws Exception
    {
        ServerSocket ss = new ServerSocket(4444);
        while (true) // the server is always running
        {
            Socket client = ss.accept();
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            Scanner input = new Scanner(client.getInputStream());

            String name = "";
            String start = "";

            output.println("Please enter your temporary username: ");

            class ClientHandler extends Thread {

                private String name;
                private String start;

                private ClientHandler(String name, String start) {
                    this.name = name;
                    this.start = start;
                }

                public void run() {
                    while (input.hasNextLine()) {
                        if (name.equals("")) {
                            String clientName = input.nextLine();
                            clientMap.put(clientName, client);
                            start = client.getInetAddress() + " \"" + clientName + "\"";
                            System.out.println(start + " connected at " + new Date());
                            name = clientName;
                            output.println(getGeneralCommands());
                            output.println(getGlobalChat());
                        } else {
                            String com = input.nextLine();
                            System.out.println(start + " issued command \"" + com +
                                    "\" at " + new Date());
                            interpretCommand(com, output, name);
                        }

                    }
                }
            }
            ClientHandler c = new ClientHandler(name, start);
            c.start();
        }
    }

    /**
     * Returns all the commands the user can use to communicate with the server.
     * chat: [Message] - sends a message to the global chat
     * pm: [User] [Message] - sends a PM to specified user
     * disconnect - disconnects this user from the Biscord server
     * logged - prints a list of the users currently logged in
     * help - prints commands used to communicate with the server
     */
    private static String getGeneralCommands() {
        return "List of Commands:\n" +
                "chat: [Message] - sends a message to the global chat\n" +
                "pm: [user] [Message] - Sends a PM to specified user\n" +
                "disconnect - disconnects this user from the Biscord server\n" +
                "logged - prints a list of the users currently logged in\n" +
                "help - prints commands used to communicate with the server";
    }

    /**
     * Interprets a command and executes via the client who called it.
     */
    private static void interpretCommand(String com, PrintWriter output, String name) {
        String[] arr = com.split(" ");
        String f = arr[0];
        String message;

        if (notACommand(f)) {
            output.println("Incorrect command. Please try again.");
            return;
        }

        if (f.equals("chat:")) {
            message = com.substring(com.indexOf(" ") + 1);
            printToGlobal(message, name);
            return;
        }

        if (f.equals("pm:")) {
            message = com.substring(com.indexOf(" ") + 1); // message with re name
            String recipient = message.substring(0, message.indexOf(" "));
            message = message.substring(message.indexOf(" ") + 1);
            printToUser(message, name, recipient);
            return;
        }

        if (f.equals("logged"))
            output.println(clientMap.keySet());

        if (f.equals("disconnect")) {
            System.out.println(clientMap.get(name).getInetAddress() + " \"" + name + "\""
                    + " disconnected at " + new Date());
            clientMap.remove(name);
            output.println("You have been disconnected from Biscord.\n Please close" +
                    " the application and have a nice day!");
        }

        if (f.equals("help"))
            output.println(getGeneralCommands());

    }

    /**
     * Prints the given message to all logged clients on this server.
     * @param message given message
     */
    private static void printToGlobal(String message, String originName) {
        String s = originName + " [GLOBAL]: " + message;
        for (Socket client : clientMap.values()) {
            try {
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                output.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        globalChat.add(originName + " [GLOBAL]: " + message);
    }

    /**
     * Prints the given message to the given origin client and recipient.
     * @param message given message
     * @param originName origin client
     * @param recipient recipient client
     */
    private static void printToUser(String message, String originName, String recipient) {
        try {
            // should NOT throw NPE, since origin is calling it
            Socket client = clientMap.get(originName);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            try {
                Socket reSocket = clientMap.get(recipient);
                PrintWriter reOutput = new PrintWriter(reSocket.getOutputStream(), true);
                reOutput.println(originName + " [" + recipient + "]: " + message);
                output.println(originName + " [" + recipient + "]: " + message);
            } catch (NullPointerException e) {
                output.println("User " + recipient + "is not currently in the server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if the given string is not a command or not. returns true
     * if so, false otherwise.
     * @param com given command
     * @return whether the command is not part of the command list.
     */
    private static boolean notACommand(String com) {
        return !com.equals("chat:") && !com.equals("pm:") && !com.equals("help") &&
                !com.equals("disconnect") && !com.equals("logged");
    }

    /**
     * Returns a string representation of the global chat in the server.
     */
    private static String getGlobalChat() {
        StringBuilder r = new StringBuilder();
        for (String s : globalChat) {
            String s2 = s + "\n";
            r.append(s2);
        }
        return r.toString();
    }
}
