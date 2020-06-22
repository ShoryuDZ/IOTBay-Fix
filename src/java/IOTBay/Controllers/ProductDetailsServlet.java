    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.io.IOException;
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

@WebServlet("/ProductDetailsServlet")
public class ProductDetailsServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException { 	    

        HttpSession session = request.getSession();

        if (request.getParameter("CancelButton") == null) {
            ProductModel product = (ProductModel)session.getAttribute("product");
            DBManager database;

            try {
                DBConnector conn = new DBConnector();
                database = new DBManager(conn.openConnection());
            }
            catch (Exception e) {
                database = (DBManager)session.getAttribute("manager");
            }

            String category = request.getParameter("productCategory");
            String imageURL = request.getParameter("productImage");
            String brand = request.getParameter("productBrand");
            String name = request.getParameter("productName");                    
            String description = request.getParameter("productDesc");
            
            double price;
            try {
                price = Double.parseDouble(request.getParameter("productPrice"));
            }
            catch (NumberFormatException e) {
                price = 0.0;
            }
            
            int stock;
            try {
                stock = Integer.parseInt(request.getParameter("productStock"));
            }
            catch (NumberFormatException e) {//number field shouldn't let non-numbers through but we'll see :eyes:
                stock = 0;
            }

            product.updateProduct(name, brand, description, price, stock, category, imageURL);

            try {
                if (database.getProduct(product.getID().toString()) != null) {
                    database.updateProduct(product);
                }
                else {
                    database.addProduct(product);
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        session.removeAttribute("product");
        response.sendRedirect("staffDashboard.jsp");
    }
}