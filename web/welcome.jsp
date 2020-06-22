<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.CartItemModel"%>
<%@page import="IOTBay.Models.OrderModel"%>
<%@page import="java.util.LinkedList"%>
<%@page import="IOTBay.Controllers.WelcomeController"%>
<%@page import="IOTBay.Models.dao.DBManager"%>
<%@ page import="IOTBay.Models.CustomerModel" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>IOTBay ~ Welcome</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet"> 
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%
            CustomerModel customer = (CustomerModel)session.getAttribute("customer");
            String orderQuery = (String)request.getParameter("orderq");
            String paymentDetailQuery = (String)request.getParameter("paymentq");
            String shippingDetailQuery = (String)request.getParameter("shippingq");
            
            
            WelcomeController controller = new WelcomeController();
            LinkedList<OrderModel> orders = controller.getOrders(customer);
            
            if (orderQuery != null) {
                orders = controller.filterOrdersByOrderQuery(orders, orderQuery);
            } 
            else if (paymentDetailQuery != null) {
                orders = controller.filterOrdersByPaymentQuery(orders, paymentDetailQuery);
            } 
            else if (shippingDetailQuery != null) {
                orders = controller.filterOrdersByShippingQuery(orders, shippingDetailQuery);
            }

        %>    
        <div class="outerContentBoxTitle">
            <span class="outerContentBoxTitleText">Welcome, <%= customer.getFirstName() %></span>
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">
        </div>
        <div class="outerContentBox">
            <div class="contentBoxContent">
                <div class="accordion" onClick="showDetailsAccordion()">Click here to view Details</div>
                <div id="accordionPanel" style="max-height: 0px;">
                    Name: <%= customer.getFullName() %> <br>
                    Contact: <%= customer.getContactDetails() %> <br> <br>
                    <form action="DeleteServlet" method="POST">
                        <button type="submit">Delete account</button>                        
                        <label> Beware of deleting your account. This action is irreversible.</label>
                    </form>
                    <br>
                    <br>
                </div>
                View your orders in the table below. <br> Alternatively, press Main below to be taken to the main shopping page, Edit Details to see/change your current customer details, & View Access Logs to view the times you've logged in and out.
            </div>
            <hr>
            <table id="ordersTable">
                <tr class="searchable-table-header">
                    <th class="orderHeaderCell">Order ID 
                        <form action="WelcomeOrderSearchServlet" method="GET">
                        <input type="search" name="orderQuery" placeholder="Search">  
                        <input type="submit" hidden />
                        </form>
                        <%
                        if (orderQuery != null) {
                        %>
                            <p class="query-label">Showing search result(s) for: <strong><%=orderQuery%></strong></p>
                        <%
                        }
                        %>
                    </th>
                    <th class="orderHeaderCell" id="itemsColumn">Items
                    </th>
                    <th class="orderHeaderCell">Payment Details 
                        <form action="WelcomePaymentSearchServlet" method="GET">
                        <input type="search" name="paymentDetailQuery" placeholder="Search">
                        <input type="submit" hidden />
                        </form>
                        <%
                        if (paymentDetailQuery != null) {
                        %>
                            <p class="query-label">Showing search result(s) for: <strong><%=paymentDetailQuery%></strong></p>
                        <%
                        }
                        %>
                    </th>
                    <th class="orderHeaderCell">Shipping Details
                        <form action="WelcomeShippingSearchServlet" method="GET">
                        <input type="search" name="shippingDetailQuery" placeholder="Search">
                        <input type="submit" hidden />
                        </form>
                        <%
                        if (shippingDetailQuery != null) {
                        %>
                            <p class="query-label">Showing search result(s) for: <strong><%=shippingDetailQuery%></strong></p>
                        <%
                        }
                        %>
                    </th>
                </tr>
                <%
                for (OrderModel order : orders) {
                %>
                <tr>
                    <td class="orderCell"><%= order.getOrderID().toString() %></td>
                    <td class="orderCell"><%
                        for (CartItemModel cartItem : order.getCart().getCartItems()) {
                            out.print(cartItem.getProduct().getFullName() + " x " + cartItem.getQuantity() + " <br>");
                        } 
                    %></td>
                    <td class="orderCell"><a href="paymentDetails.jsp?id=<%= order.getPayment().getId().toString()%>">Payment Details</a></td>
                    <td class="orderCell"><a href="shippingDetails.jsp?id=<%= order.getShipping().getShippingId().toString()%>">Shipping Details</a>
                </tr>
                <% } %>
            </table>
        </div>
        <div class="buttonFooter">
            <a href="logs.jsp"><button class="button" id="viewLogsButton" type="submit">View Access Logs</button></a>
            <a href="details.jsp"><button class="button" id="editDetailsButton" type="submit">< Edit Details</button></a>
            <a href="main.jsp"><button class="button" id="nextButton" type="submit">Shop ></button></a>
        </div>
    </body>
</html>
