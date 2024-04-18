<%-- 
    Document   : dentist
    Created on : Apr 16, 2024, 2:26:21 PM
    Author     : errol
--%>

<%@page import="java.util.List"%>
<%@page import="Business.Dentist"%>
<%@page import="Business.Patient"%>
<%@page import="Business.Appointments"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist Page</title>
        <link rel="stylesheet" href="css/pages.css">
    </head>
    <body>
        <jsp:useBean id="PAT" scope="session" class="Business.Patient"/>
        <jsp:useBean id="DEN" scope="session" class="Business.Dentist"/>
        <header>
            <h1 class="header">Hello Dentist</h1>
        </header>
        <div class="container">
            <div class="info-box">
                <ul>
                    <li class="info">First Name: <jsp:getProperty property="denFName" name="DEN"/></li>
                    <li class="info">Last Name: <jsp:getProperty property="denLName" name="DEN"/></li>
                    <li class="info">Email: <jsp:getProperty property="denEmail" name="DEN"/></li>
                    <li class="info">Office: <jsp:getProperty property="denOffice" name="DEN"/></li>
                </ul>
            </div>
        <button onclick="toggleForm()" id="toggle-button">Update Information</button>
        <form id="updateForm" action="./updateDentist.jsp" method="post" style="display: none;">
            <fieldset for="updateForm" class="update-form">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<jsp:getProperty property="denFName" name="DEN"/>">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="<jsp:getProperty property="denLName" name="DEN"/>">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<jsp:getProperty property="denEmail" name="DEN"/>">
            <div class="center"><input type="submit" id="submit" value="Update"></div>
            </fieldset>
            </form>
            <hr class="line">
        </div>
        <div class="container">
            <h2>Appointments</h2>
        <% 
            Appointments a1 = new Appointments();
            a1.selectDenDB(DEN.getDenId());
            if (a1.getApDate() != "") {
        %>
        <table class="apt-table">
            <tr class="table-heading">
                <th class="table-header">Date</th>
                <th class="table-header">Patient ID</th>
                <th class="table-header">Patient Name</th>
                <th class="table-header">Procedure Code</th>
            </tr>
            <%
                List<Appointments> appointments = DEN.getAppointments();
                for (Appointments appointment : appointments) {
                PAT.selectDB(appointment.getPatId());
                String patientName = PAT.getPatFName() + " " + PAT.getPatLName();
            %>
            <tr>
                <td class="table-data"><%=appointment.getApDate()%></td>
                <td class="table-data"><%=appointment.getPatId()%></td>
                <td class="table-data"><%=patientName%></td>
                <td class="table-data"><%=appointment.getProcCode()%></td>
            </tr>
            <% } %>
        </table>
        <hr class="line">
        <% } else { %>
        <p>empty :(</p>
        <hr class="line">
        <% } %>
        <div class="container footer">
            <a href="./LogoutServlet">
                <button class="logout">Logout</button>
            </a>
        </div>
        <script src="script/script.js"></script>
    </body>
</html>