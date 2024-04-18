<%-- 
    Document   : deleteAppointment
    Created on : Apr 17, 2024, 1:24:46 AM
    Author     : errol
--%>

<%@page import="Business.Patient"%>
<%@page import="Business.Appointments"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Patient p1 = (Patient)session.getAttribute("PAT");
            String id = p1.getPatId();
            
            Appointments a1 = new Appointments();
            a1.setPatId(id);
            a1.deleteDB();
            response.sendRedirect("patient.jsp");
        %>
    </body>
</html>
