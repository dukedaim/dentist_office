/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
/**
 * The DBConnection class establishes a connection to a Microsoft Access database and
 * provides methods for executing SQL queries and updates.
 * @author Daimyan Thomas
 */
public final class DBConnection {
    /** The JDBC driver class name for UCanAccess */
    static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";
    /** The database URL for the Microsoft Access database. */
    static final String DB_URL = "";
    Connection con;
    
    /** Default constructor that establishes a connection with the database.*/
    public DBConnection() {
        connect();
    }
    
    /** Establishes a connection with the database.*/
    public void connect() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Executes a SELECT query on the database.
     * 
     * @param query the SQL SELECT query to execute
     * @return the ResultSet object containing the results of the query, or null if an error occurs
     */
    public ResultSet select(String query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Executes a PreparedStatement SELECT query on the database.
     * 
     * @param query the SQL SELECT query to execute
     * @param values an array of values to set as parameters in the query
     * @return the ResultSet object containing the results of the query, or null if an error occurs
     */
    public ResultSet select(String query, String[] values) {
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i+1, values[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            return rs;
        } catch(SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    /**
     * Executes an UPDATE or INSERT query on the database.
     * 
     * @param query the SQL UPDATE or INSERT query to execute
     * @param values an array of values set as parameters in the query
     * @return the number of rows affected by the query, or -1 if an error occurs
     */
    public int update(String query, String[] values) {
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i+1, values[i]);
            }
            return pstmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
    
    /**
     * Executes a DELETE query on the database.
     * 
     * @param query the SQL DELETE query to execute
     * @return the number of rows affected by the query, or -1 if an error occurs
     */
    public int delete(String query) {
        try {
            Statement stmt = con.createStatement();
            return stmt.executeUpdate(query);
        } catch(SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
   
    
    /**
     * Closes the database connection.
     */
    public void closeDB() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
