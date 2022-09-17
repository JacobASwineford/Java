package JavaDemos.Java2.ch10.ch10b;

import javafx.scene.text.Font;

/**
 * A licenced dog.
 *
 * @author Jacob Swineford
 */
public class Dog extends Pet {

    private final int licence;

    /**
     * Creates a dog with a given name and licence.
     */
    public Dog(String name, int licence) {
        super(name);
        this.licence = licence;
    }

    /**
     * Makes a dog sound.
     */
    @Override
    void speak() {
        System.out.println("Woof!");
    }

    /**
     * Returns a greting from this dog.
     */
    @Override
    public String toString() {
        return super.toString() + "\nI am a dog.\nMy licence number is "
                + licence + ".";
    }

    public void fetchStick() {
        System.out.println(name + " the dog is fetching a stick.");
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Puggy", 12345);
        System.out.println(dog);
        dog.speak();
        dog.fetchStick();
    }
}
