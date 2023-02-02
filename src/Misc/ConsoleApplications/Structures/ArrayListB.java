package Misc.ConsoleApplications.Structures;

/**
 * Represents a list that is a managed array of objects.
 *
 * @param <T> type of data to be stored
 */
public class ArrayListB<T> {

    private static final int RECOMMENDED_LENGTH = 50;

    private Object[] arr;
    private int size;
    private int arrLength;

    /**
     * Constructor for ArrayListB.
     *
     * @param length manual starting length of array list.
     */
    public ArrayListB(int length) {
        arr = new Object[length];
        arrLength = length;
    }

    /**
     * Constructor for ArrayListB, using the Recommended starting length.
     */
    public ArrayListB() {
        this(RECOMMENDED_LENGTH);
    }

    /**
     * Adds a given Object to this array list.
     *
     * @param data given Object
     */
    public void add(T data) {
        if (size + 1 >= arrLength) {
            arrLength *= 2;
            Object[] re = new Object[arrLength];
            System.arraycopy(arr, 0, re, 0, size);
            arr = re;
        }
        arr[size++] = data;
    }

    /**
     * Removes the given Object from this array list.
     *
     * @param data given Object
     */
    public void remove(T data) {
        int index = 0;
        for (Object o : arr) {
            if (o == data)
                remove(index);
            index++;
        }
    }

    /**
     * Removes the Object at the given index from this array list.
     *
     * @param index given index
     */
    public void remove(int index) {
        arr[index] = null;
        size--;
    }

    /**
     * Gets the Object at the given index.
     *
     * @param index given index
     *
     * @return Object at index
     */
    public Object get(int index) {
        return arr[index];
    }

    /**
     * Gets the current size of this list.
     *
     * @return size of this list
     */
    public int size() {return size;}

    /**
     * Gets a string representation of this list, and its current elements.
     * this string comes in the form "[..., ..., ...]"
     *
     * @return string representation of this list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int c = size;

        int i = 0;
        while (c != 0) {
            if (arr[i] == null) {
                i++;
                continue;
            }
            if (c == 1)
                sb.append(String.format("%s]", arr[i].toString()));
            else
                sb.append(String.format("%s, ", arr[i].toString()));
            c--;
            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayListB<Integer> list = new ArrayListB<>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.remove(2);
        System.out.println(list);
    }
}
