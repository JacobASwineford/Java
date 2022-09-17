package Misc.TestCrap;

/**
 * @author Jacob Swineford
 */
public class test2 {

    public static void main(String[] args) {

        for (int i = 1; i <= 8; i++) {
            System.out.println("size for " + i + " bits: " + sizeFor(i));
        }
    }

    private static int sizeFor(int bits) {
        int sum = 0;
        for (int i = 0; i < bits; i++) {
            sum += Math.pow(2, i);
        }
        return sum;
    }
}
