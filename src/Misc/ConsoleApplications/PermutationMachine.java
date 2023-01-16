package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that takes an Array of type T and prints the various permutations that can be
 * made with those objects.
 *
 * @param <T> Type T to be Permuted
 *
 * @author Jacob Swineford
 */
public class PermutationMachine<T> {

    private final T[] arr;
    private final LinkedList<T> restorer;
    private final LinkedList<LinkedList<T>> permutations;

    boolean print = false;

    /**
     * Constructor for the Permutation Machine. The Array to be permuted must have the same
     * type as the generic given to this object.
     *
     * @param arr array to permute
     */
    public PermutationMachine(T[] arr) {
        this.arr = arr;
        restorer = new LinkedList<>();
        restorer.addAll(Arrays.asList(arr));
        permutations = new LinkedList<>();
    }

    /**
     * Permutes the given array and immediately prints the results. The results will be printed
     * on multiple lines, with one permutation on each line, and in the format [..., ...], where
     * "..." will be dictated by an object's .toString() conversion.
     */
    public void permute() {
        print = true;
        permuteRe(0, arr.length);
        restore();
    }

    /**
     * Permutes the given array and stores the result in this instance.
     */
    public void permuteStore() {
        print = false;
        permuteRe(0, arr.length);
        restore();
    }

    /**
     * Permutes the given Array of type T. Depending on the status of the 'print' flag,
     * the results are either stored in 'permutations' or printed directly.
     * This algorithm is in-place, so the permuted array is modified. to restore its original
     * ordering, restore() is called.
     */
    private void permuteRe(int from, int to) {
        int len = to - from;

        // base case
        if (len == 2) {
            swap(from, to - 1);
            if (print) print();
            else store();
            swap(from, to - 1);
            if (print) print();
            else store();
            return;
        }

        for (int flip = 0; flip < len; flip++) {
            swap(from, from + flip);
            permuteRe(from + 1, to);
            swap(from, from + flip);
        }
    }

    public LinkedList<LinkedList<T>> getPermutations() {return permutations;}

    /**
     * Restores the order of the passed array after the in-place permutation algorithm.
     */
    private void restore() {
        for (int i = 0; i < arr.length; i++)
            arr[i] = restorer.get(i);
    }

    /**
     * Swaps the place of two objects in the given array.
     *
     * @param a index of first item
     * @param b index of second item
     */
    private void swap(int a, int b) {
        T ob = arr[a];
        arr[a] = arr[b];
        arr[b] = ob;
    }

    /**
     * Prints out the contents on the given array with the format [..., ...] where
     * "..." is determined by a given types toString() method.
     */
    private void print() {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1)
                System.out.print(arr[i] + " ");
            else
                System.out.print(arr[i]);
        }
        System.out.println("]");
    }

    /**
     * Stores a permutation, based on the current status of the given array.
     */
    private void store() {
        LinkedList<T> st = new LinkedList<>(Arrays.asList(arr));
        permutations.add(st);
    }

    /**
     * Extracts a Character[] from a string literal.
     *
     * @param str string literal
     * @return Character[] with the characters from str
     */
    private static Character[] from(String str) {
        int len = str.length();
        Character[] arr = new Character[len];
        int count = 0;
        for (char c : str.toCharArray()) {
            arr[count++] = c;
        }
        return arr;
    }

    public static void main(String[] args) {
//        String str = "ask";
//        Character[] arr = from(str);
//        PermutationMachine<Character> pm = new PermutationMachine<>(arr);
//        pm.permuteStore();
//        LinkedList<LinkedList<Character>> re = pm.getPermutations();
//        for (LinkedList<Character> g : re) {
//            for (Character c : g) {
//                System.out.print(c + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        pm.permute();
        int i = 0;
        while (i < 1000000000)
            i++;
        System.out.println("done");
    }
}
