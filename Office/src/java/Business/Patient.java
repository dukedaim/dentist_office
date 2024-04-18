/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
/**
 * The Patient class represents a patient and provides methods to interact with their
 * information stored in the database.
 * @author Daimyan Thomas
 */
public class Patient {
    private String patId, patPass, patFName, patLName, patAddr, patEmail, patInsCo;
    DBConnection dbconnection = new DBConnection();
    
    /** Default constructor that initializes default values for its attributes. */
    public Patient() {
        patId = "";
        patPass = "";
        patFName = "";
        patLName = "";
        patAddr = "";
        patEmail = "";
        patInsCo = "";
    }
    
    /**
     * Constructs a new Patient object with the specified values for its attributes.
     * 
     * @param patId the patient identifier
     * @param patPass the patient password
     * @param patFName the patient first name
     * @param patLName the patient last name
     * @param patAddr the patient address
     * @param patEmail the patient email
     * @param patInsCo the patient insurance company
     */
    public Patient(String patId, String patPass, String patFName, String patLName, String patAddr, String patEmail, String patInsCo) {
        this.patId = patId;
        this.patPass = patPass;
        this.patFName = patFName;
        this.patLName = patLName;
        this.patAddr = patAddr;
        this.patEmail = patEmail;
        this.patInsCo = patInsCo;
    }


    //get methods
    /**
     * Gets the identifier of the patient.
     * @return the patient's identifier
     */
    public String getPatId() { return patId; }
    /**
     * Gets the password of the patient.
     * @return the patient's password
     */
    public String getPatPass() { return patPass; }
    /**
     * Gets the first name of the patient.
     * @return the patient's first name
     */
    public String getPatFName() { return patFName; }
    /**
     * Gets the last name of the patient.
     * @return the patient's last name
     */
    public String getPatLName() { return patLName; }
    /**
     * Gets the address of the patient.
     * @return the patient's address
     */
    public String getPatAddr() { return patAddr; }
    /**
     * Gets the email of the patient.
     * @return the patient's email
     */
    public String getPatEmail() { return patEmail; }
    /**
     * Gets the insurance company of the patient.
     * @return the patient's insurance company
     */
    public String getPatInsCo() { return patInsCo; }
    
    //set methods
    /**
     * Sets the identifier of the patient.
     * @param patId the patient's identifier
     */
    public void setPatId(String patId) { this.patId = patId; }
    /**
     * Sets the password of the patient.
     * @param patPass the patient's password
     */
    public void setPatPass(String patPass) { this.patPass = patPass; }
    /**
     * Sets the first name of the patient.
     * @param patFName the patient's first name
     */
    public void setPatFName(String patFName) { this.patFName = patFName; }
    /**
     * Sets the last name of the patient.
     * @param patLName the patient's last name
     */
    public void setPatLName(String patLName) { this.patLName = patLName; }
    /**
     * Sets the address of the patient.
     * @param patAddr the patient's address
     */
    public void setPatAddr(String patAddr) { this.patAddr = patAddr; }
    /**
     * Sets the email of the patient.
     * @param patEmail the patient's email
     */
    public void setPatEmail(String patEmail) { this.patEmail = patEmail; }
    /**
     * Sets the insurance company of the patient.
     * @param patInsCo the patient's insurance company
     */
    public void setPatInsCo(String patInsCo) { this.patInsCo = patInsCo; }
    
    /**
     * Retrieves the patient's information from the database based on the specified identifier.
     * @param id identifier of the patient
     */
    public void selectDB(String id)  {
        patId = id;
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Patients WHERE patId = '" + id + "';");
        try {
            rs.next();
            patPass = rs.getString("passwd");
            patFName = rs.getString("firstName");
            patLName = rs.getString("lastName");
            patAddr = rs.getString("addr");
            patEmail = rs.getString("email");
            patInsCo = rs.getString("insCo");
        } catch(SQLException e) {
            System.out.println("Error selecting from Patients database.");
            System.out.println(e);
        }
        dbconnection.closeDB();
    }
    
    /**
     * Updates the patient's information in the database.
     */
    public void updateDB() {
        String query = "UPDATE Patients SET passwd=?, firstName=?, lastName=?, addr=?, email=?, insCo=? WHERE patId=?";
        String[] values = {getPatPass(), getPatFName(), getPatLName(), getPatAddr(), getPatEmail(), getPatInsCo(), getPatId()};
        
        dbconnection.connect();
        int value = dbconnection.update(query, values);
        if (value == 1) {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Deletes the patient's record from the database.
     */
    public void deleteDB() {
        dbconnection.connect();
        int value = dbconnection.delete(
        "DELETE FROM Patients WHERE patId = '"+getPatId()+"';");
        if (value == 1) {
            System.out.println("Deletion Success");
        } else {
            System.out.println("Deletion Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Displays the patient's information.
     */
    public void display() {
        System.out.println("Name: " + getPatFName());
        System.out.println("Surname: " + getPatLName());
        System.out.println("Address: " + getPatAddr());
        System.out.println("Email: " + getPatEmail());
        System.out.println("Insurance Company: " + getPatInsCo());
    }
}