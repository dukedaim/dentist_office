/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import java.sql.*;
/**
 * The Appointments class represents an appointment and provides methods to
 * interact with appointment information stored in a database.
 * @author Daimyan Thomas
 */
public class Appointments {
    String apDate, patId, denId, procCode;
    DBConnection dbconnection = new DBConnection();
    
    /**
     * Sets the date of the appointment.
     * @param apDate the date of the appointment
     */
    public void setApDate(String apDate) { this.apDate = apDate; }
    /**
     * Sets the ID of the patient associated with the appointment.
     * @param patId the ID of the patient
     */
    public void setPatId(String patId) { this.patId = patId; }
    /**
     * Sets the ID of the dentist associated with the appointment.
     * @param denId the ID of the dentist
     */
    public void setDenId(String denId) { this.denId = denId; }
    /**
     * Sets the procedure code associated with the appointment.
     * @param procCode the procedure code
     */
    public void setProcCode(String procCode) { this.procCode = procCode; }
    /**
     * Gets the date of the appointment.
     * @return the date of the appointment
     */
    public String getApDate() { return apDate; }
    /**
     * Gets the ID of the patient associated with the appointment.
     * @return the ID of the patient
     */
    public String getPatId() { return patId; }
    /**
     * Gets the ID of the dentist associated with the appointment.
     * @return the ID of the dentist
     */
    public String getDenId() { return denId; }
    /**
     * Gets the procedure code associated with the appointment.
     * @return the procedure code
     */
    public String getProcCode() { return procCode; }
    
    /** Default constructor that initializes default values for its attributes. */
    public Appointments() {
        apDate = "";
        patId = "";
        denId = "";
        procCode = "";
    }
    
    /**
     * Constructs a new Appointments object with the specified values for its attributes.
     * 
     * @param apDate the date of the appointment
     * @param patId the ID of the patient
     * @param denId the ID of the dentist
     * @param procCode the procedure code
     */
    public Appointments(String apDate, String patId, String denId, String procCode) {
        this.apDate = apDate;
        this.patId = patId;
        this.denId = denId;
        this.procCode = procCode;
    }
    
    /**
     * Retrieves appointment information from the database based on the specified patient ID.
     * @param id the ID of the patient
     */
    public void selectPatDB(String id) {
        patId = id;
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Appointments WHERE patId ='"+getPatId()+"';");
        try {
            rs.next();
            apDate = rs.getString("apptDateTime");
            denId = rs.getString("dentId");
            procCode = rs.getString("procCode");
        } catch(SQLException e) {
            System.out.println("Error in selectPatDB()");
            System.out.println(e);
        }
        
        dbconnection.closeDB();
    }
    
    /**
     * Retrieves appointment information from the database based on the specified dentist ID.
     * @param id the ID of the dentist
     */
    public void selectDenDB(String id) {
        denId = id;
        
        dbconnection.connect();
        ResultSet rs = dbconnection.select("SELECT * FROM Appointments WHERE dentId='"+getDenId()+"';");
        try {
            rs.next();
            apDate = rs.getString("apptDateTime");
            patId = rs.getString("patId");
            procCode = rs.getString("procCode");
        } catch(SQLException e) {
            System.out.println("Error in selectDenDB()");
            System.out.println(e);
        }
    }
    
    /**
     * Inserts a new appointment record into the database.
     * 
     * @param date the date of the appointment
     * @param pat the ID of the patient
     * @param den the ID of the dentist
     * @param code the procedure code
     */
    public void insertDB(String date, String pat, String den, String code) {
        String query = "INSERT INTO Appointments (apptDateTime, patId, dentId, procCode) VALUES (?,?,?,?)";
        String[] values = {date, pat, den, code};
        
        dbconnection.connect();
        int value = dbconnection.update(query, values);
        if (value == 1) {
            System.out.println("Insertion Success");
        } else {
            System.out.println("Insertion Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Updates the appointment information in the database.
     */
    public void updateDB() {
        String query = "UPDATE Appointments SET apptDateTime=?, dentId=?, procCode=? WHERE patId=?";
        String[] values = {getApDate(), getDenId(), getProcCode(), getPatId()};
        
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
     * Deletes the appointment record from the database.
     */
    public void deleteDB() {
        dbconnection.connect();
        int value = dbconnection.delete("DELETE FROM Appointments WHERE patId='"+getPatId()+"';");
        if (value == 1) {
            System.out.println("Deletion Success");
        } else {
            System.out.println("Deletion Failed");
        }
        dbconnection.closeDB();
    }
    
    /**
     * Displays the appointment information.
     */
    public void display() {
        System.out.println("Appointment Date: " + getApDate());
        System.out.println("Patient ID: " + getPatId());
        System.out.println("Dentist ID: " + getDenId());
        System.out.println("Procedure Code: " + getProcCode());
    }
}