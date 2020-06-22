    //Author     : ISD AUT2020 G45


package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        try {	    
            String email = request.getParameter("email");
            DBConnector conn = new DBConnector();
            DBManager database = new DBManager(conn.openConnection());
            UserModel user = database.findUser(email);
            
            if (user != null) {
                String password = request.getParameter("password");
                CustomerModel customer = database.findCustomer(email);
                StaffModel staff = database.findStaff(email);
                
                if (customer != null && customer.getPassword().equals(password)) {
                    
                    HttpSession session = request.getSession(true);	    
                    session.setAttribute("customer", customer); 
                    
                    LogModel log = new LogModel(customer.getUserId());
                    session.setAttribute("log", log);
                    database.addLog(log);
                    
                    
                    response.sendRedirect("welcome.jsp"); //logged-in page 
                }
                else if (staff != null && staff.getPassword().equals(password)) { 
                    
                    HttpSession session = request.getSession(true);	    
                    session.setAttribute("staff", staff);
                    
                    LogModel log = new LogModel(staff.getUserId());
                    session.setAttribute("log", log);
                    database.addLog(log);
                    
                    response.sendRedirect("staffDashboard.jsp"); //logged-in page 
                }
                else {
                    throw new Exception();
                }
            }        
            else { 
                throw new Exception();
            } 
        }
        catch (Exception e) {
            response.sendRedirect("login.jsp?s=false"); //error page 
        }
    }
}
