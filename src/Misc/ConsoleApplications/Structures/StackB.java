package Misc.ConsoleApplications.Structures;

/**
 * Represents a stack of objects.
 *
 * @param <T> type of data to be stored
 */
public class StackB<T> {

    private final ArrayListB<T> stack;

    /**
     * Constructor for StackB.
     */
    public StackB() {
        stack = new ArrayListB<>();
    }

    /**
     * Pushes the given object onto this stack.
     *
     * @param data given object
     */
    public void push(T data) {
        stack.add(data);
    }

    /**
     * Pops the object off the top of this stack. This does
     * remove the object from the stack.
     *
     * @return the top object of the stack
     */
    public Object pop() {
        int working = stack.size() - 1;
        Object data = stack.get(working);
        stack.remove(working);
        return data;
    }

    /**
     * Peeks at the object on top of this stack. This does not
     * remove the object from the stack.
     *
     * @return the top object of the stack
     */
    public Object peek() {
        return stack.get(stack.size() - 1);
    }

    /**
     * Gets a string representation of this stack, and its current elements.
     * This string comes in the form "{counter}: [...]".
     *
     * @return string representation of this stack
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 1;
        for (int i = stack.size() - 1; i > -1; i--)
            sb.append(String.format("%2d: [%s]\n", counter++, stack.get(i).toString()));
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) {
+

        StackB<Integer> stack = new StackB<>();
        for (int i = 0; i < 10; i++)
            stack.push(i);
        System.out.println("peeked element: " + stack.peek());
        int p = (int) stack.pop();
        System.out.println("popped element: " + p);
        System.out.println(stack);
    }
}
