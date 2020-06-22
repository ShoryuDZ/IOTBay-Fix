<%-- 
    Author     : ISD AUT2020 G45
--%>
<%@ page import="IOTBay.Models.*" %>
<%@ page import="IOTBay.Controllers.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View/Edit Details</title>
    </head>
    <body>
        <%
            CustomerDetailsController controller = new CustomerDetailsController();
            
            CustomerModel customer = (CustomerModel)session.getAttribute("customer");
            if (customer == null) {
                StaffModel staff = (StaffModel)session.getAttribute("staff");
                try {
                    String ID = request.getParameter("id");
                    customer = controller.getCustomer(ID);
                }
                catch (Exception e) {
                    customer = new CustomerModel();
                }
                
                session.setAttribute("customer", customer);
            }
        %>
        <form action ="CustomerDetailsServlet" method="POST">
            <div class="outerContentBoxTitle">
                <span class="outerContentBoxTitleText"><%= customer.getFirstName() == null ? "New Customer Account" : "Update " + customer.getFirstName() + "'s Account"%></span>
                <img src="media/IOTBayLogo.png" alt="IOTBay Logo" class="outerContentBoxTitleLogo" onClick="goHome()">
            </div>
            <div class="outerContentBox">
                <h2>Customer Details</h2>    
                <div class="registerFormTable">
                    <table id="ordersTable">
                        <tr>
                        <div class="fieldPack">
                            <td> 
                                <label for = "firstName"  class="fieldTitle">First Name:</label>
                                <input type="text" class="field" id="firstName" name="firstName" value = ${customer.firstName}>
                            </td>
                            <td>
                                <label for = "lastName"  class="fieldTitle">Last Name:</label>
                                <input type="text" class="field" id="lastName" name="lastName" value = ${customer.lastName}>
                            </td>
                            <td> 
                                <label for = "email"  class="fieldTitle">Email:</label>
                                <input type="text" class="field" id="email" name="email" value = ${customer.email}>
                            </td>
                        </div>
                        </tr>
                        <tr>
                        <div class="fieldPack">
                            <td> 
                                <label for = "phoneNumber"  class="fieldTitle">Phone Number</label>
                                <input type="text" class="field" id="phoneNumber" name="phoneNumber" value = ${customer.phoneNumber}>
                            </td>
                            <td>
                                <label for = "password"  class="fieldTitle">Password</label>
                                <input type="text" class="field" id="password" name="password" value = ${customer.password}>
                            </td>
                        </div>
                        </tr>
                    </table>
                    <hr>
                    <h2>Payment Details</h2>
                    <table id="ordersTable">
                        <tr>
                            <div class="fieldPack">
                                <td> 
                                    <label for = "CardHolder"  class="fieldTitle">Card Holder:</label>
                                    <input type="text" class="field" id="CardHolder" name="CardHolder" value = ${customer.payment.cardHolder}>
                                </td>
                                <td> 
                                    <label for = "CVV"  class="fieldTitle">CVV:</label>
                                    <input type="text" class="field" id="CVV" name="cvv" value = ${customer.payment.cvv == 0 ? null : customer.payment.cvv}>
                                </td>
                            </div>
                        </tr>
                        <tr>
                        <div class="fieldPack">
                            <td> 
                                <label for = "Card Number"  class="fieldTitle">Card Number:</label>
                                <input type="text" class="field" id="CardNumber" name="cardNumber" value = ${customer.payment.cardNumber}>
                            </td>
                            <td>
                                <label for = "expiry"  class="fieldTitle">Expiry:</label>
                                <input type="text" class="field" id="expiry" name="expiry" value = ${customer.payment.expiry}>
                            </td>
                        </div>
                        </tr>
                        <button class="button" type="submit" id="nextButton" name="DeletePaymentButton">Delete</button>
                    </table>
                    <hr>
                    <h2>Customer Address</h2>
                    <table id="ordersTable">
                        <tr>
                        <div class="fieldPack">
                            <td> 
                                <label for = "streetAddress"  class="fieldTitle">Street Address:</label>
                                <input type="text" class="field" id="streetAddress" name="streetAddress" value ="${customer.address.streetAddress}">
                            </td>
                            <td>
                                <label for = "suburb"  class="fieldTitle">Suburb:</label>
                                <input type="text" class="field" id="suburb" name="suburb" value = ${customer.address.suburb}>
                            </td>
                        </div>
                        </tr>
                        <tr>
                        <div class="fieldPack">
                            <td>
                                <label for = "state"  class="fieldTitle">State:</label>
                                <input type="text" class="field" id="state" name="state" value = ${customer.address.state}>
                            </td>
                            <td>
                                <label for = "country"  class="fieldTitle">Country:</label>
                                <input type="text" class="field" id="country" name="country" value = ${customer.address.country}>
                            </td>
                            <td>
                                <label for = "postcode"  class="fieldTitle">Postcode:</label>
                                <input type="text" class="field" id="country" name="postcode" value = ${customer.address.postcode == 0 ? null : customer.address.postcode}>
                            </td>
                        </div> 
                        </tr>
                        <button class="button" type="submit" id="nextButton" name="DeleteAddressButton">Delete</button>
                    </table>
                </div>
            </div>
            <div class="buttonFooter">
                <button class="button" id="nextButton" type="submit" name="UpdateButton">Update ></button>
                <button class="button" id="previousButton" type="submit" name="CancelButton">< Cancel</button>
            </div>
        </form>
    </body>
</html>
