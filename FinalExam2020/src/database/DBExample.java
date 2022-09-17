package database;

import security.SecurityCode;

/**
 *
 * @author cjones
 */
public class DBExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        String loginName = "user1";
        String salt =   userManager.getSaltByLoginName(loginName);
        String password = SecurityCode.encryptSHA256("King1234"+salt);
        User user = userManager.validateUser(loginName, password);
        if(user == null) System.out.println("Did not work");
        else System.out.println("The user is "+user);
        
    }
    
}
