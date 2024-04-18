<%-- 
    Document   : updateDentist
    Created on : Apr 17, 2024, 2:20:54 PM
    Author     : errol
--%>

<%@page import="Business.Dentist"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            
            Dentist d1 = (Dentist)session.getAttribute("DEN");
            
            d1.setDenFName(fName);
            d1.setDenLName(lName);
            d1.setDenEmail(email);
            
            d1.updateDB();
            response.sendRedirect("dentist.jsp");
        %>
    </body>
</html>
