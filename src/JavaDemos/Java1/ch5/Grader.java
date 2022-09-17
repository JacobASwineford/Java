package JavaDemos.Java1.ch5;

import javax.swing.*;
import java.util.Scanner;

/**
 * Reads three test scores and calculates a letter grade according to the
 * following rule:
 *
 * 1. If the average is at least 90, then A.
 *
 * 2. If the average is between 70 and 89 inclusive:
 *    -- If the score is at least 90 and the lowest score is at least 70, then
 *    -- otherwise, B.
 *
 * 3. If the average is between 50 and 69 inclusive:
 *    -- If the average of second and third scores is a least 75, then C.
 *    -- otherwise, D.
 *
 * 4. If the average is less than 50, then F.
 *
 * @author Jacob Swineford
 */
public class Grader {

    public static void main(String[] args) {



        String prompt = "Enter three test scores.";
        String scores = JOptionPane.showInputDialog(prompt); // .trim for String objects gets rid of spaces on either end
        Scanner in = new Scanner(scores); //scanning input from Scores String inputted into dialog box instead of from the IDE

        int s1 = in.nextInt();
        int s2 = in.nextInt();
        int s3 = in.nextInt();

        String result;
        if (isValid(s1) && isValid(s2) && isValid(s3)) {
            char grade = getGrade(s1, s2, s3);
            result = "The composite grade is " + grade + ".";
        } else { // if else is not there, the compiler will ignore the top result string
            result = "Invalid input, Scores must be between 0 and 100.";
        }

        JOptionPane.showMessageDialog(null, result);

    }

    /**
     * Returns the letter grade given three test scores
     */
    private static char getGrade(int t1,int t2,int t3) {

        //step 1
        int avg = (t1 + t2 + t3) / 3;
        if (avg >= 90) {
            return 'A'; // returns as a character. ' ' denotes this

        }

        // step 2
        if (avg >= 70) {
            int lowest = Math.min(t1, Math.min(t2, t3));
            if (t1 >= 90 && lowest >= 70) {
                return 'A';
            }
            return 'B';
        }

        // step 3
        if (avg >= 60) {
            int avg23 = (t2 / t3) / 2;
            if (avg23 >= 75) {
                return 'C';
            }
            return 'D';
        }

        // step 4
        return 'F';

    }

    /**
     * Returns true if a given integer is in the correct range for
     * test scores (0-100), false otherwise.
     */
    private static boolean isValid(int num) {
        return (num >= 0 && num <= 100); // the logical version of "and" for boolean values is written as &&
        // else is redundant here since a helper method can only return one value
    }

}
