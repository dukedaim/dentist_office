/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
/**
 * The Procedures class represents a dental procedure and provides methods to interact
 * with procedure information stored in a database.
 * @author Daimyan Thomas
 */
public class Procedures {
    private String procCode, procName, procDesc;
    private int cost;
    DBConnection dbconnection = new DBConnection();

    /**
     * Sets the code of the procedure
     * @param procCode the code of the procedure
     */
    public void setProcCode(String procCode) { this.procCode = procCode; }
    /**
     * Sets the name of the procedure
     * @param procName the name of the procedure
     */
    public void setProcName(String procName) { this.procName = procName; }
    /**
     * Sets the description of the procedure
     * @param procDesc the description of the procedure
     */
    public void setProcDesc(String procDesc) { this.procDesc = procDesc; }
    /**
     * Sets the cost of the procedure
     * @param cost the cost of the procedure
     */
    public void setCost(int cost) { this.cost = cost; }
    /**
     * Gets the code of the procedure.
     * @return the code of the procedure
     */
    public String getProcCode() { return procCode; }
    /**
     * Gets the name of the procedure.
     * @return the name of the procedure
     */
    public String getProcName() { return procName; }
    /**
     * Gets the description of the procedure
     * @return the description of the procedure
     */
    public String getProcDesc() { return procDesc; }
    /**
     * Gets the cost of the procedure
     * @return the cost of the procedure
     */
    public int getCost() { return cost; }
    
    /** Default constructor that initializes default values for its attributes. */
    public Procedures() {
        procCode = "";
        procName = "";
        procDesc = "";
        cost = 0;
    }
    
    /**
     * Constructs a new Procedures object with the specified values for its attributes.
     * @param procCode the code of the procedure
     * @param procName the name of the procedure
     * @param procDesc the description of the procedure
     * @param cost the cost of the procedure
     */
    public Procedures(String procCode, String procName, String procDesc, int cost) {
        this.procCode = procCode;
        this.procName = procName;
        this.procDesc = procDesc;
        this.cost = cost;
    }
    
    /**
     * Retrieves procedure information from the database based on the specified procedure code.
     * @param code the code of the procedure
     */
    public void selectDB(String code) {
        procCode = code;
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Procedures WHERE procCode ='"+code+"';");
        try {
            rs.next();
            procName = rs.getString("procName");
            procDesc = rs.getString("procDesc");
            cost = rs.getInt("cost");
        } catch(SQLException e) {
            System.out.println("Error selecting from Patients database.");
            System.out.println(e);
        }
        dbconnection.closeDB();
    }
    
    /**
     * Displays the procedure information.
     */
    public void display() {
        System.out.println("Procedure Code: " + getProcCode());
        System.out.println("Procedure Name: " + getProcName());
        System.out.println("Procedure Description: " + getProcDesc());
        System.out.println("Procedure Cost: " + getCost());
    }
}