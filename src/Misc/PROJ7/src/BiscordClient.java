package Misc.PROJ7.src;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class that connects and interacts with the Biscord server. Namely,
 * this class initiates threads to read and write console output for
 * independent clients.
 *
 * @author Jacob Swineford
 */
public class BiscordClient {

    public static void main(String[] args) throws Exception
    {
        // specify and connect a socket for communication between
        // the Server and the client machines. this needs the host string
        // of the given machine. for the original machine this was written
        // on (windows), type "ipconfig" in the command prompt and use the
        // IPv4 address. the port number must be the same as the server
        // this socket is trying to connect to.
        Socket server = new Socket("192.168.1.6", 4444);

        // establish a PrintWriter object, one that is to be used for outputting
        // to the given output stream.
        PrintWriter output = new PrintWriter(server.getOutputStream(), true);

        // establish a Scanner object, one that produces values based on the given
        // input stream (my guess is that this is local, which prevents needing to call
        // on the server stream multiple times).
        Scanner input = new Scanner(server.getInputStream());

        // established "read" thread that constantly reads the input from the server
        // and sends it to this machine (as specified by println()).
        Read read = new Read(input); // input -> console

        // established "write" thread that writes to the server using the previously
        // written PrintWriter. specifically, it writes from the keyboard.
        Write write = new Write(output); // keyboard -> output
        write.start();
        read.start();
    }
}
