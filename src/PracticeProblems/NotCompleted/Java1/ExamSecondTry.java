package PracticeProblems.NotCompleted.Java1;

import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class ExamSecondTry {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a phrase with at least three words: ");
        String phrase = in.nextLine();

        int fSpace = phrase.indexOf(" ");
        int lSpace = phrase.lastIndexOf(" ");

        String firstword = phrase.substring(0, fSpace);
        String lastword = phrase.substring(lSpace + 1);
        String middleword = phrase.substring(fSpace + 1, lSpace);

        System.out.println(firstword);
        System.out.println(lastword);
        System.out.println(middleword);
    }
}
