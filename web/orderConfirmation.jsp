<%-- 
    Author     : ISD AUT2020 G45
--%>

<%@page import="IOTBay.Models.*"%>
<html>
    <head>
        <title>Order Confirmation</title>
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
            user = (CustomerModel)userSession;
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
        <form action="OrderConfirmationServlet" method="POST">
            <div class="outerContentBoxTitle" id="confirmationTitle">
                <span class="outerContentBoxTitleText">Order Confirmation</span>
            </div>
            <div class="outerContentBox">    
                <div class="row">
                    <div class="col-75">
                        <div class="row">
                            <div class="col-50">
                                <h2>Customer Information</h2>
                                <% if (user instanceof CustomerModel) { %>
                                    <div><input type="text" class="field" name="firstName" placeholder="First Name" value="<%=user.getFirstName()%>"></div> <br>
                                    <div><input type="text" class="field" name="lastName" placeholder="Last Name" value="<%=user.getLastName()%>"></div> <br>
                                    <div><input type="text" class="field" name="email" placeholder="Email" value="<%=user.getEmail()%>"></div> <br>
                                    <div><input type="text" class="field" name="phoneNumber" placeholder="Phone Number" value="<%=user.getPhoneNumber()%>"></div> <br>
                                <% } 
                                else { %>
                                    <div><input type="text" class="field" name="firstName" placeholder="First Name"></div> <br>
                                    <div><input type="text" class="field" name="lastName" placeholder="Last Name"></div> <br>
                                    <div><input type="text" class="field" name="email" placeholder="Email"></div> <br>
                                    <div><input type="text" class="field" name="phoneNumber" placeholder="Contact Number (e.g. 61401123456)"></div> <br>
                                <% } %>

                                <div class="details-heading">   
                                    <h2>Shipping Information</h2>
                                </div>
                                <% if (user instanceof CustomerModel) { %>
                                    <input type="button" id="deletePaymentBtn" onclick="clearShippingDetailsForm()" value="Clear">
                                    <div><input id ="order-conf-StreetAddress" type="text" class="field" name="streetAddress" placeholder="Address" value="${customer.address.streetAddress}"></div> <br>
                                    <div><input id ="order-conf-Suburb" type="text" class="field" name="suburb" placeholder="Suburb" value=${customer.address.suburb}></div> <br>
                                    <div><input id ="order-conf-State" type="text" class="field" name="state" placeholder="State" value=${customer.address.state}></div> <br>
                                    <div><input id ="order-conf-PostCode" type="text" class="field" name="postcode" placeholder="Postcode" value=${customer.address.postcode == 0 ? null : customer.address.postcode}></div><br>
                                    <div><input id ="order-conf-Country" type="text" class="field" name="country" placeholder="Country" value=${customer.address.country}></div> <br>
                                <% } 
                                else { %>
                                    <div><input id ="order-conf-StreetAddress" type="text" class="field" name="streetAddress"  placeholder="Address"></div> <br>
                                    <div><input id ="order-conf-Suburb" type="text" class="field" name="suburb" placeholder="Suburb"></div> <br>
                                    <div><input id ="order-conf-State" type="text" class="field" name="state" placeholder="State"></div> <br>
                                    <div><input id ="order-conf-PostCode" type="text" class="field" name="postcode" placeholder="Postcode"></div> <br>
                                    <div><input id ="order-conf-Country" type="text" class="field" name="country" placeholder="Country"></div> <br>
                                <% } %>
                                <label for="deliveryMethod">Delivery method:</label>
                                <select id="deliveryMethod" name="deliveryMethod" class="field">
                                    <option value="Normal">Normal ~ (3-5 business days)</option>
                                    <option value="Fast">Express ~ (1-2 business days)</option>
                                    <option value="SameDay">Same Day ~ (order placed before 1pm)</option>
                                </select>
                            </div>

                            <div class="col-50">
                                <div class="details-heading">   
                                    <h2>Payment</h2>    
                                </div>

                               <!-- in the value spot, use java tag get the clearpayment boolean value, if clear payment, value will be an empty string-->
                                    <% 
                                //boolean clearPayment = request.getParameter("clearPayment").equals("true");
                                if (user instanceof CustomerModel) { %>
                                <input type="button" id="deletePaymentBtn" onclick="clearPayment()" value="Clear">
                                <div><input id="order-conf-cardHolder" type="text" class="field" name="cardHolder" placeholder="Card Name" value=${customer.payment.cardHolder}></div> <br>
                                <div><input id="order-conf-cardNumber" type="text" class="field" name="cardNumber"  placeholder="Card Number (16 digits)" value=${customer.payment.cardNumber}></div> <br>
                                <div><input id="order-conf-expiry" type="text" class="field" name="expiry" placeholder="Expiry (MM/YYYY)" value="<% 
                                    CustomerModel customer = (CustomerModel)user;
                                    try {
                                        String expiry = customer.getPayment().getExpiry();
                                        String formattedExpiry = expiry.substring(5, 7) + "/" + expiry.substring(0, 4);
                                        out.print(formattedExpiry);
                                    }
                                    catch (Exception e) {
                                        out.print("");
                                    }
                                %>"></div> <br>
                                <div><input id="order-conf-cvv" type="text" class="field" name="CVV" placeholder="CVV" value=${customer.payment.cvv == 0 ? null : customer.payment.cvv}></div> <br>
                                <% } 
                                else { %>
                                <div><input id="order-conf-cardHolder" type="text" class="field" name="cardHolder"  placeholder="Card Name"></div> <br>
                                <div><input id="order-conf-cardNumber" type="text" class="field" name="cardNumber" placeholder="Card Number (xxxxxxxxxxxxxxxx)"></div> <br>
                                <div class="row">
                                    <div class="col-25"><input id="order-conf-expiry" type="text" class="field" name="expiry" placeholder="Expiry (MM/YYYY)"></div>
                                    <div class="col-25"><input id="order-conf-cardNumber" type="text" class="field" name="CVV" placeholder="CVV"></div>
                                </div>
                                <% } %>
                            </div>

                            <div class="col-50">
                                <h2>Cart</h2>
                                <% for (CartItemModel cartItem : cart.getCartItems()) { %>
                                    <p><a href="product.jsp?id=<%=cartItem.getProduct().getID().toString()%>"><% out.print(cartItem.getQuantity() + "x " + cartItem.getProduct().getFullName());%></a> <span class="price">$<%=cartItem.getPrice()%></span></p>
                                <%}%>
                                <hr>
                                <a>Total <span class="price"><b>$<%=cart.getPrice()%></b></span></a>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="buttonFooter">
                <a href="cart.jsp"><button class="button" id="previousButton">< Cart</button></a>
                <button type="submit" class="button" id="nextButton">Confirm ></button>
            </div>
        </form>
    </body>
</html>