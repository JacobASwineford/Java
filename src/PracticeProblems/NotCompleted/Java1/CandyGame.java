package PracticeProblems.NotCompleted.Java1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Plays the candy game given the number of students and
 * their initial amounts of candy. This game has the student
 * pass half of their candy to the right, and if the value of
 * candy that the receiver has afterword is odd, then they are
 * given one more piece of candy.
 *
 * Plays the game until all the students have 12 pieces of candy.
 *
 * DISCLAIMER: DOES NOT WORK IF THE SUM OF THE CANDY GIVEN TO
 * THE STUDENTS IS MORE THAN numStudents * 12. ALSO DOESN'T WORK
 * PROPERLY IF ANY OF THE CANDY COUNTS ENTERED ARE NEGATIVE.
 *
 * DOES NOT CALCULATE THE GAME PROPERLY (SEE BELOW)
 * @author Jacob Swineford
 */
public class CandyGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int students = in.nextInt();
        System.out.print("Enter candy counts: ");
        int[] counts = new int[students];

        int rounds = 0;

        // putting amounts into an array of numbers
        for (int i = 0; i < students; i++) {
            int x = in.nextInt();
            counts[i] += x;
        }

        System.out.println();
        while (rounds < 3) {
            int [] copy = playCandyGame(counts, students);
            System.arraycopy(counts, 0, copy, 0, students);
            System.out.println(Arrays.toString(copy));
            counts = copy;
            rounds++;
        }
        System.out.println("\n" + "The game ended after "
                + rounds  + " rounds.");


        // iterate how many times the candy game will be played.

    }

    /**
     * Plays the candy game with the given int[] if the value
     * of the candy is initially even then half the value of
     * that candy (8 -> 4) is given to the student on their right.
     *
     * This happens simultaneously. This is done by creating two
     * arrays, one that has arr's values halved and another that
     * should be identical that holds the other half of those values.
     * Then, the second array is added back to the original int[]'s
     * locations + 1 (for the last it loops back to the beginning).
     *
     * If the amount of candy a student ends up with is
     * odd, then they are given one more piece of
     * candy.
     *
     * returns the same array but changed depending on the game.
     *
     * CURRENTLY DOES NOT WORK AS INTENDED.
     */
    private static int[] playCandyGame(int [] arr, int students) {

        // half all of the values in the initial array
        for (int i = 0; i < students; i++) {

            arr[i] = arr[i] / 2;

            if (arr[i] % 2 != 0) {
                arr[i]++;
            }
            
        }

        int[] halfClone = new int[students];
        System.arraycopy(arr, 0, halfClone, 0, students);

        // add values from halfClone to the right value (k + 1)
        for (int k = 0; k < students; k++) {

            if (k == 0) {
                arr[0] += halfClone[students - 1];
            } else if (k == students - 1) {
                arr[k] += halfClone[k - 1];
            }
        }
        return arr;
    }
    /**
     * Checks if all the values in the array are equal to
     * 12. returns true if otherwise, signifying to the loop
     * to stop looping after the prerequisites are met.
     */
    private static boolean allTwelve(int[] arr) {
        for (int i : arr) {
            if (i != 12) {
                return true;
            }
        }
        return false;
    }
}
