package database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import utilities.ErrorLogger;

/**
 *
 * @author cjones
 */
public class SQLUtility {

    private static int convertToMin(LocalDateTime time) {
        return time.getHour() * 60 + time.getMinute();
    }

    

    public static User convertResultSetToUser(ResultSet rs) {
        User user = new User();
        try {
            user.setUserNumber(rs.getInt("userNumber"));
            user.setUserPassword(rs.getString("userPassword"));
            user.setLoginName(rs.getString("loginName"));
            user.setEmailAddress(rs.getString("emailAddress"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setSalt(rs.getString("salt"));
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "SQLException in convertResultSetToUser error " + ex, ex);
            return null;
        }
        return user;
    }
    
        /**
     * This method returns a database table formatted  as an HTML table. 
     * The resulting table has the column names as specified in the database. 
     * 
     * @param results <code> ResultSet </code> containing the database table. 
     * @return <code> String </code> containing an HTML table containing the database
     * table data. 
     * 
     * @throws SQLException if the method cannot access the database
     */
    
    public static String getHtmlTableFromResultSet(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table>");

        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");
        
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnTypeName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");
               htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnLabel(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>"); 

        // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");
        
        htmlTable.append("<p>");
        htmlTable.append("<table>");

        // add header row

        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnLabel(i));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnTypeName(i));
            htmlTable.append("</th>");
            htmlTable.append("</tr>");
        }
          

        return htmlTable.toString();
    }
    public static String getMetaDataResultSet(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table>");

        // add header row

        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<tr>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnLabel(i));
            htmlTable.append("</th>");
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnTypeName(i));
            htmlTable.append("</th>");
            htmlTable.append("</tr>");
        }
          
        return htmlTable.toString();
    }
   
}
