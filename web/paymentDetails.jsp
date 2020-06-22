<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Controllers.PaymentDetailsController"%>
<%@ page import="IOTBay.Models.*" %>



<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Payment Details</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <% 
        PaymentDetailsController controller = new PaymentDetailsController();
        String id = (String)request.getParameter("id");
        PaymentModel payment = controller.getPaymentModel(id);
    %>
    <body>                
        <div class="outerContentBoxTitle">
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">            
            <span class="outerContentBoxTitleText">Payment Details</span>
        </div>   
        <div class="outerContentBox" id="shippingContentBox">
            <table id="shippingTable" border="1"> 
                <tr>
                    <td class="shippingTitleCell"><strong>Payment ID</strong></td>
                    <td class="shippingDataCell"><% out.println(payment.getId());%></td>
                </tr>
                <tr>
                    <td class="shippingTitleCell"><strong>Card Holder Name</strong></td>
                    <td class="shippingDataCell"><% out.println(payment.getCardHolder());%></td>
                </tr>
                <tr>
                    <td class="shippingTitleCell"><strong>Card Number</strong></td>
                    <td class="shippingDataCell"><% out.println(payment.getCardNumber());%></td>
                </tr>
                <tr>
                    <td class="shippingTitleCell"><strong>CVV</strong></td>
                    <td class="shippingDataCell"><% out.println(payment.getCvv());%></td>
                </tr>
                <tr>
                    <td class="shippingTitleCell"><strong>Expiry</strong></td>
                    <td class="shippingDataCell"><% out.println(payment.getExpiry());%></td>
                </tr>                            
            </table>
        </div>
        <div class="buttonFooter">
            <a href="welcome.jsp"><button class="button" id="nextButton">< Back</button></a>
        </div>        
    </body> 
</html>