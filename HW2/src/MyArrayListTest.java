/**
 * Test class for the MyArrayList class. This class aims to demonstrate
 * the capabilities and limitations of MyArrayList and how Collection
 * classes might handle user input.
 *
 * @author Jacob Swineford
 */
public class MyArrayListTest
{

    public static void main(String[] args)
    {
        MyArrayList l = new MyArrayList();
        fillArray(l);

        // add
        System.out.println("Adding:");
        System.out.println(l + " Size: " + l.size());
        l.add(4);
        System.out.println(l + " Size: " + l.size());

        // adding at index
        System.out.println("\nAdding at index (4, 26): ");
        System.out.println(l + " Size: " + l.size());
        l.add(4, 26);
        System.out.println(l + " Size: " + l.size());

        // remove
        System.out.println("\nRemoving at index (2):");
        System.out.println(l + " Size: " + l.size());
        l.remove(2);
        System.out.println(l + " Size: " + l.size());

        // remove object
        System.out.println("\nRemoving first instance of object (REMOVED): ");
        l.add(4, "REMOVED");
        System.out.println(l + " Size: " + l.size());
        l.remove("REMOVED"); // also returns true / false
        System.out.println(l + " Size: " + l.size());

        // set
        System.out.println("\nSetting: ");
        System.out.println(l + " Size: " + l.size());
        l.set(3, 62);
        System.out.println(l + " Size: " + l.size());

        // boolean contains, isEmpty
        System.out.println("\nBoolean methods: contains + indexOf, isEmpty");
        System.out.println(l + " Size: " + l.size());
        System.out.println("is empty?: " + l.isEmpty());
        System.out.println("contains 62?: " + l.contains(62));
        System.out.println("index of 62?: " + l.indexOf(62));

        // clear
        System.out.println("\nClearing and isEmpty: ");
        System.out.println(l + " Size: " + l.size());
        l.clear();
        System.out.println(l + " Size: " + l.size());
        System.out.println("is empty?: " + l.isEmpty());
        fillArray(l);

        // get
        System.out.println("\nGetting (7): ");
        System.out.println(l + " Size: " + l.size());
        System.out.println("Object at index 7: " + l.get(7));

        // sublist
        System.out.println("\nsubList (0 - 5): ");
        System.out.println(l + " Size: " + l.size());
        MyArrayList l2 = l.subList(0, 5);
        System.out.println(l2 + " Size: " + l2.size());
    }

    private static void fillArray(MyArrayList l)
    {
        for (int i = 0; i < 10; i++)
        {
            l.add(i);
        }
    }
}
