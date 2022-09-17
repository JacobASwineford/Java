package Homework.Java1.SwinefordHW1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Converts an integer amount of seconds imputed by the user to a HH:MM:SS Format.
 *
 * As far as I know, there are no problems aside from perhaps being able to format
 * the final string better.
 *
 * @author Jacob Swineford
 */

public class SecondsToTime {

    public static void main(String[] args) {

        // initialize Scanner and output string instructions
        System.out.print("Enter the span of time in seconds: ");
        Scanner in = new Scanner(System.in);

        //calculates the values in which to output into a HH:MM:SS format
        int seconds = in.nextInt();
        int hours = (seconds / 3600);
        int minutes = ((seconds % 3600) / 60);
        int secondsTrue = (seconds % 3600) - (minutes * 60);

        // Initializes DecimalFormat
        DecimalFormat dou0 = new DecimalFormat("00");

        // Outputs integer values in a HH:MM:SS format
        System.out.println((dou0.format(hours)) + ":" + (dou0.format(minutes))
                + ":" + (dou0.format(secondsTrue)));

    }
}
