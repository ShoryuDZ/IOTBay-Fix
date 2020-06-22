    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.CustomerModel;
import IOTBay.Models.UserModel;
import IOTBay.Models.dao.DBConnector;
import IOTBay.Models.dao.DBManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {	    
        try {
            HttpSession session = request.getSession();
            CustomerModel customer = (CustomerModel)session.getAttribute("customer");
            UUID id = customer.getCustomerId();
            
            DBConnector conn = new DBConnector();
            DBManager database = new DBManager(conn.openConnection());

            database.deleteCustomer(id);
            response.sendRedirect("logout.jsp");
        } 
        catch (SQLException ex) {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}


 
