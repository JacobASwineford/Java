package Misc.TestCrap;

/**
 * @author Jacob Swineford
 */
public class test4 {

    public static void main(String[] args) {
        // variable choice based on right type, not data type
        A c = new C();
        c.i = 4;
        System.out.println(c.getI());
    }
}

class A {
    int i;
    String getI() {return "class C I is: " + i;}
}

class B extends A {

}

class C extends B {
    int i;
    String getI() {return "class C I is: " + i;}
}
