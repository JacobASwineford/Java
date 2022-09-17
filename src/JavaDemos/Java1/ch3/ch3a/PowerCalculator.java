package JavaDemos.Java1.ch3.ch3a;

import java.util.Scanner;

/**
 * Performs integer exponentiation with a user-specified base and exponent.
 * @author Jacob Swineford
 */
public class PowerCalculator {

    public static void main(String[] args) {

        // prompt the user and read output
        System.out.print("Enter two integers (base and exponent): ");
        Scanner in = new Scanner (System.in); //enables user input
        int base = in.nextInt(); //First integer entered is the base
        int exponent = in.nextInt(); //second integer is the exponent

        //Calculate the result
        int result = (int) Math.pow(base, exponent); //Pow is a static method; a method that doesnt depend on th// e object

        //format and display the result
        String output = base + "^" + exponent + " = " + result;
        System.out.println(output);
    }
}
