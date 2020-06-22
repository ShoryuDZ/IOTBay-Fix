<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.LogModel"%>
<%@page import="IOTBay.Models.UserModel"%>
<%@page import="IOTBay.Models.dao.*"%>
<%@ page import="IOTBay.Models.CustomerModel" %>
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
        <title>IOTBay ~ Logged Out</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <% 
            DBConnector conn = new DBConnector();
            DBManager database = new DBManager(conn.openConnection());
            LogModel log = (LogModel)session.getAttribute("log");
            
            log.setLogoutTime();
            database.updateLogForLogout(log);            
            
            session.invalidate();
        %>
        <div id="logOutContentBox">
            <img src="media/IOTBayLogo.png" alt="IOTBay Logo" id="logOutLogo"> 
            <div class="contentBoxTitle">You are now Logged Out!</div>
            <br>
            <div class="contentBoxContent" id="logOutContent">
                <p>Thanks for shopping with IOT Bay!</p>
                <div class="linkPack">
                    <a href="index.html" class="link">Back Home</a> <br>
                    <a href="main.jsp" class="link">Continue Shopping</a>
                </div>
            </div>
        </div>
    </body>
</html>
