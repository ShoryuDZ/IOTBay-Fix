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

@WebServlet("/RegisterCardServlet")
public class RegisterCardServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();
        String cardNumber = request.getParameter("cardNumber");
        String cardHolder = request.getParameter("cardHolder");
        String expiry = request.getParameter("expiry");
        
        int cvv;
        try {
            cvv = Integer.parseInt(request.getParameter("cvv"));
        } 
        catch (NumberFormatException e) {
            cvv = 000;
        }
        
        if (!expiry.isEmpty() && !cardNumber.isEmpty() && !cardHolder.isEmpty() && cvv != 000) {
            String year = expiry.substring(3); 
            String month = expiry.substring(0, 2);
            String formattedExpiry = year + "-" + month + "-01";

            CustomerModel customer = (CustomerModel)session.getAttribute("customer");
            customer.setPayment(cardNumber, cvv, formattedExpiry, cardHolder);

            DBManager manager;
            try {
                DBConnector conn = new DBConnector();
                manager = new DBManager(conn.openConnection());
            }
            catch (Exception e) {
                manager = (DBManager)session.getAttribute("manager");
            }

            validator.clear(session);


            if (!validator.validateCardNumber(cardNumber) || !validator.validateExpiry(expiry)){
                response.sendRedirect("registerCard.jsp?error=true");
            } 
            else {
                try {
                    manager.updateCustomerPaymentMethod(customer);
                    session.setAttribute("customer", customer);
                    response.sendRedirect("welcome.jsp");   
                } 
                catch (SQLException ex) {
                    Logger.getLogger(RegisterCardServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else {
            response.sendRedirect("welcome.jsp");
        }
    }      
}
