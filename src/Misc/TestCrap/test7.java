package Misc.TestCrap;

import java.io.*;

/**
 * @author Jacob Swineford
 */
public class test7 {

    public static void main(String[] args) {
        String filename = "Saved/test.ser";
        AC a = new AC();
        a.a = 4;
        a.b = 33;

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(a);

            file.close();
            out.close();


        } catch (IOException e) {
            System.out.println("something went wrong");
        }

        AC empty;

        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            empty = (AC) in.readObject();
            System.out.println(empty.a + " " + empty.b);
        } catch (Exception e) {
            System.out.println("swomething went wrong");
        }

    }


}

class AC implements Serializable {
    double a;
    double b;
}