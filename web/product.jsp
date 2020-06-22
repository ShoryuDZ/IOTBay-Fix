<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Controllers.ProductController"%>
<%@ page import="IOTBay.Models.*" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>IOTBay ~ Google Home Mini</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <%
        UserModel user;
        Object userSession = session.getAttribute("customer");
        if (userSession != null) {
            user = (UserModel)userSession;
        }
        else {
            user = new UserModel();
            session.setAttribute("customer", user);
        }
        
        String productID = request.getParameter("id");
        ProductController controller = new ProductController();
        ProductModel product = controller.getProduct(productID);
    %>
    <body>
        <div class="header">
            <img src="media/IOTBayLogo_Textless.png" alt="IOTBay Logo" class="headerLogo">
            <span class="headerItem">
                <% if (user instanceof CustomerModel) {%>
                <a href ="welcome.jsp"> <% out.print("Hello, " + user.getFirstName()); %> </a>
                <button class="headerItem" id="headerLogOut" onclick="logOut()">Log Out</button>
                <% }
                else {%>
                <a href="login.jsp"><button class="headerItem">Login</button></a>
                <a href="register.jsp"><button class="headerItem">Sign Up</button></a>
                <%}
                %>
            </span>
            <a href="cart.jsp"><img src="media/cart.png" alt="cart" class="headerItem" id="cartLink"></a>
        </div>
        <br>
        <span class="productPath"><a href="main.jsp">Home</a> > <% out.print(product.getFullName()); %></span>
        <div class ="mainContentBoxProduct">
            <table id="productsTable">
                <tr>
                    <td>
                        <div class ="productDetailBox">
                            <img id="productimage" src="<% out.print(product.getImageName()); %>" alt="A google home">
                        </div>
                    </td>
                    <td class="productDescription">
                        <span class="productTitleText"><% out.print(product.getFullName()); %></span>
                        <p> <% out.print(product.getProductDescription()); %></p>
                        <div class="productStatus">
                            <p>Category: <%= product.getCategory()%></p>
                            <p>Price: $<% out.print(product.getPrice()); %></p>
                            <p>Stock: <% out.print(product.getStock()); %> left</p>
                        </div>
                        <form action="ProductServlet" method="POST">    
                            <% session.setAttribute("addedProduct", product); %>
                            <button type="submit" class="productAddToCartt" id="addToCart">Add to cart</button> 
                        </form>
                    </td>
                </tr>
            </table>
        </div>
       
        <div class="buttonFooter">
            <a href="main.jsp"><button class="button" id="previousButton">< Home</button></a>
            <a href="cart.jsp"><button class="button" id="nextButton">Cart ></button></a>
        </div>
    </body>
</html>
