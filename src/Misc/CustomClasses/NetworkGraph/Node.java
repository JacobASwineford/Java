package Misc.CustomClasses.NetworkGraph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class Node {
    private char name;
    private LinkedList<Edge> neighbors;

    Node(char name) {
        this.name = name;
        neighbors = new LinkedList<>();
    }

    void add(Edge... toAdd) {
        neighbors.addAll(Arrays.asList(toAdd));
    }

    @Override
    public String toString() {
        return name + "";
    }

    LinkedList<Edge> getNeighbors() {return neighbors;}
    char getName() {return name;}
}
