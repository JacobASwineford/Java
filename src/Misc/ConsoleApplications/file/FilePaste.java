package Misc.ConsoleApplications.file;

import java.io.*;

/**
 * @author Jacob Swineford
 */
public class FilePaste {

    public static void main(String[] args) throws Exception {
        File f = new File("src\\Misc\\ConsoleApplications\\file\\paste.txt");
        FileWriter w = new FileWriter(f);
        w.append(" append this string!");
        w.close();
    }

}
