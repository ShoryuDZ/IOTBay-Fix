<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@ page import="java.util.LinkedList"%>
<%@ page import="IOTBay.Models.*" %>
<%@ page import="IOTBay.Controllers.*" %>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>IOTBay ~ Main</title>
        <link rel="stylesheet" href="css/style.css" type="text/css"/>
        <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
        <script src="js/script.js" type="text/javascript"></script>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head> 
    <% 
        MainController controller = new MainController();

        UserModel user;
        Object customerSession = session.getAttribute("customer");
        Object staffSession = session.getAttribute("staff");
        if (customerSession != null && staffSession == null) {
      // if (customerSession != null) {
            user = (UserModel)customerSession;
        }
        else if (customerSession == null && staffSession != null){
            user = (UserModel)staffSession;
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
                <a href="cart.jsp"><img src="media/cart.png" alt="cart" class="headerItem" id="cartLink"></a>
                <% }
                else if (user instanceof StaffModel) { %>
                <a href ="staffDashboard.jsp"> <% out.print("Hello, " + user.getFirstName()); %> </a>
                <button class="headerItem" id="headerLogOut" onclick="logOut()">Log Out</button>
                <% }
                else {%>
                <a href="login.jsp"><button class="headerItem">Login</button></a>
                <a href="register.jsp"><button class="headerItem">Sign Up</button></a>   
                <a href="cart.jsp"><img src="media/cart.png" alt="cart" class="headerItem" id="cartLink"></a>
                <%}
                %>
             </span>
        </div>
        <div class="mainContentBoxTitle">
            <span class="mainContentBoxTitleText">Welcome!</span>
            <form action="MainServlet" method="GET">
                <input type="text" class="mainContentBoxTitleSearch" name="query" placeholder="&#xF002; Search Products">
                <input type="submit" hidden />
            </form>
        </div>   
        <div class="mainContentBox">
<!--            <label for="sort">Sorted by:</label>
            <select id="sort">
                <option value="recent">Date Added (most recent first)</option>
                <option value="rich">Price (highest to lowest)</option>
                <option value="cheap">Price (lowest to highest)</option>
            </select>-->
            <table id="productsTable">
                <% 
                    LinkedList<ProductModel[]> products;
                    String query = request.getParameter("q");
                    
                    if (query != null) {
                        products = controller.getProductsForMain(query);
                        %>
                        <p class="query-label">Showing search result(s) for: <strong><%=query%></strong></p>
                    <%
                    }
                    else {
                        products = controller.getProductsForMain();
                    }
                    
                    
                    for (ProductModel[] productArray : products) { %>
                        <tr>
                            <% for (ProductModel product : productArray) { %>
                                <td>
                                    <div class="productBox">
                                        <img src="<% out.print(product.getImageName()); %>">
                                        <p><% out.print(product.getProductBrand() + " " + product.getProductName() + " ~ $" + product.getPrice()); %></p>
                                        <a href="product.jsp?id=<% out.print(product.getID().toString()); %>"><button id="productButton">See Details</button></a>
                                    </div>
                                </td>
                            <% } %>
                        </tr>
                    <% } %>
            </table>
        </div>    
        <div class="buttonFooter">
            <%
            if (user instanceof CustomerModel) { %>
                <button class="button" id="previousButton" onclick="logOut()">< Log Out</button>
                <a href="cart.jsp"><button class="button" id="nextButton">Cart ></button></a>
                <%} 
            else if (user instanceof StaffModel) { %>
                <button class="button" id="nextButton" onclick="logOut()"> Log Out</button>
            <%}
                else {%>
                <a href="cart.jsp"><button class="button" id="nextButton">Cart ></button></a>
                <%}
                %>
        </div>
    </body>
</html>