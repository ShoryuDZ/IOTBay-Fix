<%--
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>IOTBay ~ Cart</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">  
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

        CartModel cart;
        Object cartSession = session.getAttribute("cart");
        if (cartSession != null) {
            cart = (CartModel)session.getAttribute("cart");
        }
        else {
            cart = new CartModel(user);
            session.setAttribute("cart", cart);
        }
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
        <div class="outerContentBoxContentBoxTitle">
            <span class="mainContentBoxTitleText" id="cartTitle">Your Cart</span>
        </div>
        <form action="CartServlet" method="POST">
            <div class="outerContentBox">                        
                <div class="contentBoxContent">
                    Your shopping cart contains <% out.print(cart.getCartItems().size()); %> product/s.
                    <p>Subtotal: $<% out.print(cart.getPrice());%></p>
                </div>

                <table id="ordersTable">

                    <tr>
                        <th class="orderHeaderCell">Product Image</th>
                        <th class="orderHeaderCell">Name</th>
                        <th class="orderHeaderCell">Price</th>
                        <th class="orderHeaderCell">Quantity</th>
                    </tr>
                    <% 
                        for (CartItemModel cartItem : cart.getCartItems()) { %>
                            <tr>
                                <td class="orderCell" id="imageCell">
                                    <img src="<% out.print(cartItem.getProduct().getImageName());%>" class="cartTableImage">
                                </td>
                                <td class="orderCell"><% out.print(cartItem.getProduct().getFullName()); %></td>
                                <td class="orderCell">$<% out.print(cartItem.getProduct().getPrice()); %></td>
                                <td class="orderCell">
                                    <!-- Following buttons and input is generated dynamically, so they are named dynamically with the CartItem id for identification later -->
                                    <button type="button" class="quantityButton" onclick="changeQuantity(-1, 'productQuantity:<%=cartItem.getID().toString()%>')">-</button>
                                    <input type="text" value="<% out.print(cartItem.getQuantity()); %>" size="2" class="quantityField" id="productQuantity:<%=cartItem.getID().toString()%>" name="productQuantity:<%=cartItem.getID().toString()%>">
                                    <button type="button" class="quantityButton" onclick="changeQuantity(1, 'productQuantity:<%=cartItem.getID().toString()%>')">+</button>
                                </td>
                            </tr>
                        <% }
                    %>
                </table>
                <br>
                <button class="button" name="clearButton">Clear Cart</button>
            </div>
            <div class="buttonFooter">
                <button class="button" id="previousButton" name="backButton">< Shop</button>
                <button type ="submit" class="button" id="nextButton" name="orderButton">Order ></button>
            </div>
        </form>
    </body>
</html>