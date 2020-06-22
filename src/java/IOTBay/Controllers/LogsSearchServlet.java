    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogsSearchServlet")
public class LogsSearchServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        try {
            String dateQuery = request.getParameter("dateQuery");
            
            if (dateQuery.equals("")) {
                throw new Exception();
            }
            response.sendRedirect("logs.jsp?dateq=" + dateQuery);
       }
        catch (Exception e) {
            response.sendRedirect("logs.jsp"); 
        }
    }
}