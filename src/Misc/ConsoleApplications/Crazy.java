package Misc.ConsoleApplications;

/**
 * @author Jacob Swineford
 */
public class Crazy {

    public static void main(String[] args) {
        int i = 43;
        System.out.println(leading(i, 4));

    }

    // apply leading zeros to a particular length, for a number
    private static String leading(int i, int length) {
        StringBuilder sb = new StringBuilder();
        String in = Integer.toString(i);
        int zeros = length - in.length();
        while (zeros > 0) {
            sb.append(0);
            zeros--;
        }
        for (int e = 0; e < in.length(); e++)
            sb.append(in.charAt(e));
        return sb.toString();
    }
}
