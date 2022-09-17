package JavaDemos.Java2.ch10.ch10b;

/**
 * A cat that is possibly a mouser.
 *
 * @author Jacob Swineford
 */
public class Cat extends Pet {

    private final boolean isMouser;

    /**
     * Creates a cat with a given name and mouser status.
     */
    public Cat(String name, boolean isMouser) {
        super(name);
        this.isMouser = isMouser;
    }

    /**
     * Creates a cat with a given name that catches mice.
     */
    public Cat(String name) {
        this(name, true);
    }

    /**
     * Makes a cat sound.
     */
    @Override
    void speak() {
        System.out.println("Meow!");
    }

    /**
     * Returns a greeting from this cat.
     */
    @Override
    public String toString() {
        if (isMouser) {
            return super.toString() + "\nI am a cat.\n I catch mice";
        }
        return super.toString() + "\nI am a cat.\n I ignore mice";
    }

    public static void main(String[] args) {
        Cat cat = new Cat("Muffin", true);
        System.out.println(cat);
        cat.speak();
    }
}

