package PracticeProblems.Completed.Java1;

import java.util.Scanner;

/**
 * inscribes an ASCI code pyramid made of asterisks.
 *
 * @author Jacob Swineford
 */
public class PyramidOfStars {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter a height of a pyramid: ");
        int levels = in.nextInt();
        String stars = "*";
        int numSpaces = levels - 1;
        String spaces = " ";
        int j = 0;

        for (int i = 0; i < levels; i++) {
            while (j < numSpaces) {
                System.out.print(spaces);
                j++;
            }
            System.out.println(stars);
            stars += "**";
            j -= numSpaces; // resets the value of j to 0
            numSpaces -= 1; // increments the number of spaces for the next loop test
        }
    }
}
