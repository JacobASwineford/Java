package Misc.ConsoleApplications.Pokemon;

interface testInterface {
    void doSomething();
}

public class Runner {

    public static void something() {
        System.out.println("I am the something!");
    }

    public static void otherSomething() {
        System.out.println("I am the other something!");
    }

    public static void main(String[] args) {
        testInterface test = Runner::something;
        test.doSomething();
        test = Runner::otherSomething;
        test.doSomething();
    }
}
