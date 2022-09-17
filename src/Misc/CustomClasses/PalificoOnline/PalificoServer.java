package Misc.CustomClasses.PalificoOnline;

import javafx.scene.paint.Color;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static javafx.scene.paint.Color.*;


/**
 * @author Jacob Swineford
 */
public class PalificoServer {

    private static HashMap<String, Socket> clientMap = new HashMap<>();

    private static ArrayList<Color> colors = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        initColors();
        ServerSocket ss = new ServerSocket(5000);
        while (true) {
            Socket client = ss.accept();
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            Scanner input = new Scanner(client.getInputStream());


            class ClientHandler extends Thread {

                private Color color;

                private ClientHandler() {}

                public void run() {
                    output.println("hi there.");
                    String username = "";
                    if (input.hasNextLine()) { // wait for username
                        username = input.next();
                    }
                    clientMap.put(username, client);
                    output.println("Please choose a dice color from this list.");
                    output.println(getAvailableColors());
                    String color = input.next();
                    this.color = Color.valueOf(color); // temp


                    output.println("Welcome to Palifico Online! Here are" +
                            "the clients currently waiting for a game:");
                    output.println(getPlayerList());
                    output.println("Here are the available dice colors: ");

                    while (true) {


                    }
                }
            }

            new ClientHandler().start();
        }
    }

    private static String getPlayerList() {
        String r = "";
        for (String client : clientMap.keySet()) {
            r += client + ", ";
        }
        return r;
    }

    private static String getAvailableColors() {
        String r = "";
        for (Color color : colors) {
            r += color + ", ";
        }
        return r;
    }

    private static void initColors() {
        colors.add(BLACK);
        colors.add(BLUE);
        colors.add(RED);
        colors.add(GREEN);
        colors.add(ORANGE);
        colors.add(YELLOW);
        colors.add(PURPLE);
    }
}


