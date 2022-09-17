package JavaDemos.Java2.ch10.ch10b;

/**
 * An immutable pet with a name.
 *
 * @author Jacob Swineford
 */
public abstract class Pet {

    protected final String name;

    /**
     * Creates a pet with the given name.
     */
    public Pet(String name) {
        this.name = name;
    }

    /**
     * Makes a sound.
     */
    abstract void speak();

    /**
     * Returns a description of this pet.
     */
    @Override
    public String toString() {
        return "My name is " + name + ".";
    }
}

