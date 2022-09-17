package PracticeProblems.Completed.Java1;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Displays the hours and minutes entered by the user into a standard time format
 * starting at 12:00 AM midnight. Note that the program DOES NOT WORK PROPERLY
 * when entering negative numbers.
 *
 * @author Jacob Swineford
 */
public class AfterMidnight {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Hours and minutes: ");

        int hours = in.nextInt();
        int minutes = in.nextInt();

        String amPm = AMorPM(hours, minutes);
        String standardTime = StandardTime(hours, minutes);

        System.out.println(standardTime + amPm);

    }
    /**
     * Returns whether the time should be within AM or PM.
     *
     */
    private static String AMorPM(int x, int y) {
        int trueMinutes = (x * 60) + y;
        int trueSwitches = trueMinutes / 720;

        if (trueSwitches % 2 == 0) {
            return "AM";
        } else {
            return "PM";
        }

    }
    /**
     * Returns the String Containing the Hours and minutes entered
     * in standard time.
     *
     */
    private static String StandardTime(int x, int y) {
        int trueMinutes = (x * 60) + y;
        int standardHours = trueMinutes / 60; //1
        int standardMinutes = trueMinutes % 60; //15
        DecimalFormat double0 = new DecimalFormat("00");

        if (standardHours == 0) {
            return ("12:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 1) {
            return ("1:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 2) {
            return ("2:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 3) {
            return ("3:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 4) {
            return ("4:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 5) {
            return ("5:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 6) {
            return ("6:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 7) {
            return ("7:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 8) {
            return ("8:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 9) {
            return ("9:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 10) {
            return ("10:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 11) {
            return ("11:" + double0.format(standardMinutes) + " ");

        } else if (standardHours == 12) {
            return ("12:" + double0.format(standardMinutes) + " ");

        } else if (standardHours > 12){
            int standardHours2 = standardHours - 12 * (trueMinutes / 720);
            return (standardHours2 + ":" + double0.format(standardMinutes) + " ");
        }

        return null;
    }
}
