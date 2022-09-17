package JavaDemos.Java2.ch9.ch9b;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Sorted list of arbitrary comparable objects.
 * @author Jacob Swineford
 */
public class SortedList {

    private ArrayList<Comparable> list; // objects of type Comparable (those who implement comparable)

    /**
     * Creates an empty list of line segments.
     */
    public SortedList() {
        list = new ArrayList<>();
    }

    /**
     * returns the size of this list.
     */
    public int size() {
        return list.size();
    }

    /**
     * returns the Object at a given position in the list.
     */
    public Comparable get(int i) {
        return list.get(i);
    }

    /**
     * Adds an object to this list at its correct position in length order.
     */
    public void add(Comparable comparableObject) {
        int i = 0; // pos to insert

        while (i < list.size()) {
            Comparable objectInList = get(i);
            if (comparableObject.compareTo(objectInList) <= 0) { // late binding. refers to compareTo method
                break;
            }
            i++;
        }
        list.add(i, comparableObject);
    }

    /**
     * Quick test p.
     */
    public static void main(String[] args) {
        SortedList list = new SortedList();
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            // random coordinates for two endpoints
            double a = 10 * rand.nextDouble();
            double b = 10 * rand.nextDouble();
            double c = 10 * rand.nextDouble();
            double d = 10 * rand.nextDouble();
            list.add(new LineSegment(a, b, c, d));
        }

        // traverse list and output contents
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}

