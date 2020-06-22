    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WelcomeShippingSearchServlet")
public class WelcomeShippingSearchServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        try {
            String shippingQuery = request.getParameter("shippingDetailQuery");

            if (shippingQuery.equals("")) {
                throw new Exception();
            }
            response.sendRedirect("welcome.jsp?shippingq=" + shippingQuery);
        }
        catch (Exception e) {
            response.sendRedirect("welcome.jsp"); 
        }
    }
}
