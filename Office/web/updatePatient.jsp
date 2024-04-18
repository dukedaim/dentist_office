<%-- 
    Document   : updatePatient
    Created on : Apr 16, 2024, 11:27:02 PM
    Author     : errol
--%>

<%@page import="Business.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Patient Information</title>
    </head>
    <body>
        <%
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String addr = request.getParameter("address");
            String email = request.getParameter("email");
            String insCo = request.getParameter("insCo");
            
            Patient p1 = (Patient)session.getAttribute("PAT");
            
            p1.setPatFName(fName);
            p1.setPatLName(lName);
            p1.setPatAddr(addr);
            p1.setPatEmail(email);
            p1.setPatInsCo(insCo);
            
            p1.updateDB();
            response.sendRedirect("patient.jsp");
        %>
    </body>
</html>
