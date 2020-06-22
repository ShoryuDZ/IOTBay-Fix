    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        HttpSession session = request.getSession();
        CartModel cart = (CartModel)session.getAttribute("cart");
        ProductModel product = (ProductModel)session.getAttribute("addedProduct");
        
        cart.addToCart(product, 1);
        session.removeAttribute("addedProduct");
        session.setAttribute("cart", cart);
        
        response.sendRedirect("cart.jsp"); // Cart page 
    }
}
