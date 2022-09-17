package JavaDemos.Java2.ch10.ch10b;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Illustration of upcasting.
 *
 * @author Jacob Swineford
 */
public class PetDemo1 {
    public static void main(String[] args) {

        // Inheritance expresses an is-a relationship. Here we can say that a
        // dog (subtype) is a kind of pet (supertype). Whenever a superclass
        // reference is required, a subtype reference is valid. the upcast is
        // implicit.
        Pet myPet = new Dog("doodles", 12321321);



        //myPet.fetchStick();
        // What's wrong with this? The variable myPet refers to an instance
        // of the dog class, which has a fetchStick method...
    }
}

