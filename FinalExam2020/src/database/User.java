
package database;

import java.io.Serializable;


/**
 * Representation of a generic user of the wait list.
 *
 * @author cjones
 */
public class User implements Comparable<User>, Serializable {

    private int userNumber; //Primary key in our database --auto created
    private String loginName;
    private String userPassword;
    private String salt;
    private String lastName;
    private String firstName;
    private String emailAddress;

    /**
     * Constructs an empty user.
     */
    public User() {
    }

    /**
     * Constructs a new user with given properties such as a user number, login
     * name, password, last name, first name, email address, department, user
     * role, last login, and login count.
     *
     * @param userNumber A number that represents this user. This number is the
     * primary key in our database and is auto incremented.
     *
     * @param loginName The name that this user uses to login to the system.
     *
     * @param password The password that this user uses to login to the system.
     *
     * @param salt The salt used to hash the password for this user
     *
     * @param lastName The last name of this user.
     *
     * @param firstName The first name of this user.
     *
     * @param emailAddress The university email of this user.
      */
    public User(int userNumber, String loginName, String password, String salt, String lastName, String firstName, String emailAddress) {
        this.userNumber = userNumber;
        this.loginName = loginName;
        this.userPassword = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.salt = salt;
    }

   
    /**
     * Gets the email address of this user.
     *
     * @return The email address of this user. If there is no email address null
     * is returned.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address for this user. No error checking is performed.
     *
     * @param emailAddress The email address that is set for this user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the user number of this user. This is the unique identifier for this
     * user.
     *
     * @return The user number of this user. If there is no user number null is
     * returned.
     */
    public int getUserNumber() {
        return userNumber;
    }

    /**
     * Sets the user number for this user. No error checking is performed.
     *
     * @param userNumber The user number that is set for this user.
     */
    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    /**
     * Gets the last name of this user.
     *
     * @return The last name of the user. If no last name exists null is
     * returned.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of this user. No error checking is performed.
     *
     * @param lastName The last name that is set to this user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the first name of this user.
     *
     * @return The first name of this user. If no first name exists null is
     * returned.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of this user. No error checking is performed.
     *
     * @param firstName The first name that is set to this user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

       /**
     * Gets the login name of this user.
     *
     * @return The login name of this user. If there is no login name null is
     * returned.
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Sets the login name of this user. No error checking is performed.
     *
     * @param loginName The login name that is set for this user.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Gets the password for the login of this user.
     *
     * @return The password for this user. If no password exists null is
     * returned.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the password for the login of this user. No error checking is
     * performed.
     *
     * @param userPassword The password that is set for this user.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the salt for the login of this user.
     *
     * @return The salt for this user. If no password exists null is returned.
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt for the login of this user. No error checking is performed.
     *
     * @param salt The salt that is set for this user.
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Returns the hash value of this user. Only the user number is used 
     * to construct the hash value.
     *
     * @return The hash value of this user number.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.userNumber;
        return hash;
    }

    /**
     * Overrides the equals method to compare two <code> User </code>s. If the
     * object parameter is not a <code> User </code> or is null return false.
     * Otherwise, if the <code> User </code> is equal to this <code> User
     * </code>, return true.
     *
     * @param obj The <code> User </code> object to be compared to.
     * @return Whether or not the <code> User </code> given is equal to this <code> User
     * </code>.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final User other = (User) obj;
        return this.userNumber == other.userNumber;
    }

    /**
     * Returns the order of a <code> User </code> compared to this <code> User
     * </code>.
     *
     * @param o The <code> User </code> to compare to this <code> User </code>.
     * @return The order of the given <code> User </code> relative to this <code> User
     * </code>. Positive for before, zero for equal, and negative for after.
     */
    @Override
    public int compareTo(User o) {
        int order = lastName.compareToIgnoreCase(o.lastName);
        if (order != 0) {
            return order;
        }
        order = firstName.compareToIgnoreCase(o.firstName);
        return order;
    }

    @Override
    public String toString() {
        return "User{" + "userNumber=" + userNumber + ", loginName=" + loginName
                + ", userPassword=" + userPassword + ", salt=" + salt
                + ", lastName=" + lastName
                + ", firstName=" + firstName + ", emailAddress=" + emailAddress
                + '}';
    }

}

