package Misc.ConsoleApplications;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class PercentDifference {

    public static void main(String[] args) {
        printStuff(.256, 1);
        printStuff(.339, 2);
        printStuff(.546, 4);
        printStuff(.681, 8);
        System.out.println();
        printStuff(.464, 1);
        printStuff(.228, 2);
        printStuff(.115, 4);
        System.out.println();
        printStuff(.464, 1);
        printStuff(.677, 2);
        printStuff(.298, 4);


    }

    private static double percentDiff(double e, double a) {
        double p = (e - a) / a;
        return p * 100;
    }

    public static double glassPrismRefraction(double apex, double deviation) {
        double a = Math.toRadians(.5 * (deviation + apex));
        double b = Math.toRadians(.5 * apex);
        return Math.sin(a) / Math.sin(b);
    }

    // f = di * do / di + do
    private static double focalLength(double di, double dom) {
        return (di * dom) / (di + dom);
    }

    // 1/ di + 1/do = 1/f
    private static double focalLength2(double di, double dom) {
        double a = 1 / di;
        double b = 1 / dom;
        return 1 / (a + b);
    }

    private static double average(double... values) {
        double a = 0;
        for (double d : values) a += d;
        return a / values.length;
    }

    private static double serial = .456;
    private static void printStuff(double parallel, int worldSize) {
        double sp = serial / parallel;
        double ef = sp / worldSize * 100;
        System.out.printf("(%d processes): %.3f sec -> S: %.3f, E: %.3f%%\n",
                          worldSize, parallel, sp, ef);
    }

}
