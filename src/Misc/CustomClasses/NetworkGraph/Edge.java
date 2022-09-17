package Misc.CustomClasses.NetworkGraph;

/**
 * An edge with a value that connects one node to another.
 *
 * @author Jacob Swineford
 */
public class Edge {
    private int value;
    private Node connected;

    Edge(int value, Node toConnect) {
        this.value = value;
        connected = toConnect;
    }

    int getValue() {return value;}
    Node getConnected() {return connected;}
}
