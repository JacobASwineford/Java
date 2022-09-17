package Misc.CustomClasses.PalificoOnline;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class PalificoClient {

    public static void main(String[] args) throws Exception
    {
        Socket server = new Socket("192.168.1.8", 5000);
        PrintWriter output = new PrintWriter(server.getOutputStream(), true);
        Scanner input = new Scanner(server.getInputStream());
        Read read = new Read(input); // input -> console
        Write write = new Write(output); // kb -> output
        write.start();
        read.start();
    }
}
