
import java.util.LinkedList;

/**
 * An ordered LinkedList of type T.
 *
 * T must implement Comparable to be compared with
 * other elements in this list, as elements are ordered
 * according to their natural ordering.
 *
 * @author Jacob Swineford
 */
public class OrderedList<T extends Comparable<T>>
{

    private LinkedList<T> data;
    private int size;

    public OrderedList()
    {
        data = new LinkedList<>();
        size = 0;
    }

    /**
     * Removes the first instance of the given element from this list.
     * @param e removed element T
     */
    public void remove(T e)
    {
        data.remove(e);
        size--;
    }

    /**
     * Returns a copy of the k-th element.
     *
     * @param k k-th index
     * @return element at l-th location
     * @throws IndexOutOfBoundsException if k < 0 || k >= size
     */
    public T get(int k) throws IndexOutOfBoundsException
    {
        return data.get(k);
    }

    /**
     * Returns an OrderedList containing the k largest values in this list.
     * @param k number of elements
     * @return OrderedList containing k largest elements
     */
    public OrderedList kLargest(int k)
    {
        OrderedList<T> r = new OrderedList<>();
        for (int i = size - k; i < size; i++)
        {
            r.insert(data.get(i));
        }
        return r;
    }

    /**
     * Inserts the given element T into it's correct position in this list.
     * @param e given element T
     */
    public void insert(T e)
    {
        if (size == 0) {
            data.add(e);
            size++;
            return;
        }

        for (int i = 0; i < size; i++) {
            if (e.compareTo(data.get(i)) < 0 || e.compareTo(data.get(i)) == 0) {
                data.add(i, e);
                size++;
                return;
            }
        }
        data.add(e);
        size++;
    }

    /**
     * Adds the given list of type T to this list.
     *
     * @param aList appended list
     * @return OrderedList resulting from appended lists
     */
    public OrderedList add(OrderedList<T> aList)
    {
        int t = 0; // this list index
        int a = 0; // aList index

        OrderedList<T> r = new OrderedList<>();
        for (int n = 0; n < aList.size + this.size; n++) // n = new list index
        {
            try
            {
                T t1 = this.data.get(t);
            } catch (IndexOutOfBoundsException e)
            {
                // aList has data
                r.data.add(aList.data.get(a));
                r.size++;
                a++;
                continue;
            }

            try
            {
                T t2 = aList.data.get(a);
            } catch (IndexOutOfBoundsException e)
            {
                // this list has data
                r.data.add(this.data.get(t));
                r.size++;
                t++;
                continue;
            }

            T t1 = this.data.get(t);
            T t2 = aList.data.get(a);
            if (t1.compareTo(t2) > 0)
            {
                r.data.add(t2);
                r.size++;
                a++;
            } else
                {
                    r.data.add(t1);
                    r.size++;
                    t++;
                }
        }
        return r;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++)
        {
            if (i != size - 1)
            {
                String str = data.get(i) + ", ";
                sb.append(str);
                continue;
            }
            sb.append(data.get(i));
        }
        return sb.append("]").toString();
    }

    public int size() {
        return size;
    }
}
