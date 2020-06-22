    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.AddressModel;
import IOTBay.Models.CartModel;
import IOTBay.Models.CustomerModel;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import IOTBay.Models.OrderModel;
import IOTBay.Models.PaymentModel;
import IOTBay.Models.ShippingDetailModel;
import IOTBay.Models.UserModel;
import IOTBay.Models.dao.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/OrderConfirmationServlet")
public class OrderConfirmationServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String streetAddress = request.getParameter("streetAddress");
        String suburb = request.getParameter("suburb");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String cardNumber = request.getParameter("cardNumber");
        String cardHolder = request.getParameter("cardHolder");
        String expiry = request.getParameter("expiry");
        
        int cvv;
        try {
            cvv = Integer.parseInt(request.getParameter("CVV"));
        } 
        catch (NumberFormatException e) {
            cvv = 000;
        }
        
        int phone;
        try {
            phone = Integer.parseInt(request.getParameter("phoneNumber"));
        } 
        catch (NumberFormatException e) {
            phone = 0;
        }
          
        int postcode;
        try {
            postcode = Integer.parseInt(request.getParameter("postcode"));
        } 
        catch (NumberFormatException e) {
            postcode = 0;
        }
        
        if (validator.validateExpiry(expiry)) {
            String year = expiry.substring(3); 
            String month = expiry.substring(0, 2);
            expiry = year + "-" + month + "-01";
        }
                
        DBManager manager;
        try {
            DBConnector conn = new DBConnector();
            manager = new DBManager(conn.openConnection());
        }
        catch (Exception e) {
            manager = (DBManager)session.getAttribute("manager");
        }
        validator.clear(session);

        try {
            UserModel thisUser = new UserModel(firstName, lastName, email, phone);
            CustomerModel sessionUser = (CustomerModel)session.getAttribute("customer");
            if (!thisUser.equals(sessionUser)) {
                manager.addUser(thisUser);
            }
            else {
                thisUser.setUserId(sessionUser.getUserId());
            }

            CartModel cart = (CartModel)session.getAttribute("cart");

            PaymentModel thisPayment = new PaymentModel(cardNumber, cvv, expiry, cardHolder);
            PaymentModel sessionPayment = sessionUser.getPayment();
            if (!thisPayment.equals(sessionPayment)) {
                manager.addPayment(thisPayment);
            }
            else {
                thisPayment.setId(sessionPayment.getId());
            }

            AddressModel thisAddress = new AddressModel(streetAddress, suburb, state, country, postcode);
            AddressModel sessionAddress = sessionUser.getAddress();
            if (!thisAddress.equals(sessionAddress)) {
                manager.addAddress(thisAddress);
            }
            else {
                thisAddress.setId(sessionAddress.getId());
            }
            ShippingDetailModel shipping = new ShippingDetailModel(thisAddress, "Fast", java.time.LocalDate.now().toString());

            OrderModel order = new OrderModel(thisUser, cart, thisPayment, shipping);
            cart.setOrder(order);

            manager.addOrder(order);
            manager.addCart(cart);
            session.removeAttribute("cart");
            response.sendRedirect("confirmed.html");
        } 
        catch (SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("orderConfirmation.jsp?error=true");
        } 
    }      
}