package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author cjones
 */
public class CreateTables {
   
    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String []args) throws SQLException{
        
        //First -- Get a connection to the database
      //  Connection connection = MYSQL_Helper.getConnection();
        MySQL mySQL = new MySQL(); 
        Connection connection = mySQL.getConnection();
        //Get a statement from the connection
        Statement statement = connection.createStatement();
        
        String sqlString = "CREATE TABLE IF NOT EXISTS users (" +
            "userNumber INT NOT NULL UNIQUE AUTO_INCREMENT," +
            "loginName VARCHAR(60) NOT NULL UNIQUE," +
            "userPassword VARCHAR(80) NOT NULL," +
            "firstName VARCHAR (30)DEFAULT ''," +
            "lastName VARCHAR (40)DEFAULT ''," +
            "emailAddress VARCHAR(50)DEFAULT ''," +
            "salt VARCHAR(50)," +
            "PRIMARY KEY (userNumber)" +
            ");" ;
        
    //    statement.execute("DROP TABLE IF EXISTS users;");  
        
        
        statement.execute(sqlString);
        
        String insertStatement = "INSERT INTO users (loginName, userPassword,firstName,lastName,emailAddress,salt)" + " VALUES " + 
          "('user1','65b5cd782d62cf7620b2d31ffbe8f2acbcbe1ab74312a393e045d7dc2d5fc1f3','User1','User1','user1@bloomu.edu', '61nhmut8majvcoefp1bnp00ai5')," +
          "('user2','bf5471cfcc292d50229311dddce3baf0034d848c1a17532ae5144cb6dc9a8e24','User2','User2','user2@bloomu.edu','egnvmvnn9aajv6f2caia0hpg16')" +
          " ;";
        
        statement.execute(insertStatement);
          
        MYSQL_Helper.closeStatement(statement);
        mySQL.closeConnection(connection);
    }        
}
