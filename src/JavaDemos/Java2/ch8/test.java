package JavaDemos.Java2.ch8;

import java.util.ArrayList;

/**
 * @author Jacob Swineford
 */
public class test {

    private static final int i = 2;

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        // arrays have values 1 - 9, find the alternating values if the first
        // array only has 1 2 3 4 6 8 9 (missing 5 7)
        for (int i = 1; i < 10; i++) {
            arr.add(i);
        }
        arr.remove(5);
        arr.remove(7);
        arr.remove(4);
        arr.remove(2);
        for (int g = 0; g < 10; g++) {
            if (!arr.contains(g)) {
                arr2.add(g);
            }
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : arr2) {
            System.out.print(i + " ");
        }
    }
}
