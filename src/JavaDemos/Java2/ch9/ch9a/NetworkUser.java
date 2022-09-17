package JavaDemos.Java2.ch9.ch9a;

/**
 * An immutable network user with a login name and password.
 *
 * @author Jacob Swineford
 */
public class NetworkUser {

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
