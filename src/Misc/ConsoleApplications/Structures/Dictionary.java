package Misc.ConsoleApplications.Structures;

import java.util.ArrayList;

/**
 * A generic type of map that maps Strings to the type of object represented by T.
 *
 * @param <T> type of data to be stored
 */
public class Dictionary<T> {

    /**
     * Represents a key - data pair.
     *
     * @param <T> type of data to be stored
     */
    private static class Cart<T> {
        public T data;
        public String key;

        public Cart(String key, T data) {
            this.key = key;
            this.data = data;
        }
    }

    /**
     * Represents a list of key - data pairs.
     *
     * @param <T> type of data to be stored
     */
    private static class Bay<T> {
        public ArrayList<Cart<T>> arr;
    }

    public static int RECOMMENDED_LENGTH = 100;

    private final ArrayList<Bay<T>> map;
    private final int length;

    /**
     * Constructor that initializes memory for this dictionary.
     *
     * @param length starting length for initialization and storing.
     */
    public Dictionary(int length) {
        map = new ArrayList<>(length);
        this.length = length;

        // ArrayList won't initialize memory unless value are added
        for (int i = 0; i < length; i++)
            map.add(null);
    }

    /**
     * Constructor that initializes memory for this dictionary.
     * <p>
     * This constructor uses the recommended starting length for a map.
     */
    public Dictionary() {this(RECOMMENDED_LENGTH);}

    /**
     * Sets the given key to the given data.
     *
     * @param key given key
     * @param data given data
     */
    public void set(String key, T data) {
        int index = getHashIndex(key);
        Bay<T> bay = map.get(index);
        if (bay == null) {
            bay = new Bay<>();
            bay.arr = new ArrayList<>();
            map.set(index, bay);
        }

        // override any existing values
        for (Cart<T> cart : bay.arr) {
            if (cart.key.equals(key)) {
                cart.data = data;
                return;
            }
        }

        // create new cart to add to the bay, with a new association
        bay.arr.add(new Cart<>(key, data));
    }

    /**
     * Gets some data based on the given key.
     *
     * @param key given key
     * @return the data represented by the given key. null otherwise
     */
    public T get(String key) {
        int index = getHashIndex(key);
        Bay<T> bay = map.get(index);
        for (Cart<T> cart : bay.arr) {
            if (cart.key.equals(key))
                return cart.data;
        }
        return null;
    }

    /**
     * Gets the proper index to use when inserting / querying this map. The "proper"
     * index needs to be calculated from a given object's hash code, then the remainder
     * needs to be taken to fit within the given length of the map.
     *
     * @param key given key
     * @return index from hash code
     */
    private int getHashIndex(String key) {
        return Math.abs(key.hashCode()) % length;
    }

    public static void main(String[] args) {
        Dictionary<Integer> dic = new Dictionary<>();
        dic.set("hithere", 2);
        Integer i = dic.get("hithere");
        System.out.println(i);
        dic.set("hithere", 3);
        System.out.println(dic.get("hithere"));
    }
}
