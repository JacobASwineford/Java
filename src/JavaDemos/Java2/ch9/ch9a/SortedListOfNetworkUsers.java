package JavaDemos.Java2.ch9.ch9a;

import java.util.ArrayList;

/**
 * A list of network users stored in lexical order by login name.
 *
 * @author Jacob Swineford
 */
public class SortedListOfNetworkUsers {

    private ArrayList<NetworkUser> list;

    /**
     * Creates an empty list of network users.
     */
    public SortedListOfNetworkUsers() {
        list = new ArrayList<>();
    }

    /**
     * Returns the network user at a given position in the list.
     */
    public NetworkUser get(int i) {
        return list.get(i);
    }

    /**
     * Returns the number of network users in this list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds a network user to the list in its correct position wit hrespect to
     * login name.
     */
    public void add(NetworkUser user) {
        int i = 0; // position st which to insert
        String nameToAdd = user.getLogin();

        while (i < list.size()) {
            String nameInList = list.get(i).getLogin();
            if (nameToAdd.compareTo(nameInList) <= 0) {
                break;
            }
            i++;
        }
        list.add(i, user);
    }

    /**
     * Quick test of this class.
     */
    public static void main(String[] args) {
        NetworkUser n = new NetworkUser("pugs", "aa");
        NetworkUser n2 = new NetworkUser("mugs", "bbb");
        NetworkUser n3 = new NetworkUser("doug", "ccc");
        NetworkUser n4 = new NetworkUser("chug", "ddd");

        SortedListOfNetworkUsers s = new SortedListOfNetworkUsers();
        s.add(n);
        s.add(n2);
        s.add(n3);
        s.add(n4);

        // traverse the list and output the names
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i));
        }


    }

}

