
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages MySQL database connections. 
 * @author cjones
 */
public class MySQL {
  
    private static final String mysqlPrefix="jdbc:mysql://";
    private static final String hostname ="classdb.mads.bloomu.edu";
    private String databaseName = "CS360";
    private String databaseURL = mysqlPrefix+hostname +"/"+databaseName;
    private String userName = "csuser";
    private String password = "csuser";

    /**
     * Database connections are created using credentials provided in the property file.
     * These credentials may be encoded if the secret key file name is given.
     */        
    public  MySQL( ){

        initDB();
    }
    
    /**
     * Database connections are created using credentials provided in the parameter list.
     * @param databaseURL The database connection string 
                    (jdbc:mysql://classdb.mads.bloomu.edu/CS360).
     * @param userName The user name for this database connection. 
     * @param password The password for this database connection.
     */
    public  MySQL(String databaseURL, String userName, String password ){
        this.databaseURL = databaseURL;
        this.userName = userName;
        this.password = password;
        initDB();
    }

    /**
     * Ensures that we can connect to the database given the current connection parameters. 
     */
    private void initDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //Not needed for MySQL - here for show only
        } catch (ClassNotFoundException ex) {
           System.out.println( "Could not find the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
           System.exit(1);
        } catch (InstantiationException ex) {
              System.out.println("Could not instaniate the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
           System.exit(1);
        } catch (IllegalAccessException ex) {
           System.out.println( "Could not access the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
           System.exit(1);
        }
        try {
           Connection conn = DriverManager.getConnection(databaseURL,userName,password);
           conn.close();
        } catch (SQLException ex) {
            System.out.println("Could not connect to the database. "
                    + "Database string = "+
                    databaseURL+ " user =  "+userName + " password = "+password);
            System.exit(1);
        }
    }
    
     /**
     * Returns a connection to the database. Connections are not reused. 
     * Each connection should be closed after use. 
     * 
     * @return A <code>Connection</code> to the database.
     */
    public Connection getConnection() {
       Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL,userName,password);
        } catch (SQLException e) {
            System.out.println( "Could not connect to the database. "
                    + "Database string = "+
                    databaseURL+ " user =  "+userName + " password = "+password);
            System.exit(1);
        }
       return conn;
    }
    
    public void closeConnection(Connection connection){
        if (connection != null) { 
          try { 
              connection.close(); 
              } catch (SQLException e) {
                    System.out.println( "SQL Exception is thrown while " +
                    "trying to close a Connection object. The connection " + 
                    "object was not null."); 
              }
        }
    }
    
    public static void main(String args[]){
        MySQL database = new MySQL();
        Connection connection = database.getConnection();
        System.out.println("Got CS360 Connection");
        database.closeConnection(connection);
        System.out.println("Closed CS360 Connection ");

    }
    
}
