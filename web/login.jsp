<%-- 
    Author     : ISD AUT2020 G45
--%>

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
        <script src="js/script.js" type="text/javascript"></script>
        <title>IOTBay ~ Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <% 
        boolean isValid = true;
        try {
            if (request.getParameter("s").equals("false")) {
                isValid = false;
            }
        }
        catch (NullPointerException e) { 
            isValid = true; 
        }
    %>
    <body>
        <img src="media/IOTBayLogo.png" alt="IOTBay Logo" id="loginLogo" onclick="goHome()"> 
        <div class="outerContentBox" id="loginContentBox">
            <div class="contentBoxTitle">Log In</div>
            <br>
            <form action="LoginServlet" method="POST">
                <div class="fieldPack">
                    <div class="fieldTitle">Email</div>
                    <div>
                        <input type="text" class="field" name="email" placeholder="Enter your Email"
                            <% if (!isValid) { %>
                                required
                            <% } 
                        %>>                               
                    </div>
                </div>
                <div class="fieldPack">
                    <div class="fieldTitle">Password</div>
                    <div>
                        <input type="password" class="field" name="password" placeholder="Enter password"
                            <% if (!isValid) { %>
                                required
                            <% } 
                        %>>   
                    </div>
                </div>
                <div id="loginButtonHolder"><button type="submit" value="submit" class="button">Submit</button></div>
            </form>
            <div id="loginAssistantLinks">
                <% if (!isValid) { %>
                    <div class="linkPack"><span id="loginErrorText">Something appears to be wrong with your credentials. Please try again.</span> </div>
                <% } %>
                <div class="linkPack"><a href="register.jsp" class="link">New User? Create a new account</a></div>
            </div>
        </div>
    </body>
</html>
