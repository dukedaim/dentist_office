/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import Business.Patient;
import Business.Dentist;
import Business.DBConnection;
import jakarta.servlet.http.HttpSession;

/**
 * The LoginServlet class handles user authentication and redirects users based on their role (patient or dentist).
 * It processes login requests sent via HTTP GET method.
 * @author Daimyan Thomas
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    /**
     * Handles HTTP GET requests for user login.
     * Retrieves user credentials from the request parameters, validates them against the database,
     * and redirects user based on their role (patient or dentist).
     * 
     * @param request the HttpServletRequest object containing the request parameters
     * @param response the HttpServletResponse object for sending the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pass = request.getParameter("password");
        String user = request.getParameter("username");
        DBConnection dbconnection = new DBConnection();
        String[] creds = {user,pass};
        String patientQuery = "SELECT * FROM Patients WHERE patId=? AND passwd=?";
        String dentistQuery = "SELECT * FROM Dentists WHERE id=? AND passwd=?";
        
        dbconnection.connect();
        ResultSet test = dbconnection.select(patientQuery, creds);
        try {
            if (test.next()) {
                Patient p1 = new Patient();
                p1.selectDB(user);
                p1.display();
                
                HttpSession ses1 = request.getSession();
                ses1.setAttribute("PAT", p1);
                
                RequestDispatcher rd = request.getRequestDispatcher("patient.jsp");
                rd.forward(request, response);
                dbconnection.closeDB();
            }
        } catch (SQLException e) {
            System.out.println(e);
            dbconnection.closeDB();
        }
        
        dbconnection.connect();
        ResultSet test2 = dbconnection.select(dentistQuery, creds);
        try {
            if(test2.next()) {
                Dentist d1 = new Dentist();
                d1.selectDB(user);
                d1.display();
                    
                HttpSession ses1 = request.getSession();
                ses1.setAttribute("DEN", d1);
                    
                RequestDispatcher rd = request.getRequestDispatcher("dentist.jsp");
                rd.forward(request, response);
                dbconnection.closeDB();
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("LoginError.jsp");
                rd.forward(request, response);
                dbconnection.closeDB();
            }
        } catch(SQLException e) {
            System.out.println(e);
            dbconnection.closeDB();
        }
    }
}