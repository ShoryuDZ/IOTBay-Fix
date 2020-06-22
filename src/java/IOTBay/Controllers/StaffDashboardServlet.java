    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.dao.*;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StaffDashboardServlet")
public class StaffDashboardServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        
        String submitter = request.getParameterNames().nextElement();
        String[] actions = submitter.split(":");
        
        try {
            DBConnector conn = new DBConnector();
            DBManager database = new DBManager(conn.openConnection()); 

            switch (actions[1]) {
                case "edit":
                    if (actions[0].equals("c")) {
                        response.sendRedirect("details.jsp?id=" + actions[2]);
                    }
                    if (actions[0].equals("p")) {
                        response.sendRedirect("productDetails.jsp?id=" + actions[2]);             
                    }
                    break;
                case "delete":
                    try { 
                        if (actions[0].equals("p")) {
                            database.deleteProduct(UUID.fromString(actions[2]));
                        }
                        if (actions[0].equals("c")) {
                            database.deleteCustomer(UUID.fromString(actions[2]));
                        }
                        response.sendRedirect("staffDashboard.jsp");
                    }
                    catch (Exception e) {
                        response.sendRedirect("staffDashboard.jsp?e=false");
                    }
                    break;
                case "add":
                    if (actions[0].equals("c")) {
                        response.sendRedirect("details.jsp");
                    }
                    if (actions[0].equals("p")) {
                        response.sendRedirect("productDetails.jsp");             
                    }
                    break;
            }
        }
        catch (Exception e) {
            response.sendRedirect("staffDashboard.jsp?e=false");
        }
    }
}
