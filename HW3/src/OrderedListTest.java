import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Test class for OrderedList.
 * This class showcases methods from OrderedList,
 * and tests it's output to make sure everything is done correctly.
 *
 * @author Jacob Swineford
 */
public class OrderedListTest {

    private static OrderedList<Integer> ol = new OrderedList<>();
    private static OrderedList<Integer> ol2 = new OrderedList<>();
    public static void main(String[] args) {
        int[] arr1 = new int[6];
        int[] arr2 = new int[6];
        randomize(arr1);
        randomize(arr2);

        // inserts in proper order
        for (int i : arr1) {
            ol.insert(i);
        }
        for (int i : arr2) {
            ol2.insert(i);
        }

        System.out.println("Random Integers: " + Arrays.toString(arr1));
        System.out.println("Ordered List 1: " + ol);

        System.out.println("\nRandom Integers: " + Arrays.toString(arr2));
        System.out.println("Ordered list 2: " + ol2);

        System.out.println("\nCombined List: " + ol.add(ol2));

        System.out.println("\n4 Largest Elements: " + ol.kLargest(4));

        System.out.println("\nGet Element 0: " + ol.get(0));

        ol.remove(ol.get(0));
        System.out.println("Remove Element 0: " + ol);

        int i = ol.size();
        try
        {
            System.out.println(ol.get(i));
        }  catch (IndexOutOfBoundsException e)
        {
            System.out.println("\n" + i + " is out of range for current OrderedList");
        }

        int i2 = -1;
        try
        {
            System.out.println(ol.get(i2));
        } catch (IndexOutOfBoundsException e)
        {
            System.out.println(i2 + " is out of range for current OrderedList");
        }
    }

    private static void randomize(int[] r) {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        for (int i = 0; i < r.length; i++) {
            r[i] = rand.nextInt(100) + 1;
        }
    }
}
