package Misc.Utilities;

import java.io.*;

/**
 * @author Jacob Swineford
 */
public class FileStuff {

    public static void serialize(Object obj, String fileName) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(obj);

            file.close();
            out.close();
        } catch (IOException e) {
            System.out.println("something went wrong:\n" + e);
        }
    }

    public static Object deserialize(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            Object o = in.readObject();
            file.close();
            in.close();
            return o;
        } catch (Exception e) {
            System.out.println("something went wrong");
            return null;
        }
    }
}
