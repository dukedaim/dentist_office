/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The Dentist class represents a dentist and provides methods to interact with their
 * information stored in the database
 * @author Daimyan Thomas
 */
public class Dentist {
    private String denId, denPass, denFName, denLName, denEmail, denOffice;
    DBConnection dbconnection = new DBConnection();
    
    /** Default constructor that initializes default values for its attributes. */
    public Dentist() {
        denId = "";
        denPass = "";
        denFName = "";
        denLName = "";
        denEmail = "";
        denOffice = "";
    }
    
    /**
     * Constructs a new Dentist object with the specified values for its attributes.
     * 
     * @param denId the dentist identifier
     * @param denPass the dentist password
     * @param denFName the dentist first name
     * @param denLName the dentist last name
     * @param denEmail the dentist email
     * @param denOffice the dentist office
     */
    public Dentist(String denId, String denPass, String denFName, String denLName, String denEmail, String denOffice) {
        this.denId = denId;
        this.denPass = denPass;
        this.denFName = denFName;
        this.denLName = denLName;
        this.denEmail = denEmail;
        this.denOffice = denOffice;
    }
    
    //get methods
    /**
     * Gets the identifier of the dentist.
     * @return the dentist's indentifier
     */
    public String getDenId() { return denId; }
    /**
     * Gets the password of the dentist.
     * @return the dentist's password
     */
    public String getDenPass() { return denPass; }
    /**
     * Gets the first name of the dentist.
     * @return the dentist's first name
     */
    public String getDenFName() { return denFName; }
    /**
     * Gets the last name of the dentist.
     * @return the dentist's last name
     */
    public String getDenLName() { return denLName; }
    /**
     * Gets the email of the dentist.
     * @return the dentist's email
     */
    public String getDenEmail() { return denEmail; }
    /**
     * Gets the office of the dentist.
     * @return the dentist's office
     */
    public String getDenOffice() { return denOffice; }
    
    //set methods
    /**
     * Sets the identifier of the dentist.
     * @param denId the dentist's identifier
     */
    public void setDenId(String denId) { this.denId = denId; }
    /**
     * Sets the password of the dentist.
     * @param denPass the dentist's password
     */
    public void setDenPass(String denPass) { this.denPass = denPass; }
    /**
     * Sets the first name of the dentist.
     * @param denFName the dentist's first name
     */
    public void setDenFName(String denFName) { this.denFName = denFName; }
    /**
     * Sets the last name of the dentist.
     * @param denLName the dentist's last name
     */
    public void setDenLName(String denLName) { this.denLName = denLName; }
    /**
     * Sets the email of the dentist.
     * @param denEmail the dentist's email
     */
    public void setDenEmail(String denEmail) { this.denEmail = denEmail; }
    /**
     * Sets the office of the dentist
     * @param denOffice the dentist's office
     */
    public void setDenOffice(String denOffice) { this.denOffice = denOffice; }
    
    /**
     * Retrieves the dentist's information from the database based on the specified identifier.
     * @param denId identifier of the dentist
     */
    public void selectDB(String denId) {
        this.denId = denId;
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Dentists WHERE id = '"+denId+"';");
        try {
            rs.next();
            denPass = rs.getString("passwd");
            denFName = rs.getString("firstName");
            denLName = rs.getString("lastName");
            denEmail = rs.getString("email");
            denOffice = rs.getString("office");
        } catch(SQLException e) {
            System.out.println("Error selecting from Dentists database.");
            System.out.println(e);
        }
    }
    
    /**
     * Updates the dentist's information in the database.
     */
    public void updateDB() {
        String query = "UPDATE Dentists SET passwd=?, firstName=?, lastName=?, email=?, office=? WHERE id=?";
        String[] values = {getDenPass(), getDenFName(), getDenLName(), getDenEmail(), getDenOffice(), getDenId()};
        
        dbconnection.connect();
        int value = dbconnection.update(query, values);
        if (value == 1)  {
            System.out.println("Update Success");
        } else {
            System.out.println("Update Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Deletes the dentist's record from the database.
     */
    public void deleteDB() {
        dbconnection.connect();
        int value = dbconnection.delete("DELETE FROM Dentists WHERE id = '"+getDenId()+"';");
        if (value == 1) {
            System.out.println("Deletion Success");
        } else {
            System.out.println("Deletion Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Retrieves a list of appointments associated with the dentist from the database.
     * @return a list of appointments associated with the dentist
     */
    public List<Appointments> getAppointments() {
        List<Appointments> appointments = new ArrayList<>();
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Appointments WHERE dentId='"+getDenId()+"';");
        try {
            while(rs.next()) {
                Appointments appointment = new Appointments();
                appointment.setApDate(rs.getString("apptDateTime"));
                appointment.setPatId(rs.getString("patId"));
                appointment.setProcCode(rs.getString("procCode"));
                appointments.add(appointment);
            }
        } catch(SQLException e) {
            System.out.println("Error in getAppoinments()");
            System.out.println(e);
        }
        dbconnection.closeDB();
        return appointments;
    }
    
    /**
     * Displays the dentist's information.
     */
    public void display() {
        System.out.println("Name: " + getDenFName());
        System.out.println("Surname: " + getDenLName());
        System.out.println("Email: " + getDenEmail());
        System.out.println("Office: " + getDenOffice());
    }
}