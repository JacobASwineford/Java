package Misc.CustomClasses.Cipher;
import java.util.Scanner;

/**
 * Cracks and Encodes Strings based on a Cipher Set.
 * A Cipher Set is based on a Cipher Disk such that if
 * a = b, b = c, etc. Cipher Disks loop on their values
 * and are restricted to their values. Cipher Sets base
 * themselves on the ASCII table so its values aren't
 * restricted by its shape, and its values don't loop on
 * themselves.
 *
 * @author Jacob Swineford
 */
public class CipherSet {

    // f on top of s creates difference
    private int dif;

    public CipherSet(char f, char s) {
        dif = f - s;
    }

    public String decrypt(String code) {
        StringBuilder sb = new StringBuilder();
        for (char c : code.toCharArray()) {
            int index = c - dif;
            sb.append((char) index);
        }
        return sb.toString();
    }

    public String encrypt(String code) {
        StringBuilder sb = new StringBuilder();
        for (char c : code.toCharArray()) {
            int index = c + dif;
            sb.append((char) index);
        }
        return sb.toString();
    }

    // Example Program
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("f over s, followed by message: ");
        String[] line = in.nextLine().split(" ");
        char f = line[0].toCharArray()[0];
        char s = line[1].toCharArray()[0];
        System.out.println((int) f + " - " + (int) s + " = " + ((int) f - (int) s));
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < line.length; i++) {
            String temp = line[i] + " ";
            sb.append(temp);
        }
        String code = sb.toString().trim();
        CipherSet c = new CipherSet(f, s);
        System.out.println("Cracked Code: " + c.decrypt(code));
        System.out.println("Encrypted Code: " + c.encrypt(code));
        System.out.println("Potential Characters and values: ");
        int i = 0;
        while (true) {
            if (i == 500) {
                break;
            }
            System.out.println("Number " + i + " " + (char) i);
            i++;
        }
    }
}
