
/**
 * A list that holds Objects internally using an array.
 * MyArrayList is a class meant to imitate the ArrayList class
 * in the Java class library.
 *
 * @author Jacob Swineford
 */
public class MyArrayList {

    private Object[] data;
    private int size;

    private static final int DEFAULT_LENGTH = 10;

    public MyArrayList()
    {
        this(DEFAULT_LENGTH);
    }

    public MyArrayList(int l)
    {
        data = new Object[l];
        size = 0;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e appended element
     */
    public void add(Object e)
    {
        potentiallyLengthenDataPool();
        data[size] = e;
        size++;
    }

    /**
     * Inserts the specified element at the specified position of
     * this list.
     *
     * @param index specified position
     * @param element Inserted element
     */
    public void add(int index, Object element) throws IndexOutOfBoundsException
    {
        potentiallyLengthenDataPool();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear()
    {
        data = new Object[DEFAULT_LENGTH];
        size = 0;
    }

    /**
     * Returns true if this list contains the specified element.
     */
    public boolean contains(Object element)
    {
        for (Object obj : data)
        {
            if (obj.equals(element))
            { // same addresses mean same object
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index specified position
     * @throws IndexOutOfBoundsException index exceeds data array length
     */
    public Object get(int index) throws IndexOutOfBoundsException
    {
        if (index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    /**
     * Returns the index of the first occurrence of the specified
     * element in this list, or -1 if this list does not contain
     * the element.
     *
     * @param element specified element
     */
    public int indexOf(Object element)
    {
        int c = 0;
        for (Object obj : data)
        {
            if (obj.equals(element))
            {
                return c;
            }
            c++;
        }
        return -1;
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index specified index
     */
    public void remove(int index)
    {
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        data[size - 1] = null;
        size--;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present. Returns false if the element is not in this list.
     *
     * Note - removing the object from this ArrayList's data is a side effect.
     * Use this method with caution, as misuse might result in unintentional loss
     * of data.
     *
     * @param element specified element
     * @return true/false based on whether this list contains the specified object
     */
    public boolean remove(Object element)
    {
        // find index of object then reenact the other remove method
        boolean b = false;
        int index = 0;
        for (Object obj  : data)
        {
            if (obj.equals(element))
            {
                b = true;
                break;
            }
            index++;
        }
        this.remove(index);

        return b;
    }

    /**
     * Replaces the element at the position i in this list with the
     * specified element.
     *
     * @param index specified index
     * @param element replacing element
     * @throws IndexOutOfBoundsException index exceeds data array length
     */
    public void set(int index, Object element) throws IndexOutOfBoundsException
    {
        if (index >= size)
        {
            throw new IndexOutOfBoundsException();
        }
        data[index] = element;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return size variable of this list
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns a view of the portion of this list between the
     * specified fromIndex, inclusive, and toIndex, exclusive.
     *
     * @param fromIndex inclusive starting Index
     * @param toIndex exclusive ending index
     * @return subList of this list
     */
    public MyArrayList subList(int fromIndex, int toIndex)
    {
        MyArrayList myAL = new MyArrayList();
        for (int i = fromIndex; i < toIndex; i++)
        {
            myAL.add(data[i]);
        }
        return myAL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            if (i != size - 1)
            {
                String str = data[i] + ", ";
                sb.append(str);
                continue;
            }
            sb.append(data[i]);
        }
        return sb.append("]").toString();
    }

    /**
     * Returns a boolean value based on whether the current size is equal
     * to the length of data.
     *
     * @return true if data is full, false otherwise
     */
    private boolean dataIsFull() {
        return data.length == size;
    }

    /**
     * Potentially lengthens the array that this ArrayList carries data in.
     * This is only necessary when the data array is full of data,
     * to preserve memory that would otherwise meaningless null references.
     */
    private void potentiallyLengthenDataPool() {
        if (dataIsFull())
        {
            Object[] copy = data;
            data = new Object[size * 2];
            System.arraycopy(copy, 0, data, 0, copy.length);
        }
    }
}
