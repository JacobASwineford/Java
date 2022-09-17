package JavaDemos.Java2.ch9.ch9e;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Prompts the user for a line of text and outputs the words on sorted order
 * without duplicates.
 * @author Jacob Swineford
 */
public class SortingWithoutDuplicates {

    public static void main(String[] args) {
        System.out.print("Enter a line of text: ");
        String text = new Scanner(System.in).nextLine();

        // add each word to a set
        Set<String> words = new TreeSet<>(); // tree sets put Strings in the natural ordering automatically
        Scanner in = new Scanner(text);
        while (in.hasNext()) {
            words.add(in.next());
        }

        ArrayList<Character> checked = new ArrayList<>();
        String s = "sasas";
        // you can also use the split method to import strings into the tree set

        for (String word: words) {
            System.out.print(word + " ");
        }
        if ('d' == 'd')
        System.out.println();
    }
}

