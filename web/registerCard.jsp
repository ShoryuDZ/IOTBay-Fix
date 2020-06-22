<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@ page import="IOTBay.Models.CustomerModel" %>
<%@ page import="IOTBay.Models.dao.DBManager" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">  
        <title>IOTBay ~ Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="RegisterCardServlet" method="POST">
            <div class="outerContentBoxTitle">
                <span class="outerContentBoxTitleText">Register</span>
                <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo">
            </div>
            <div class="outerContentBox">
                <div class="contentBoxSubTitle">Payment Information</div>
                <div class="contentBoxContent">
                    <% 
                    try {
                        if (request.getParameter("error").equals("true")) { %>
                            Something was wrong with your submission. Please check your data and try again. Note, all fields are required if you decide to submit card details!
                        <% }
                        else {
                            throw new Exception();
                        }
                    }
                    catch (Exception e) { %>
                        This section is optional, and may be completed later upon order. <br>
                    <%
                    } %>
                </div>
                <hr>
                <div class="registerFormTable">
                    <table class="registerFormTable">
                        <tr>
                            <td colspan="4">
                                <div class="fieldPack">
                                    <div class="fieldTitle">Card Number:</div>
                                    <div><input type="text" class="field" placeholder="16 Digit Number" placeholder="xxxx-xxxx-xxxx-xxxx" name="cardNumber"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="fieldPack">
                                    <div class="fieldTitle">Card holder Name: </div>
                                    <div><input type="text" class="field" name="cardHolder"></div>
                                </div>
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Card Expiry:</div>
                                    <div><input type="text" class="field" placeholder="MM/YYYY" name="expiry"></div>
                                </div>
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Security Code</div>
                                    <div><input type="text" class="field" placeholder="CVV" name="cvv"></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="buttonFooter">
                <button class="button" id="previousButton" onclick="form.action='register.jsp';">< Previous</button>        
                <button type="submit" class="button" id="nextButton">Next ></button>
            </div>
        </form>
    </body>
</html>
