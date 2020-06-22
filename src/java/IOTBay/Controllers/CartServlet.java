    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import java.util.Collections;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    
        
        HttpSession session = request.getSession();
        CartModel cart = (CartModel)session.getAttribute("cart");
        
        if (request.getParameter("clearButton") == null) {
            for (String cartField : Collections.list(request.getParameterNames())) {
                if (cartField.split(":")[0].equals("productQuantity")) {
                    UUID id = UUID.fromString(cartField.split(":")[1]);
                    int quantity = Integer.parseInt(request.getParameter("productQuantity:" + id.toString()));
                    if (quantity < 1) {
                        cart.removeFromCart(cart.getCartItemFromID(id));
                    }
                    else {
                        cart.getCartItemFromID(id).setQuantity(quantity);
                    }
                }
            }

            session.setAttribute("cart", cart);

            if (request.getParameter("orderButton") == null || cart.getCartItems().isEmpty()) {
                response.sendRedirect("main.jsp"); // Main page
            }
            else if (request.getParameter("backButton") == null) {  
                response.sendRedirect("orderConfirmation.jsp"); // Confirm Order Page
            }
        }
        else {
            UserModel user = (UserModel)session.getAttribute("customer");
            cart = new CartModel(user);
            session.setAttribute("cart", cart);
            response.sendRedirect("main.jsp"); // Main page
        }
    }
}
