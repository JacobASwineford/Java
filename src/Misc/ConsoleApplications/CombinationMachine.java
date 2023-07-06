package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that takes an array of type T and prints the various combinations that
 * can be made with those objects
 *
 * @param <T> Type T to be Combined
 *
 * @author Jacob Swineford
 */
public class CombinationMachine<T> {

    private final T[] cur;
    private final LinkedList<T[]> combinations;

    // used for algorithm indexing and adding
    private final int[] vals;
    private int bound;

    /**
     * Constructor for the combination machine. The array to be combined must
     * have the same type as the generic given to this object.
     *
     * @param arr given array of type T
     */
    public CombinationMachine(T[] arr) {
        cur = arr;
        combinations = new LinkedList<>();
        vals = new int[cur.length];
        for (int i = 0; i < cur.length; i++)
            vals[i] = i;
        bound = vals[cur.length - 1];
    }

    /**
     * Performs the algorithm to generate the combinations of the current array,
     * and stores the results within this object.
     */
    public void performCombinations(int k) {
        
    }

    /**
     * Gets the list of generated combinations. This list is initially empty.
     *
     * @return list of combinations
     */
    public LinkedList<T[]> getCombinations() {
        return combinations;
    }

    /**
     * returns the factorial of the given number.
     *
     * @return factorial of given number
     */
    private static int factorial(int num) {
        int fac = 1;
        while (num > 1)
            fac *= num--;
        return fac;
    }

    private static void add(int[] arr, int bound) {
        System.out.print("before: " + Arrays.toString(arr) + " -> ");
        int target = arr.length - 1;
        while (true) {
            arr[target]++;
            if (arr[target] == bound) {
                arr[target] -= 2;
                target--;
                continue;
            } else if (target != arr.length - 1 && arr[target] == arr[target + 1]) {
                arr[target + 1]++;
                break;
            } else {
                break; // done
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void biasThing(int[] base, int[] sol, int bias) {
        int lo = 0;
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < sol.length; j++) {
                sol[j] = base[lo];
                lo = (lo + bias) % base.length;
            }
            System.out.println(Arrays.toString(sol));
            lo = i + 1;
        }
    }

    public static void main(String[] args) {
        int[] base = {1, 2, 3, 4, 5};
        int[] arr = {1, 2, 3, 4, 5, 6};

        int[] sol = new int[4];
        int bias = 1;
        biasThing(arr, sol, bias);
        biasThing(arr, sol, bias + 1);
        biasThing(arr, sol, bias + 2);

    }
}
