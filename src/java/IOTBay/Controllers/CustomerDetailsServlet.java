    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CustomerDetailsServlet")
public class CustomerDetailsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        
        HttpSession session = request.getSession();
        StaffModel staff = (StaffModel)session.getAttribute("staff");
        CustomerModel customer = (CustomerModel)session.getAttribute("customer");

        if (request.getParameter("UpdateButton") != null) {
            DBManager database;

            try {
                DBConnector conn = new DBConnector();
                database = new DBManager(conn.openConnection());
            }
            catch (Exception e) {
                database = (DBManager)session.getAttribute("manager");
            }

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");                    
            String cardHolder = request.getParameter("CardHolder");
            String cardNumber = request.getParameter("cardNumber");
            String expiry = request.getParameter("expiry");
            String streetAddress = request.getParameter("streetAddress");
            String suburb = request.getParameter("suburb");                    
            String state = request.getParameter("state");
            String country = request.getParameter("country");

            int phoneNumber;
            try {
                phoneNumber = Integer.parseInt(request.getParameter("phoneNumber"));
            }
            catch (NumberFormatException e) {
                phoneNumber = 0;
            }

            int postcode;
            try {
                postcode = Integer.parseInt(request.getParameter("postcode"));
            }
            catch (NumberFormatException e) {
                postcode = 0;
            }

            int cvv;
            try {
                cvv = Integer.parseInt(request.getParameter("cvv"));
            }
            catch (NumberFormatException e) {
                cvv = 0;
            }

            customer.updateCustomer(firstName, lastName, email, phoneNumber, password, streetAddress, suburb, state, country, postcode, cardNumber, cvv, expiry, cardHolder);
            Boolean hasAddressData = !streetAddress.isEmpty() || !suburb.isEmpty() || !state.isEmpty() || !country.isEmpty() || postcode != 0;
            Boolean hasPaymentData = !cardNumber.isEmpty() || !cardHolder.isEmpty() || !expiry.isEmpty() || cvv != 0;

            try {
                if (database.findCustomer(customer.getCustomerId()) != null) {
                    database.updateCustomer(customer, hasAddressData, hasPaymentData);
                }
                else {
                    database.addCustomer(customer, hasAddressData, hasPaymentData);
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(CustomerDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (request.getParameter("DeletePaymentButton") != null) {
            try {
                DBConnector conn = new DBConnector();
                DBManager database = new DBManager(conn.openConnection());

                database.deletePaymentFromCustomer(customer);
                
                //update customers payment model
                PaymentModel payment = new PaymentModel(customer.getPaymentId());
                customer.setPayment(payment);
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CustomerDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if (request.getParameter("DeleteAddressButton") != null) {
            try {
                DBConnector conn = new DBConnector();
                DBManager database = new DBManager(conn.openConnection());

                database.deleteAddress(customer);
                
                //update customers payment model
                AddressModel address = new AddressModel(customer.getAddressId());
                customer.setAddress(address);
            } 
            catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CustomerDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (staff == null) {
            session.setAttribute("customer", customer);
            response.sendRedirect("welcome.jsp");
        }
        else {
            session.removeAttribute("customer");
            response.sendRedirect("staffDashboard.jsp");
        }
    }
}
