<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.ShippingDetailModel"%>
<%@page import="IOTBay.Controllers.ShippingDetailsController"%>



<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Shipping Details</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%             
        ShippingDetailsController controller = new ShippingDetailsController();
        String id = (String)request.getParameter("id");
        ShippingDetailModel shippingDetail = controller.getShippingDetailModel(id);
        
    %>
    <body>                
        <div class="outerContentBoxTitle">
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">            
            <span class="outerContentBoxTitleText">Shipping Details</span>
        </div>   
        <div class="outerContentBox" id="shippingContentBox">
                        <table id="shippingTable" border="1"> 
                            <tr>
                                <td class="shippingTitleCell"><strong>Shipping ID</strong></td>
                                <td class="shippingDataCell"><% out.println(shippingDetail.getShippingId());%></td>
                            </tr>
                            <tr>
                                <td class="shippingTitleCell"><strong>Address</strong></td>
                                  <td class="shippingDataCell"><% out.println(shippingDetail.getAddress().toString());%></td>
                            </tr>
                            <tr>
                                <td class="shippingTitleCell"><strong>Shipping Method</strong></td>
                                <td class="shippingDataCell"><% out.println(shippingDetail.getShippingMethod());%></td>
                            </tr>
                            <tr>
                                <td class="shippingTitleCell"><strong>Shipping Date</strong></td>
                                <td class="shippingDataCell"><% out.println(shippingDetail.getShippingDate());%></td>
                            </tr>
                        </table>
                    </td>
                </tr>
        </div>
        <div class="buttonFooter">
            <a href="welcome.jsp"><button class="button" id="nextButton">< Back</button></a>
        </div>        
    </body> 
</html>
