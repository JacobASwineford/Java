package JavaDemos.Java2.ch10.ch10b;

/**
 * A guard dog may or may not be fierce.
 *
 * @author Jacob Swineford
 */
public class GuardDog extends Dog {

    private boolean isFierce;

    /**
     * Creates a guard dog with a given name, licence number and fierceness status.
     */
    public GuardDog(String name, int licence, boolean isFierce) {
        super(name, licence);
        this.isFierce = isFierce;
    }

    /**
     * Creates a fierce guard dog with a given name, licence number.
     */
    public GuardDog(String name, int licence) {
        this(name, licence, true);
    }

    @Override
    public String toString() {
//        if (isFierce) {
//            return super.toString() + " I am fierce.";
//        }
//        return super.toString() + " I am meek.";

        return super.toString() + " I am " + (isFierce ? "fierce." : "meek.");
    }

    /**
     * This dog attacks or retreats depending on whether it is fierce.
     */
    public void attackStranger() {
        if (isFierce) {
            System.out.println(name + " is attacking stranger.");
        } else {
            System.out.println(name + " is running away from a stranger");
        }
    }

    public static void main(String[] args) {
        GuardDog dog = new GuardDog("boy", 213213);
        System.out.println(dog);
        dog.speak();
        dog.attackStranger();
    }
}


