package Misc.ConsoleApplications.Structures;

import java.util.LinkedList;

/**
 * Represents a type of list that contains a collection of nodes that
 * are linked to each other. These nodes store the data associated with
 * their position, and a reference to it's linked node (next node).
 *
 * @param <T> type of data to be stored
 */
public class LinkedListB<T> {

    /**
     * Represents a Node that is used within a LinkedListB.
     *
     * @param <T> type of data to be stored
     */
    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) {this.data = data;}
    }

    private Node<T> head;
    private int size;

    /**
     * Constructor that initializes the head of this list.
     *
     * @param headData given data to store
     */
    public LinkedListB(T headData) {
        head = new Node<>(headData);
    }

    /**
     * Constructor for this list.
     */
    public LinkedListB() { }

    /**
     * Appends the given data to the end of this list.
     *
     * @param data given data
     */
    public void add(T data) {
        if (head == null)
            head = new Node<>(data);
        else {
            Node<T> cur = head;
            while (cur.next != null)
                cur = cur.next;
            cur.next = new Node<>(data);
        }
        size++;
    }

    /**
     * Removes the given data from this list.
     *
     * @param data given data
     */
    public void remove(T data) {
        Node<T> cur = head;
        int i = 0;
        while (cur != null) {
            if (cur.data == data)
                remove(i);
            cur = cur.next;
            i++;
        }
    }

    /**
     * Removes the data stored in this list at the given index.
     *
     * @param index index used for removal
     */
    public void remove(int index) {
        if (size == 0) return;
        Node<T> before = head;
        Node<T> cur = head;
        while (cur != null) {
            if (index == 0 && cur == head) {
                head = head.next;
                size--;
                return;
            } else if (index == 0) {
                before.next = cur.next;
                size--;
                return;
            }
            before = cur;
            cur = cur.next;
            index--;
        }
    }

    /**
     * Overridden toString() method, that will return a string representation
     * of this list, in the form "[..., ..., ...]".
     *
     * @return string representation if this list
     */
    @Override
    public String toString() {
        Node<T> cur = head;
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1)
                sb.append(String.format("%s]", cur.data.toString()));
            else
                sb.append(String.format("%s, ", cur.data.toString()));
            cur =  cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListB<Integer> list = new LinkedListB<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.remove(4);
        System.out.println(list);
        System.out.println(list.size);
    }
}
