package Homework.Java1.SwinefordHW1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Given a target time in an H:MM format, calculates the average amount of time someone
 * should spend on a mile in a M:SS format. The calculated amount expects someone to run
 * the same mile time every mile (hence being the average).
 *
 * Here's an example of two calculations done to highlight the rounding problem:
 *
 * Target time: 3:05            Target time: 3:20
 * 7                            7
 * 3                            38
 * 7.0560190703218115           7.628128724672229
 * 3.3611442193086916           37.687723480333744
 *
 * Answers --
 * 3:05 = 7:04
 * 3:20 = 7:38
 *
 * @author Jacob Swineford
 */

public class Marathonrunner {

    public static void main(String[] args) {

        // Initialize Scanner and output string instructions
        Scanner sc = new Scanner(System.in);
        System.out.print("Target time: ");

        // converts the user input into integers
        String targetTime = sc.next();
        String targetHours = targetTime.substring(0, 1);
        String targetMinutes = targetTime.substring(2, 4);
        int startHour = Integer.parseInt(targetHours);
        int startMinute = Integer.parseInt(targetMinutes);

        // Referring to the extra yards beyond the 26 miles of a marathon
        double marathonDecimal = (385.0/1760.0);

        // average minutes per mile
        double averageMinutes = (((startHour * 60) + startMinute) / (26 + marathonDecimal));
        int minutesFinal = (int)(averageMinutes);

        // Initializes DecimalFormat
        DecimalFormat dou0 = new DecimalFormat("00");

        // conversions for seconds to be converted from minutes.
        double secondsInMinutes = (averageMinutes % 1);
        double unroundedSeconds = (secondsInMinutes * 60);
        double roundedSeconds = (Math.ceil(unroundedSeconds));

        // Outputs time in M:SS format
        System.out.println("Mile pace: " + minutesFinal + ":" + dou0.format(roundedSeconds));
    }
}
