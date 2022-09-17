package JavaDemos.Java2.ch9.ch9h;

import java.util.Arrays;

/**
 * Sorts strings by length;
 *
 * @author Jacob Swineford
 */
public class ReverseSort {

    public static void main(String[] args) {
        String[] things = {"1234", "12345", "123456", "1234567"};

        Arrays.sort(things, (s1, s2) -> s1.length() - s2.length());
        for (String s : things) {
            System.out.println(s);
        }
    }
}

