package JavaDemos.Java1.ch3.ch3a;

import javax.swing.*;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Performs unbounded integer exponentiation with a user-specified base and exponent.
 * This version uses dialog boxes for I/O.
 *
 * @author Jacob Swineford
 */
public class PowerCalculator3 {

    public static void main(String[] args) {

        // prompt the user and read output
        String prompt = "Enter base and exponent.";
        String input = JOptionPane.showInputDialog(prompt);

        //extract the base and exponent from the input string
        Scanner sc = new Scanner(input);
        BigInteger base = sc.nextBigInteger(); //Scanner interprets the next big integer
        int exponent = sc.nextInt(); //"23 57" takes the 57 after the previous statement takes the 23

        //Calculate the answer
        BigInteger result = base.pow(exponent); //Pow method only takes an inr arguments

        //format and display the answer
        String output = base + "^" + exponent + " = " + result;
        JOptionPane.showMessageDialog(null, output);


    }
}
