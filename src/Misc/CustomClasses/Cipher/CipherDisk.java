package Misc.CustomClasses.Cipher;
import Misc.CustomClasses.Other.SpinnableDisk;
import javafx.scene.layout.Pane;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class CipherDisk {

    private int dif;
    private static String alphKey = "abcdefghijklmnopqrstuvwxyz";
    private String postAlphKey;

    public CipherDisk(char f, char s) throws CharacterMismatchException {
        if (!Character.isLetter(f) || !Character.isLetter(s)) {
            throw new CharacterMismatchException("Characters must all be letters");
        }
        dif = Character.toLowerCase(f) - Character.toLowerCase(s);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < alphKey.length(); i++) {
            sb.append(advance(alphKey, i, dif));
        }
        postAlphKey = sb.toString();
    }

    /**
     * Encrypts the given message using the current configuration of this
     * Cipher Disk.
     * @param message given message
     * @return decrypted message
     * @throws CharacterMismatchException if String contains non-letter characters
     */
    public String encrypt(String message) throws CharacterMismatchException {
        message = strToLowerCase(message);
        StringBuilder sb = new StringBuilder();
        char[] arr = postAlphKey.toCharArray();
        for (char c : message.toCharArray()) {
            int i = alphKey.indexOf(c);
            char cha = arr[i];
            sb.append(cha);
        }
        return sb.toString();
    }

    /**
     * Decrypts the given message using the current configuration of this
     * Cipher Disk.
     * @param message given message
     * @return decrypted message
     * @throws CharacterMismatchException if String contains non-letter characters
     */
    public String decrypt(String message) throws CharacterMismatchException {
        message = strToLowerCase(message);
        StringBuilder sb = new StringBuilder();
        char[] arr = alphKey.toCharArray();
        for (char c : message.toCharArray()) {
            int i = postAlphKey.indexOf(c);
            char cha = arr[i];
            sb.append(cha);
        }
        return sb.toString();
    }

    /**
     * Spins this Cipher Disk according to the specified keys. This method may be used
     * to reinitialize the fields of this Cipher Disk to decode other messages.
     * @param f top ring Adventurer
     * @param s bottom ring Adventurer
     * @throws CharacterMismatchException if String contains non-letter characters
     */
    public void spin(char f, char s) throws CharacterMismatchException {
        CipherDisk cd = new CipherDisk(f, s);
        dif = cd.dif;
        postAlphKey = cd.postAlphKey;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char cha : alphKey.toCharArray()) {
            String str = cha + " ";
            sb.append(str);
        }
        sb = new StringBuilder(sb.toString().trim());
        sb.append("\n");
        for (char cha : postAlphKey.toCharArray()) {
            String str = cha + " ";
            sb.append(str);
        }
        return sb.toString().trim();
    }

    public Pane getSpinnableDisk(double radius, int ciphers, int numContents) {
        SpinnableDisk sd = new SpinnableDisk(radius, ciphers, numContents);
        return sd.getCipherDisk();
    }

    /**
     * Converts all characters of a String to lowercase.
     * @param str String to convert
     * @throws CharacterMismatchException if String contains non-letter characters
     */
    private static String strToLowerCase(String str) throws CharacterMismatchException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.toCharArray()[i];
            if (!Character.isLetter(c) && c != ' ') {
                throw new CharacterMismatchException("Characters must all be letters");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * Given a String, advances the number of spaces from the starting position. If
     * the difference between the start Adventurer and the end of the String is exceeded
     * by advanced, then the cursor will advance to index 0.
     * @param str given String
     * @param start index to start at
     * @param advanced positions to advance
     * @return char at advanced position
     */
    private static char advance(String str, int start, int advanced) {
        try {
            return str.toCharArray()[start + advanced];
        } catch (IndexOutOfBoundsException e) {}
        int a = advanced - (str.substring(start).length() - 1);
        return advance(str, 0, a - 1);
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println(new CipherDisk('z', 'a'));
    }

}
