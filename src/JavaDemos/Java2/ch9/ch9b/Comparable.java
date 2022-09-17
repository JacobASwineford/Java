package JavaDemos.Java2.ch9.ch9b;

/**
 * imposes a total ordering on the objects of any class that implements this
 * interface. It is a version of an interface of the same name in the Java API.
 * The library version uses a generic type, but this custom non-generic interface
 * better serves immediate pedagogical goals.
 *
 * @author Jacob Swineford
 */
public interface Comparable {

    /**
     * Compares this object with the given object for order. Returns a negative
     * integer, zero, or a positive integer depending on whether this object
     * is less than, equal to, or greater than the given object respectively.
     */
    int compareTo(Object o); // abstract method
}

