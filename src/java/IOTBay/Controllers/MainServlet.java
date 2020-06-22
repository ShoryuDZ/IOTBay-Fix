    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        try {
            String query = request.getParameter("query");
            if (query.equals("")) {
                throw new Exception();
            }
            response.sendRedirect("main.jsp?q=" + query); // Main page with query
        }
        catch (Exception e) {
            response.sendRedirect("main.jsp"); // Main page 
        }
    }
}
