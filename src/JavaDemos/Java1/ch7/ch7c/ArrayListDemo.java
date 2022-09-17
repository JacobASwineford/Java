package JavaDemos.Java1.ch7.ch7c;

import java.util.ArrayList;

/**
 * Demonstrates basic features of the ArrayList class.
 *
 * @author Jacob Swineford
 */
public class ArrayListDemo {

    public static void main(String[] args) {

        ArrayList<String> animals = new ArrayList<>();
        animals.add("pPine");
        animals.add("pSwine");
        animals.add("pDine");
        animals.add("pAine");
        animals.add("ZPine");
        animals.add("GPine");

        // display contents of list
        for (int i = 0; i < animals.size(); i++) {
            System.out.print(animals.get(i) + " ");
        }
        System.out.println(animals.size());

        animals.add(2, "snake");
        animals.remove(4);
        animals.add("okapi"); // adds to the

        for (String s : animals) { // for each in array loop
            System.out.print(s + " ");
        }
        System.out.println(animals.size());

        // Add two animals and remove one.

    }
}

