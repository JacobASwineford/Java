/**
 * Node class used to store data for OrderedList2<T>.
 *
 * @author Jacob Swineford
 */
public class Node<T> {`x`

    private T data;
    private Node link;

    public Node(T data, Node link) {
        this.data = data;
        this.link = link;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
}
