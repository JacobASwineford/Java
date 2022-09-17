package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import security.SecurityCode;

/**
 *
 * @author 
 */
public class FinalExamQuestion15 {
  public static void main(String []args) throws SQLException{
                MySQL sql = new MySQL(); 
        Connection c = sql.getConnection();
        Statement s = c.createStatement();
        String username = "jswineford";
        String salt = SecurityCode.generateSalt();
        String password = "AssWd";
        String first = "Jacob";
        String last = "Swineford";
        String email = "jas47367@huskies.bloomu.edu";
        s.execute("create table if not exists swineford ("
                + "username varchar(30),"
                + "userpassword varchar(30),"
                + "salt varchar(50),"
                + "firstname varchar(30),"
                + "lastname varchar(30),"
                + "email varchar(30));");
        System.out.println("Database created!");
        s.execute("insert into swineford values ('"
                + username + "','"
                + password + "','"
                + salt + "','"
                + first + "','"
                + last + "','"
                + email + "');");
        ResultSet results = s.executeQuery("select * from swineford");
        while (results.next()) {
            System.out.println(results.getString("username"));
            System.out.println(results.getString("userpassword"));
            System.out.println(results.getString("salt"));
            System.out.println(results.getString("firstname"));
            System.out.println(results.getString("lastname"));
            System.out.println(results.getString("email"));
        }
        
        MYSQL_Helper.closeStatement(s);
        sql.closeConnection(c);

    }        
}
