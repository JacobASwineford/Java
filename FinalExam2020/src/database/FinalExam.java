
package database;

import finalExam.FinalExam2020;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import security.SecurityCode;

/**
 * I can guarantee that the Question 2 is correct. Other than that, I just blindly wrote code that might (or might not)
 * solve the problem. If I had more time, here is what I would do: (stop when you find the right solution)
 * 1. check if the hashed password is associated with someone in the database. if so, The salt can be deduced by the userManager class,
 *    and the password can be figured out with the methods I wrote during the take home part of the final.
 * 2. if the salt cannot be found, assume it is not there. check the commonPasswords or dictionary for the password.
 * 3. no salt, check by force
 * 4. with random salt, check by force
 * @author Jacob Swineford
 */
public class FinalExam {
  public static void main(String []args) throws Exception {
     //Place your code for the in class final exam here 
     //Question 2 code goes here
        MySQL sql = new MySQL(); 
        Connection c = sql.getConnection();
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("select userPassword, salt from users where loginName = 'cjones'");
        System.out.println("Worked!");
        while (r.next()) {
            System.out.println(r.getString("userPassword"));
            System.out.println(r.getString("salt"));
        }


     //Question 3 code goes here
     // im blindly believing that this will work
     UserManager userManager = new UserManager();
     r = s.executeQuery("select loginName frim users where loginName = 'cjones'");
     String salt = "";
     while (r.next()) {
         salt = userManager.getSaltByLoginName(r.getString("loginName"));
     }
     System.out.println("Password: " + FinalExam2020.determineOriginalStringWithSaltromFiles(
             "a5d0f757c580081f2b4ab6e5e47ab39bdcdb131b2f4a14d4", salt));
     
     
     //Question 4 code goes here
     // I didn't find this is commonPasswords or Dictionary, so i'm blindly believing that this will work
     int length = 6;
     String password = FinalExam2020.determineOriginalStringByBruteForce("382132701c4733c3402706cfdd3c8fc7f41f80a88dce5428d145259a41c5f12f", length);
     System.out.println(password);
     
     //Question 5 code goes here
     // I didn't find this is commonPasswords or Dictionary, so i'm blindly believing that this will work
     length = 6;
     password = FinalExam2020.determineOriginalStringByBruteForce("4787c556561d88a063a67e8e0dd494b852b44bc1", length);
     System.out.println(password);
    }        
}