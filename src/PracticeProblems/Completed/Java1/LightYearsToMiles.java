package PracticeProblems.Completed.Java1;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class LightYearsToMiles {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the distance in light years: ");
        BigInteger lightYears = in.nextBigInteger();

        BigInteger secondsInAYear = new BigInteger("31536000");
        BigInteger milesTravelled = new BigInteger("186000");

        BigInteger lightYearMiles = lightYears.multiply(milesTravelled);
        BigInteger lightYearMilesYears = lightYearMiles.multiply(secondsInAYear);

        NumberFormat nf = NumberFormat.getInstance();
        System.out.println(nf.format(lightYearMilesYears));






        //BigInteger SecondsPerYear = new BigInteger();
    }
}
