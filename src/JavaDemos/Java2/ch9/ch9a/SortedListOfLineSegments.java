package JavaDemos.Java2.ch9.ch9a;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class SortedListOfLineSegments {

    private ArrayList<LineSegment> list;

    /**
     * Creates an empty list of line segments.
     */
    public SortedListOfLineSegments() {
        list = new ArrayList<>();
    }

    /**
     * returns the size of this list.
     */
    public int size() {
        return list.size();
    }

    /**
     * returns the line segment at a given position in the list.
     */
    public LineSegment get(int i) {
        return list.get(i);
    }

    /**
     * Adds a line segment to this list at its correct position in length order.
     */
    public void add(LineSegment ls) {
        int i = 0; // pos to insert
        double length = ls.length();

        while (i < list.size()) {
            double otherLength = list.get(i).length();
            if (length <= otherLength) {
                break;
            }
            i++;
        }
        list.add(i, ls);
    }

    /**
     * Quick test p.
     */
    public static void main(String[] args) {
        SortedListOfLineSegments list = new SortedListOfLineSegments();
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

