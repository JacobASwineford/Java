package JavaDemos.Java1.ch3.ch3a;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Performs integer exponentiation with a user-specified base and exponent.
 * This version can deal with arbitrary-precision integers.
 *
 * @author Jacob Swineford
 */
public class PowerCalculator2 {

    public static void main(String[] args) {

        // prompt the user and read output
        System.out.print("Enter two integers (base and exponent): ");
        Scanner in = new Scanner (System.in); //enables user input
        String baseString = in.next(); //First String entered is the base
        int exponent = in.nextInt(); //second integer is the exponent

        //Calculate the result
        BigInteger base = new BigInteger(baseString);
        BigInteger result = base.pow(exponent);

        //format and display the result
        String output = base + "^" + exponent + " = " + result;
        System.out.println(output);
    }
}

