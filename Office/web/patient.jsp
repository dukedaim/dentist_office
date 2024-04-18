<%-- 
    Document   : patient
    Created on : Apr 16, 2024, 1:11:55 AM
    Author     : errol
--%>
<%@page import="Business.Appointments"%>
<%@page import="Business.Patient"%>
<%@page import="Business.Dentist"%>
<%@page import="Business.Procedures"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient Page</title>
        <link rel="stylesheet" href="css/pages.css">
    </head>
    <body>
        <% 
            Patient p1;
            p1 = (Patient)session.getAttribute("PAT");
            p1.display();
            
            Appointments a1 = new Appointments();
            a1.selectPatDB(p1.getPatId());
            System.out.println();
            a1.display();
            
            Dentist d1 = new Dentist();
            d1.selectDB(a1.getDenId());
            System.out.println();
            d1.display();
            
            Dentist name = new Dentist();
            name.selectDB("D201");
            String den1Name = name.getDenFName() + " " + name.getDenLName();
            name.selectDB("D202");
            String den2Name = name.getDenFName() + " " + name.getDenLName();
            name.selectDB("D203");
            String den3Name = name.getDenFName() + " " + name.getDenLName();
            name.selectDB("D204");
            String den4Name = name.getDenFName() + " " + name.getDenLName();
            
            Procedures p2 = new Procedures();
            p2.selectDB(a1.getProcCode());
            p2.display();
        %>
        <header>
            <h1 class="header">Welcome Patient</h1>
        </header>
        <div class="container">
            <div class="info-box">
                <ul>
                    <li>First Name: <%=p1.getPatFName()%></li>
                    <li>Last Name: <%=p1.getPatLName()%></li>
                    <li>Address: <%=p1.getPatAddr()%></li>
                    <li>Email: <%=p1.getPatEmail()%></li>
                    <li>Insurance Company: <%=p1.getPatInsCo()%></li>
                </ul>
            </div>
        
        <button onclick="toggleForm()" id="toggle-button">Update Information</button>
            <form id="updateForm" action="./updatePatient.jsp" method="post" style="display: none;">
                <fieldset for="updateForm" class="update-form">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="<%=p1.getPatFName()%>">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="<%=p1.getPatLName()%>">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="<%=p1.getPatAddr()%>">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="<%=p1.getPatEmail()%>">
                <label for="insCo">Insurance Company:</label>
                <input type="text" id="insCo" name="insCo" value="<%=p1.getPatInsCo()%>">
                <div class="center"><input type="submit" id="submit" value="Update"></div>
                </fieldset>
            </form>
            <hr class="line">
        </div>
        <div class="container">
        <% if (a1.getApDate() != "") { %>
        <div class="info-box bigger">
            <p>You have an appointment on <%=a1.getApDate()%> with <%=d1.getDenFName()%> <%=d1.getDenLName()%> and the procedure will be <%=p2.getProcName()%></p>
        </div>
        <div class="center">
                <button onclick="toggleAppointForm()">Change Appointment</button>
                <a href="./deleteAppointment.jsp"><button>Delete Appointment</button></a>
            </div>
        <form id="showAppointForm" action="./updatePatientAppointment.jsp" method="post" style="display: none;">
            <fieldset for="showAppointForm" class="update-form">
                <label for="date">Pick a Date</label>
                <input type="date" name="appointDate" id="appointDate" min="2018-05-01" max="2018-05-25" value="2018-05-01">
                <label for="time">Pick a Time</label>
                <select name="selectTime" id="time">
                    <option value="9am" name="9am">9am</option>
                    <option value="10am" name="10am">10am</option>
                    <option value="11am" name="11am">11am</option>
                    <option value="1pm" name="1pm">1pm</option>
                    <option value="2pm" name="2pm">2pm</option>
                    <option value="3pm" name="3pm">3pm</option>
                    <option value="4pm" name="4pm">4pm</option>
                </select>
                <label for="dentist">Pick a dentist</label>
                <select name="selectDentist" id="dentist">
                    <option value="D201"><%=den1Name%></option>
                    <option value="D202"><%=den2Name%></option>
                    <option value="D203"><%=den3Name%></option>
                    <option value="D204"><%=den4Name%></option>
                </select>
                <label for="procedure">Pick a procedure</label>
                <select name="selectProcedure" id="procedure">
                    <option value="P114">Cleaning/Exam</option>
                    <option value="P119">Xrays</option>
                    <option value="P122">Whitening</option>
                    <option value="P321">Cavity</option>
                    <option value="P650">Top Dentures</option>
                    <option value="P660">Bottom Dentures</option>
                    <option value="P780">Crown</option>
                    <option value="P790">Root Canel</option>
                </select>
                <div class="center">
                    <input type="submit" id="submit" value="Update Appointment">
                </div>
            </fieldset>
        </form>
        <hr class="line">
        <% } else { %>
        <div class="info-box bigger">
            <p>You currently do not have an appointment would you like to set one up?</p>
        </div>
        <button onclick="toggleAppointForm()">Click Here To Create Appointment</button>
        <form id="showAppointForm" action="./createPatientAppointment.jsp" method="post" style="display: none;">
            <fieldset for="showAppointForm" class="update-form">
                <label for="date">Pick a Date</label>
                <input type="date" name="appointDate" id="appointDate" min="2018-05-01" max="2018-05-25" value="2018-05-01">
                <label for="time">Pick a Time</label>
                <select name="selectTime" id="time">
                    <option value="9am" name="9am">9am</option>
                    <option value="10am" name="10am">10am</option>
                    <option value="11am" name="11am">11am</option>
                    <option value="1pm" name="1pm">1pm</option>
                    <option value="2pm" name="2pm">2pm</option>
                    <option value="3pm" name="3pm">3pm</option>
                    <option value="4pm" name="4pm">4pm</option>
                </select>
                <label for="dentist">Pick a dentist</label>
                <select name="selectDentist" id="dentist">
                    <option value="D201"><%=den1Name%></option>
                    <option value="D202"><%=den2Name%></option>
                    <option value="D203"><%=den3Name%></option>
                    <option value="D204"><%=den4Name%></option>
                </select>
                <label for="procedure">Pick a procedure</label>
                <select name="selectProcedure" id="procedure">
                    <option value="P114">Cleaning/Exam</option>
                    <option value="P119">Xrays</option>
                    <option value="P122">Whitening</option>
                    <option value="P321">Cavity</option>
                    <option value="P650">Top Dentures</option>
                    <option value="P660">Bottom Dentures</option>
                    <option value="P780">Crown</option>
                    <option value="P790">Root Canel</option>
                </select>
                <div class="center">
                    <input type="submit" id="submit" value="Create Appointment">
                </div>
            </fieldset>
        </form>
        <hr class="line">
        <% } %>
        </div>
        <footer>
            <div class="container footer">
                <a href="./LogoutServlet"><button>Logout</button></a>
            </div>
        </footer>
        <script src="script/script.js"></script>
    </body>
</html>
