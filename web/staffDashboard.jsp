<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="IOTBay.Controllers.StaffDashboardController"%>
<%@ page import="IOTBay.Models.*" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Staff Dashboard</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet"> 
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        StaffDashboardController controller = new StaffDashboardController();
        LinkedList<ProductModel> products = controller.getProducts();
        LinkedList<CustomerModel> customers = controller.getCustomers();
        StaffModel staff = (StaffModel)session.getAttribute("staff");
    %>
    <body>  
        <div class="outerContentBoxTitle">
            <span class="outerContentBoxTitleText">Staff Dashboard</span>
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">
        </div>
        <div class ="outerContentBox">
            <form action="StaffDashboardServlet" method="POST">
                <% if (staff.isStaffAdmin()) {%>
                <h1>Customer</h1>
                <table id="ordersTable">
                    <tr>
                        <th class="orderHeaderCell">Customer Name</th>
                        <th class="orderHeaderCell">Email</th>
                        <th class="orderHeaderCell">Phone</th>
                        <th class="orderHeaderCell">Edit</th>
                        <th class="orderHeaderCell">Delete</th>
                    </tr>
                    <% for (CustomerModel customer : customers) {%>
                    <tr>
                        <td class="orderCell"><%= customer.getFullName() %></td>
                        <td class="orderCell"><%= customer.getEmail() %></td>
                        <td class="orderCell"><%= customer.getPhoneNumber() %></td>
                        <td class="orderCell"><input class="link-button" type="submit" name="c:edit:<%=customer.getCustomerId().toString()%>" value="Edit"></td>
                        <td class="orderCell"><input class="link-button" type="submit" name="c:delete:<%=customer.getCustomerId().toString()%>" value="Delete"></td>
                    </tr>
                    <%
                    } %>
                </table>
                <br>
                <button class="button" id="addButton" type="submit" name="c:add">Add Customer</button>
                <% } %>
                <br>
                <h1>Product</h1>
                <table id="ordersTable">
                    <tr>
                        <th class="orderHeaderCell">Product Name</th>
                        <th class="orderHeaderCell">Price</th>
                        <th class="orderHeaderCell">Stock</th>
                        <th class="orderHeaderCell">Category</th>
                        <th class="orderHeaderCell">Edit</th>
                        <th class="orderHeaderCell">Delete</th>
                    </tr>

                    <% for (ProductModel product : products) {%>
                    <tr>
                        <td class="orderCell"><%= product.getFullName() %></td>
                        <td class="orderCell">$<%= product.getPrice() %></td>
                        <td class="orderCell"><%= product.getStock() %></td>
                        <td class="orderCell"><%= product.getCategory() %></td>
                        <td class="orderCell"><input class="link-button" type="submit" name="p:edit:<%=product.getID().toString()%>" value="Edit"></td>
                        <td class="orderCell"><input class="link-button" type="submit" name="p:delete:<%=product.getID().toString()%>" value="Delete"></td>
                    </tr>
                    <%
                    } %>
                </table>
                <br>
                <button class="button" id="addButton" type="submit" name="p:add">Add Product</button>
            </form>
            <br>
        </div>
        <div class="buttonFooter">
            <a href="logs.jsp"><button class="button" id="viewLogsButton" type="submit">View Access Logs</button></a>            
            <a href="main.jsp"><button class="button" id="nextButton" type="submit">Main ></button></a>
            <a href="logout.jsp"><button class="button" id="previousButton" onclick="logOut()"> < Log Out </button></a>
        </div>
    </body>
</html>
