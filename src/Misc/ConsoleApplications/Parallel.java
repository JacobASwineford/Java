package Misc.ConsoleApplications;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class Parallel {

    public static void main(String[] args) {
        for (int i = 0; i < 11; i++) {
            BigInteger res = out(i);
            System.out.println("Result of " + i + ": " + res);
        }
    }

    private static double percentError(double actual, double expected) {
        return ((actual - expected) / actual) * 100;
    }

    private static double timeLinear(double n) {
        return Math.pow(n, 2);
    }

    private static double timeParallel(double n, double p) {
        double a = Math.pow(n, 2);
        double b = p + log2(p);
        return a / b;
    }

    private static double speedup(double n, double p) {
        return timeLinear(n) /  timeParallel(n, p);
    }

    private static double efficiency(double n, double p) {
        return timeLinear(n) / timeParallel(n, p) * p;
    }

    private static double log2(double num) {
        return Math.log(num) /  Math.log(2);
    }

    private static BigInteger[] bis = new BigInteger[11];
    private static BigInteger out(int i) {
        BigInteger b;
        if (i < 3) {
            String cal = Integer.toString(i * 5);
            b = new BigInteger(cal);
        } else {
            BigInteger mul = bis[i - 2].multiply(bis[i - 1]);
            b = mul.add(new BigInteger("5"));
        }
        bis[i] = b;
        return b;
    }

}
