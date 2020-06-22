    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WelcomeOrderSearchServlet")
public class WelcomeOrderSearchServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        try {
            String orderQuery = request.getParameter("orderQuery");

            if (orderQuery.equals("")) {
                throw new Exception();
            }
            response.sendRedirect("welcome.jsp?orderq=" + orderQuery);
        }
        catch (Exception e) {
            response.sendRedirect("welcome.jsp"); 
        }
    }
}
