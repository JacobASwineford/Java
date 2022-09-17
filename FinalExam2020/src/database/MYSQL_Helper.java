package database;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author cjones
 */
public class MYSQL_Helper {
    private static final boolean REUSE_CONNECTION=true;
    private static int connectionCount = 0;
    private static Connection connection = null;

    private static final String mysqlPrefix="jdbc:mysql://";
    private static final String hostname ="classdb.bloomu.edu";
   //  private static final String databaseName = "Compsci360_01";
     private static final String databaseName = "CS360";
     private static final String databaseURL = mysqlPrefix+hostname +"/"+databaseName;
     private static final String userName = "csuser";
      private static final String password = "csuser";


    /**
     * Returns a connection to the database. 
     * 
     * @return A <code>Connection</code> to the database.
     */
    public static Connection getConnection() {

     try {
        if (connection == null || connection.isClosed()) {
            connection = MYSQL_Helper.getNewConnection();
            return connection;
        }
        // Now and old connection did exist, but is it still valid?
        
            //allow 3 seconds to see if connection is still valid
            if (connection.isValid(3)) {
                return connection;
            } else {
                connection = MYSQL_Helper.getNewConnection();
                return connection;
            }
        } catch (SQLException e) {
            return getNewConnection();
        }
    }

    /**
     * Creates a new connection with a DriverManager and returns it if the connection
     * is established. Catches any SQLException exception thrown by the
     * <code>getConnection(String url, String user, String password)</code>
     * method of <code>DriverManager</code> class and logs an error message in the error log
     * file.  
     *
     * @return A <code>Connection</code> to the database or null if none could be created.
     */
    private static Connection getNewConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //Not always needed for MySQL - here for safety only
        } catch (ClassNotFoundException ex) {
           System.out.println( "Could not find the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
            ex.printStackTrace();
        } catch (InstantiationException ex) {
              System.out.println("Could not instaniate the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
           ex.printStackTrace();
        } catch (IllegalAccessException ex) {
           System.out.println( "Could not access the class com.mysql.jdbc.Driver \n"+
                    "Program will now exit. ");
            ex.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(databaseURL,userName,password);
        } catch (SQLException e) {
            System.out.println( "SQL Exception was thrown while "
                    + "trying to connect to the database. Database string = "+
                    databaseURL+ " user =  "+userName + " password "+password);
        }
        System.out.println("Connection Count is "+ ++connectionCount);
        //Connection is thread safe, but only one thread can access the database at once now
        return conn;
    }

    
    /**
     * Returns the given <code>Connection</code> object to the connection pool.
     *
     * @param connection The Connection object being returned.
     */
    public static void returnConnection(Connection connection) {
        if(REUSE_CONNECTION) return;             
        if (connection != null) { 
          try { connection.close(); connection=null;
              } catch (SQLException e) {
                    System.out.println( "SQL Exception is thrown while " +
                    "trying to close a Connection object. The connection " + 
                    "object was not null."); 
              }
        }
         
    }
    
    
    
    
    /**
     * Closes the given <code>Connection</code> object if it is not null.
     * Catches SQLException if it is thrown by the <code>close</code> method of
     * <code>Connection</code> interface and logs an error message in the 
     * error log file.
     *
     * @param conn The Connection object to close.
     */
    public static void closeConnectionsOnExit() {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception is thrown while "
                    + "trying to close a Connection object during "
                    + "program termination. The connection "
                    + "object was not null.");
        }

    }
    
   /**
     * Closes the given <code>Statement</code> object if it is not null. Catches
     * SQLException if it is thrown by the <code>close</code> method of
     * <code>Statement</code> interface and logs an error message in the error log file.
     *
     * @param stmt The Statement object to close.
     */
    public static void closeStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
               System.out.println( "SQL Exception is thrown while "
                        + "trying to close a Statement object. The connection "
                        + "object is not null.");
            }
        }
    }
    
    /**
     * Closes the given <code>PreparedStatement</code> object if it is not null.
     * Catches SQLException if it is thrown by the <code>close</code> method of
     * <code>PreparedStatement</code> interface and logs an error message in the error log
     * file.
     *
     * @param ps The PreparedStatement object to close.
     */
    public static void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println( "SQL Exception is thrown while "
                        + "trying to close a PreparedStatement object. The connection "
                        + "object is not null.");
            }
        }
    }
    
    /**
     * Closes the given <code>ResultSet</code> object if it is not null. Catches
     * the SQLException if it is thrown by the <code>close</code> method of
     * <code>ResultSet</code> interface and logs an error message in the error log file.
     *
     * @param rs The ResultSet object to close.
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
               System.out.println("SQL Exception is thrown while "
                        + "trying to close a ResultSet object. The connection "
                        + "object is not null.");
            }
        }
    }

    public static void main(String[] args){
         Connection conn = MYSQL_Helper.getConnection();
         Connection newconnection = MYSQL_Helper.getConnection();
         MYSQL_Helper.returnConnection(conn);
         MYSQL_Helper.returnConnection(newconnection);
         newconnection = MYSQL_Helper.getConnection();
         conn = MYSQL_Helper.getConnection();
         conn = MYSQL_Helper.getConnection();
         MYSQL_Helper.returnConnection(conn);
         MYSQL_Helper.returnConnection(newconnection);
         conn = MYSQL_Helper.getConnection();
         conn = MYSQL_Helper.getConnection();
         MYSQL_Helper.closeConnectionsOnExit();
    }
    
}
