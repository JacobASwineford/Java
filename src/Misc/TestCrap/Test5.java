package Misc.TestCrap;

import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class Test5 {

    public static void main(String[] args) {
        LinkedList<String> s = new LinkedList<>();
        String a = "hi";
        s.add(a);
        s.add(a);
        s.add(a);
        s.add(a);
        s.add(a);
        s.add(a);
        System.out.println(s);
        s.remove(a);
        System.out.println(s);

    }
}
