package JavaDemos.Java2.ch9.ch9b;

/**
 * An immutable network user with a login name and password.
 *
 * @author Jacob Swineford
 */
public class NetworkUser implements Comparable {

    private final String login;
    private final String password;

    /**
     * Constructs a network user with a given login name and password.
     */
    public NetworkUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Returns a negative integer, zero, or a positive integer depending on
     * whether this network user's login is less than, equal to, or greater than
     * the login of the given network user in ASCII order.
     */
    @Override
    public int compareTo(Object o) {
        NetworkUser n = (NetworkUser) o;
        return login.compareTo(n.login);
    }

    /**
     * Returns this network user's login name.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns this network user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the concatenation of this user's name and password.
     */
    @Override
    public String toString() {
        return login + " (" + password + ")";
    }
}

