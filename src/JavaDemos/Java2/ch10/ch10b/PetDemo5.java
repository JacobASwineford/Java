package JavaDemos.Java2.ch10.ch10b;

/**
 * Illustration class cast exceptions
 *
 * @author Jacob Swineford
 */
public class PetDemo5 {

    public static void main(String[] args) {
        Pet myPet = new Dog("Puggles", 12345);

        GuardDog duke = new GuardDog("Duke", 23213, false);
        duke.attackStranger();

        // the downcast is needed for compilation, but
        // what happens at runtime?
        GuardDog ductchess = (GuardDog) myPet;
        ductchess.attackStranger();
    }
}

