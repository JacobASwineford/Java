import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An ordered LinkedList of type T.
 *
 * T must implement Comparable to be compared with
 * other elements in this list, as elements are ordered
 * according to their natural ordering.
 *
 * This is different from OrderedList<T> in that this version
 * orders and maintains nodes directly. Unfortunately, I couldn't
 * get this to work. If you want to look at the version that works,
 * look at OrderedList instead. As an alternative, I will lay out the logic
 * of each method in a comment.
 *
 * I will leave my old code here for others to review.
 *
 * @author Jacob Swineford
 */
public class OrderedList2<T extends Comparable<T>>
{

    // LinkedLists have references to the first and last node
    private Node<T> first;
    private Node<T> last;
    private int size;

    public OrderedList2()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Removes the first instance of the given element from this list.
     *
     * This removes an element and it's node from the list by assigning
     * the node behind it to the node in front of it. This is fairly simple,
     * and is cost efficient for removal compared to ArrayLists.
     *
     * Special cases:
     * element equals first node element in list - reduce size and reassign first to
     *  the node that's in front of it.
     * element is not in this list - nothing is removed and size is not reduced.
     *
     * @param e removed element T
     */
    public void remove(T e)
    {
//        // special case
//        if (e.equals(first.getData())) {
//            first = first.getLink();
//            size--;
//            return;
//        }
//
//        Node n1 = first; // node before n2
//        Node n2 = first; // item to be removed
//        while (n2 != null) {
//            if (n2.getData().equals(e)) {
//                if (n2 == last) {
//                    last = n1;
//                }
//                n1.setLink(n2.getLink());
//                break;
//            }
//
//            n1 = n2;
//            n2 = n2.getLink();
//        }
//
//        // element is not in this list
//        if (n2 == null) {
//            return;
//        }
//        size--;
    }

    /**
     * Returns a copy of the k-th element.
     *
     * First the variable is checked to see if it is within range. If
     * it is not, then an IndexOutOfBounds Exception will be thrown.
     * Then, starting from the first node, the node advances k spaces
     * (first inclusive) until the desired node is reached and the data
     * is returned.
     *
     * No special cases.
     *
     * @param k k-th index
     * @return element at l-th location
     * @throws IndexOutOfBoundsException if k < 0 || k >= size
     */
    public T get(int k) throws IndexOutOfBoundsException
    {
//        if (k >= size || k < 0) {
//            throw new IndexOutOfBoundsException();
//        }
//
//        Node<T> n = first;
//        for (int i = 0; i < k; i++) {
//            n = n.getLink();
//        }
//        return n.getData();
        return first.getData();
    }

    /**
     * Returns an OrderedList containing the k largest values in this list.
     *
     * This can be written multiple ways. I would imagine the most efficient
     * way is assigning the first and last node of the new OrderedList<T>
     * (Which is not the method listed in the code). By advancing size - k
     * nodes the first node can be assigned, then, that node chain is to be advanced
     * until there is a node with link == null. This is the last link ans can
     * be assigned to the new OrderedList<T>.
     *
     * Special cases:
     * k >= size - a copy of the current OrderedList<T> is returned.
     *
     * @param k number of elements
     * @return OrderedList containing k largest elements
     */
    public OrderedList2 kLargest(int k)
    {
//        OrderedList2<T> r = new OrderedList2<>();
//        Node<T> current = first;
//        int c = 0;
//        while (current != null) {
//            if (c >= size - k) {
//                r.insert(current.getData());
//            }
//            current = current.getLink();
//            c++;
//        }
//        return r;
        return new OrderedList2();
    }

    /**
     * Inserts the given element T into it's correct position in this list.
     *
     * This is the method that I could not figure out. In the code, there
     * is a loop that iterates over all nodes in this list. Upon the
     * inclusion of the if statement, some output would result in an infinite
     * loop. I couldn't figure out the nature of this behavior, and it prevented
     * me from testing other methods.
     *
     * A reference to the current node and the node behind it is necessary for this
     * operation. if the given element returns negative or 0 when comparing to the current
     * element, then the given element is inserted before the compared element
     * via a new node, and the element behind is sets it's link to the given element's node.
     *
     * Special cases:
     * no elements in list - first is initialized with a new node with link mull and element.
     *  size is incremented and last is assigned to first.
     * element is not in this list - nothing is inserted and size is not incremented.
     * element is bigger than all other elements - last is assigned to new node containing
     *  element, last is then assigned to new node
     *
     *
     * @param e given element T
     */
    public void insert(T e)
    {
//        if (size == 0) {
//            first = new Node<>(e, null);
//            last = first;
//            size++;
//            return;
//        }
//
//
          // Causes infinite loop sometimes?
//        Node<T> n1 = first;
//        Node<T> n2 = first;
//        while (n2 != null) {
//            if (e.compareTo(n2.getData()) <= 0) {
//                Node<T> n3 = new Node<>(e, n2);
//                n1.setLink(n3);
//                size++;
//                return;
//            }
//            n1 = n2;
//            n2 = n2.getLink();
//        }
//
//        if (e.compareTo(last.getData()) > 0) {
//            Node<T> n3 = new Node<>(e, null);
//            n1.setLink(n3);
//            last = n3;
//            size++;
//        }

    }

    /**
     * Adds the given list of type T to this list.
     *
     * This method checks the first node's element of each list to
     * see which one is bigger. The smaller one gets assigned to be the
     * first node in the returned OrderedList<T>. The list that contained
     * the smaller node moves on to it's next element, and the two "front"
     * nodes are compared again. It gets assigned to the first node of returned,
     * and the node chain keeps getting built up like this until either of
     * the lists are empty. From there the front node of the remaining list is
     * added and the last node of the remaining list is set = to last for the returned
     * list.
     *
     * Special cases:
     * if one list (this one or the given one) is empty - return the other list
     *
     * @return OrderedList resulting from appended lists
     */
    public OrderedList2 add(OrderedList2<T> aList)
    {
        return new OrderedList2();
    }

    @Override
    public String toString() {
        String string = "[";
        Node current = first;
        int c = 0;
        while (current != null) {
            if (c == size - 1) {
                string += current.getData() + ", ";
            }
            current = current.getLink();
            c++;
        }
        string += "]";
        return string;
    }
}
