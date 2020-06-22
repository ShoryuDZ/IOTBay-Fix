    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import IOTBay.Models.CustomerModel;
import IOTBay.Models.dao.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String streetAddress = request.getParameter("streetAddress");
        String suburb = request.getParameter("suburb");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        
        int phone;
        try {
            phone = Integer.parseInt(request.getParameter("phone"));
        } 
        catch (Exception e) {
            phone = 0;
        }
          
        int postcode;
        try {
            postcode = Integer.parseInt(request.getParameter("postcode"));
        } 
        catch (Exception e) {
            postcode = 0;
        }
        
        Boolean hasAddressData = !streetAddress.isEmpty() || !suburb.isEmpty() || !state.isEmpty() || !country.isEmpty();
        Boolean hasPaymentData = false;
        CustomerModel customer = new CustomerModel(firstName,lastName, email, phone, password, streetAddress, suburb, state, country, postcode);
        
        DBManager manager;
        try {
            DBConnector conn = new DBConnector();
            manager = new DBManager(conn.openConnection());
        }
        catch (Exception e) {
            manager = (DBManager)session.getAttribute("manager");
        }
        validator.clear(session);

        if (!validator.validateEmail(email)){
            response.sendRedirect("register.jsp?error=true");
        } 
        else {
            try {
                CustomerModel exist = manager.findCustomer(email);
                if (exist != null) {
                    response.sendRedirect("register.jsp?error=true");
                } 
                else {
                    manager.addCustomer(customer, hasAddressData, hasPaymentData);
                    session.setAttribute("customer", customer);
                    response.sendRedirect("registerCard.jsp");
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }      
}