<%-- 
    Document   : createPatientAppointment
    Created on : Apr 17, 2024, 12:54:25 AM
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
            String date = request.getParameter("appointDate");
            String time = request.getParameter("selectTime");
            String dent = request.getParameter("selectDentist");
            String proc = request.getParameter("selectProcedure");
            
            String[] partsTime = date.split("-");
            String formattedDate = "May " + partsTime[2] + ", " + partsTime[0] + ", " + time;
            
            Patient p1 = (Patient)session.getAttribute("PAT");
            String id = p1.getPatId();
            
            Appointments a1 = new Appointments();
            a1.insertDB(formattedDate, id, dent, proc);
            response.sendRedirect("patient.jsp");
        %>
    </body>
</html>
