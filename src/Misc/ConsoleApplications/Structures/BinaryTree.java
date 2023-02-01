package Misc.ConsoleApplications.Structures;

/**
 * Represents a data structure called a binary tree.
 * <p>
 * A binary tree is a collection of nodes that each contain references
 * to left and right nodes. these nodes contain the values inserted into a tree.
 *
 * @param <T> type of data that extends Comparable
 */
public class BinaryTree<T extends Comparable<T>> {

    /**
     * Represents a Node meant to be used in this binary tree.
     *
     * @param <T> Object that extends Comparable
     */
    private static class Node<T extends Comparable<T>> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size;

    /**
     * Adds a value to this binary tree.
     *
     * @param val value to add
     *
     * @return the new node containing the given value
     */
    public Node<T> add(T val) {
        if (head == null) {
            head = new Node<>(val);
            size++;
            return head;
        }
        return add(val, head, head);
    }

    /**
     * Adds a value to this binary tree. This method contains the recursive
     * algorithm to insert values into their proper position within this tree.
     * <p>
     * The value that is inserted is put to the left of the current node if it
     * is less than the value contained by the current node. otherwise, it is put to the
     * right. This process is continued until a null node is hit, which means that
     * a new node is created and inserted into position.
     *
     * @param val value to inert
     * @param cur current node, checked for insertion
     * @param parent parent node to current node
     *
     * @return the new node containing the given value
     */
    private Node<T> add(T val, Node<T> cur, Node<T> parent) {
        if (cur == null) {
            cur = new Node<>(val);
            if (val.compareTo(parent.data) < 0)
                parent.left = cur;
            else
                parent.right = cur;
            size++;
            return cur;
        }

        boolean left = val.compareTo(cur.data) < 0;
        if (left)
            return add(val, cur.left, cur);
        else
            return add(val, cur.right, cur);
    }

    /**
     * Perform and print a Pre-Order Traversal for this tree.
     *
     * @param target origin node
     */
    public void preOrderTraversal(Node<T> target) {
        if (target == null) return;
        printNode(target);
        preOrderTraversal(target.left);
        preOrderTraversal(target.right);
    }

    /**
     * Perform and print an In-Order Traversal for this tree.
     *
     * @param target origin node
     */
    public void inOrderTraversal(Node<T> target) {
        if (target == null) return;
        inOrderTraversal(target.left);
        printNode(target);
        inOrderTraversal(target.right);
    }

    /**
     * Perform and print a Post-Order Traversal for this tree.
     *
     * @param target origin node
     */
    public void postOrderTraversal(Node<T> target) {
        if (target == null) return;
        postOrderTraversal(target.left);
        postOrderTraversal(target.right);
        printNode(target);
    }

    /**
     * Prints information associated with the given node.
     *
     * @param target given node
     */
    private void printNode(Node<T> target) {
        if (target == null) return;
        System.out.printf("[%s]", target.data);
        if (target.left != null)
            System.out.printf(" {left: [%s]}", target.left.data);
        if (target.right != null)
            System.out.printf(" {right: [%s]}", target.right.data);
        System.out.println();
    }

    public Node<T> getHead() {return head;}
    public int getSize() {return size;}

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Node<Integer> head = tree.add(0);
        for (int i = 1; i < 5; i++)
            tree.add(i);
        for (int i = -1; i > -5; i--)
            tree.add(i);
        tree.add(-1);
        System.out.println("pre order traversal:\n");
        tree.preOrderTraversal(head);
        System.out.println("\npost order traversal\n");
        tree.postOrderTraversal(head);
        System.out.println("\nin order traversal\n");
        tree.inOrderTraversal(head);
    }
}
