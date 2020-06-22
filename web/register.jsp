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
        <title>IOTBay ~ Register</title>
        <meta charset="UTF-8">
        <script src="js/script.js" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>    
        <form action="RegisterServlet" method="POST" onsubmit="return isPasswordMatching()">
            <div class="outerContentBoxTitle">
                <span class="outerContentBoxTitleText">Register</span>
                <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo">
            </div>
            <div class="outerContentBox">
                <div class="contentBoxSubTitle">
                    Welcome 
                </div>
                <div class="contentBoxContent">
                    <% 
                    try {
                        if (request.getParameter("error").equals("true")) { %>
                            Something was wrong with your submission. Please check your data and try again. Note, each new account required a unique email!
                        <% }
                        else {
                            throw new Exception();
                        }
                    }
                    catch (Exception e) { %>
                        Here, you can register to become an official customer of IOTBay. This means we will autofill your details when you make orders, you will be able to log in and see the progress of your orders, and you may be eligible for certain member discounts. <br>
                    <%
                    } %>
                    (Fields marked with a * are mandatory)    
                </div>
                <hr>
                <div class="registerFormTable">
                    <table class="registerFormTable">
                        <tr class="registerFormRow">
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">First Name: *</div>
                                    <div><input type="text" class="field" name="firstName" placeholder="Paul" required></div>
                                </div>
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Last Name: *</div>
                                    <div><input type="text" class="field" name="lastName" placeholder="Sherman" required></div>
                                </div> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle" id="Password">Password: *</div>
                                    <div><input type="password" class="field" name="password" id="password" required></div>
                                </div>
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Confirm Password: *</div>
                                    <div><input type="password" class="field" id="confirmPassword" required></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Email: *</div>
                                    <div><input type="text" class="field" name="email" placeholder="P.Sherman@example.com" required></div>
                                </div> 
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Phone: *</div>
                                    <div><input type="text" class="field" name="phone" placeholder="61400123456" required></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div class="fieldPack">
                                    <div class="fieldTitle">Street Address:</div>
                                    <div><input type="text" class="field" name="streetAddress" placeholder="42 Wallaby Way"></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Suburb</div>
                                    <div><input type="text" class="field" name="suburb" placeholder="Sydney"></div>
                                </div> 
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Postcode:</div>
                                    <div><input type="text" class="field" name="postcode" placeholder="2000"></div>
                                </div> 
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">State:</div>
                                    <div><input type="text" class="field" name="state" placeholder="NSW"></div>
                                </div> 
                            </td>
                            <td>
                                <div class="fieldPack">
                                    <div class="fieldTitle">Country:</div>
                                    <div><input type="text" class="field" name="country" placeholder="Australia"></div>
                                </div> 
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="buttonFooter">
                <button type="submit" class="button" id="nextButton">Next</button>
            </div>
        </form>
    </body>
</html>
