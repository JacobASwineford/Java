package PracticeProblems.NotCompleted.Java1;
import java.util.Scanner;

/**
 * a-e e-i i-o o-u u-a
 *
 * @author Jacob Swineford
 */
public class VowelShifter {

    public static void main(String[] args) {

        // Initialize scanner and user instructions (come back to this one)
        System.out.print("Enter a sentence: ");
        Scanner in = new Scanner(System.in);
        String sentence = in.nextLine(); //scans the whole line for input

        String sentencejum = sentence.replaceAll("g", "e");

        System.out.println(sentencejum);


        //


    }
}
