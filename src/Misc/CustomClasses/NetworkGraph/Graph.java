package Misc.CustomClasses.NetworkGraph;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Jacob Swineford
 */
public class Graph {

    private static ArrayList<Node> nodes;

    private static String nodeRepresentation(LinkedList<Node> nodes) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < nodes.size(); i++) {
            if (i != nodes.size() - 1) {
                sb.append(nodes.get(i).getName());
                sb.append(", ");
            } else {
                sb.append(nodes.get(i).getName());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private static void traverse(Node from, Node to, LinkedList<Node> traversed) {
        if (traversed.contains(from)) return;

        traversed.add(from);
        if (from == to) {
            System.out.println(nodeRepresentation(traversed));
            traversed.remove(from);
            return;
        }

        LinkedList<Edge> neighbors = from.getNeighbors();
        for (Edge e : neighbors)
            traverse(e.getConnected(), to, traversed);
        traversed.remove(from);
    }

    private static void printHeader() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nodes.size() + 3; i++){
            sb.append(" ");
        }
        sb.append("|");
        System.out.print(sb.toString());
        for (Node n : nodes) {
            System.out.printf(" %-4s |", n);
        }
        System.out.println();
    }
    private static void printRow(LinkedList<Node> used, HashMap dist) {
        System.out.printf("%-2s|", Integer.toString(used.size()));
        StringBuilder sb = new StringBuilder();
        for (Node n : used) sb.append(n);

        String format = "%-" + nodes.size() + "s|";
        System.out.printf(format, sb.toString());
        for (Node n : nodes) {
            String str = "    ";
            if (dist.get(n) != null) {
                str = dist.get(n).toString();
            }
            System.out.printf(" %-4s |", str);
        }
        System.out.println();
    }
    private static void DijkstraShortestPath(Node from) {
        LinkedList<Node> used = new LinkedList<>();
        LinkedList<Node> unused = new LinkedList<>(nodes);
        HashMap<Node, Pair<Node, Integer>> dist = new HashMap<>();

        unused.remove(from);
        used.add(from);
        for (Edge e : from.getNeighbors())
            dist.put(e.getConnected(), new Pair<>(from, e.getValue()));
        dist.put(from, new Pair<>(from, 0));
        printHeader();
        printRow(used, dist);

        while (used.size() != nodes.size()) {
            // find distance to node such that it is a minimum and not in used
            Node minNode = null;
            Integer min = null;
            for (Node n : unused) {
                if (dist.get(n) == null) continue;
                Integer m = dist.get(n).getValue();
                if (min == null && m != null) {
                    min = m;
                    minNode = n;
                } else if (m != null && m <= min) {
                    minNode = n;
                    min = m;
                }
            }
            assert minNode != null;

            // use the minNode to update the map of minimum values
            for (Edge e : minNode.getNeighbors()) {
                Integer val =  dist.get(minNode).getValue() + e.getValue();
                if (dist.get(e.getConnected()) == null) {
                    dist.put(e.getConnected(), new Pair<>(minNode, val));
                } else if (dist.get(e.getConnected()).getValue() > val) {
                    dist.put(e.getConnected(), new Pair<>(minNode, val));
                }
            }
            used.add(minNode);
            unused.remove(minNode);

            printRow(used, dist);
        }
    }

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        Node z = new Node('z');
        Node y = new Node('y');
        Node x = new Node('x');
        Node v = new Node('v');
        Node t = new Node('t');
        Node u = new Node('u');
        Node w = new Node('w');

        nodes.add(z);
        nodes.add(y);
        nodes.add(x);
        nodes.add(v);
        nodes.add(t);
        nodes.add(u);
        nodes.add(w);
        z.add(new Edge(8, x), new Edge(12, y));
        y.add(new Edge(12, z), new Edge(6, x), new Edge(8, v), new Edge(7, t));
        x.add(new Edge(8, z), new Edge(6, w), new Edge(6, y), new Edge(3, v));
        w.add(new Edge(6, x), new Edge(4, v), new Edge(3, u));
        u.add(new Edge(3, w), new Edge(2, t), new Edge(3, v));
        t.add(new Edge(2, u), new Edge(4, v), new Edge(7, y));
        v.add(new Edge(8, y), new Edge(3, x), new Edge(4, w), new Edge(3, u), new Edge(4, t));
        DijkstraShortestPath(t);
        System.out.println();
        DijkstraShortestPath(u);
        System.out.println();
        DijkstraShortestPath(v);
        System.out.println();
        DijkstraShortestPath(w);
        System.out.println();
        DijkstraShortestPath(y);
        System.out.println();
        DijkstraShortestPath(z);
    }
}
