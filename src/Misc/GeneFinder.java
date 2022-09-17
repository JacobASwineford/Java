package Misc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Given a set of alleles in the form "ab", "aabb", etc. find the gene sequences
 * that result from a punnet square calculation. If the given number of sequences
 * equals two, then a punnet square calculation is directly performed. otherwise,
 * the resulting genes are found by distribution.
 *
 * @author Jacob Swineford
 */
public class GeneFinder {

    private static ArrayList<String> results = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a set of alleles (must be positive): ");
        String line = in.nextLine();
        String[] sequences = line.split(" ");
//        punnetSquare2(sequences, 0, new Stack());

        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i) + " ");
        }
    }

//    private static void punnetSquare2(String[] sequences, int on, Stack counter) {
//        if (on == sequences.length) {
//            results.add();
//            return;
//        }
//        String acting = sequences[on];
//        for (int i = 0; i < sequences[on].length(); i++) {
//            sb.append(acting.charAt(i));
//            punnetSquare2(sequences, on + 1, sb);
//            sb.delete(sequences.length - 1, sequences.length);
//        }
//    }


}
