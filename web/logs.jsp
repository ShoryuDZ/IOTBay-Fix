<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.StaffModel"%>
<%@page import="java.util.LinkedList"%>
<%@page import="IOTBay.Models.LogModel"%>
<%@page import="IOTBay.Models.UserModel"%>
<%@page import="IOTBay.Controllers.LogsController"%>
<%@ page import="IOTBay.Models.CustomerModel" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>User Logs</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet"> 
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body> 
        <!--<form action="main.jsp">-->
            <div class="outerContentBoxTitle">
                <span class="outerContentBoxTitleText">Your Access Log</span>
                <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">
            </div>
            <div class="outerContentBox">
                <div class="contentBoxContent">
                    View your access logs | Search through your access logs (by date)
                    <br>
                </div>
                <hr>
                <table id="ordersTable">
                    <%
                        boolean isAdmin = false;
                        CustomerModel customer = (CustomerModel)session.getAttribute("customer");
                        StaffModel staff = (StaffModel)session.getAttribute("staff");
                        String dateQuery = (String)request.getParameter("dateq");
                        LogsController controller = new LogsController();
                        
                        LinkedList<LogModel> logs = new LinkedList<LogModel>();
                        
                        //determine permissions
                        if (customer != null) {
                            logs = controller.getUserLogs(customer); // show customer logs to customer
                        }
                        else if (staff != null) {
                            logs = controller.getUserLogs(staff); // show staff logs to staff
                        }
                        
                        //process query
                        if (dateQuery != null) {
                            logs = controller.filterLogsByDateQuery(logs, dateQuery);
                        }
                    %>
                    <col span ="1" class="wide">
                    <tr class="searchable-table-header">
                        <th class="orderIDHeaderCell">Log ID</th>
                        <th class="orderHeaderCell">Date(DD/MM/YYYY)
                            <form action="LogsSearchServlet" method="GET">
                            <input type="search" name="dateQuery" placeholder="Search">  
                            <input type="submit" hidden />
                            </form>
                            <%
                            if (dateQuery != null) {
                            %>
                                <p class="query-label">Showing search result(s) for: <strong><%=dateQuery%></strong></p>
                            <%
                            }
                            %>
                        </th>
                        <th class="orderHeaderCell">Login Time</th>
                        <th class="orderHeaderCell">Logout Time</th>
                    </tr>
                    <% for (LogModel log : logs) { %>
                    <tr>
                        <td class="orderCell"><%= log.getId().toString() %></td>
                        <td class="orderCell"><%= log.getDate() %></td>
                        <td class="orderCell"><%= log.getLoginTime() %></td>
                        <td class="orderCell"><%= log.getLogoutTime() %></td>
                    </tr>
                    <% } %>
                </table>
            </div>
            <div class="buttonFooter">
                    <%
                    if (customer != null) {
                    %>
                        <a href="welcome.jsp"><button class="button welcome-button" id="previousButton">< Welcome</button></a>
                    <%
                    }
                    %>
                    <a href="main.jsp"><button class="button" id="nextButton">Main ></button></a>
            </div>
        <!--</form>-->
    </body>
</html>
