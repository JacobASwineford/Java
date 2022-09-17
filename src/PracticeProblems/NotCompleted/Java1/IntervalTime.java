package PracticeProblems.NotCompleted.Java1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class IntervalTime {

    public static void main(String[] args) {

        // Initialize Scanner and prompt user for input
        Scanner in = new Scanner(System.in);

        System.out.print("Interval distance in meters: ");
        int meters = in.nextInt();
        System.out.print("Target mile pace: ");
        String milePace = in.next();

        //extract the integers from mile pace input
        String mileMinuteString = milePace.substring(0, 1);
        String mileSecondString = milePace.substring(2); //limits the input to H:SS format
        int mileMinutes = Integer.parseInt(mileMinuteString); //5
        int mileSeconds = Integer.parseInt(mileSecondString); //15

        double minutesTrue = mileMinutes + ((double) mileSeconds / 60); //good
        double lapcount = ((double) meters / 400); //good

        DecimalFormat single0 = new DecimalFormat(".0");

        double minutesPerLap = (minutesTrue / lapcount);
        double secondsPerLap = (minutesPerLap % 1) * 60.0;


        System.out.println(minutesPerLap);
        System.out.println((minutesPerLap % 1));
        System.out.println(secondsPerLap);
        System.out.println(lapcount);

    }
}
