    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;
import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class MainController {
    
    // Private Fields
    LinkedList<ProductModel> products;
    
    // Constructor
    public MainController() {
    }
    
    // Get Products Method
    public LinkedList<ProductModel> getProducts() throws ClassNotFoundException, SQLException { 
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.getAllProducts();
    }
    
     // Get Products with Query Method
    public LinkedList<ProductModel> getProducts(String query) throws ClassNotFoundException, SQLException { 
        LinkedList<ProductModel> allProducts = getProducts();
        LinkedList<ProductModel> queriedProducts = new LinkedList<>();
        
        for (ProductModel product : allProducts) {
            if (product.getFullName().toLowerCase().contains(query.toLowerCase()) || product.getCategory().toLowerCase().contains(query.toLowerCase())) {
                queriedProducts.add(product);
            }
        }
        
        return queriedProducts;
    }
    
    // Get Products for Main Page
    public LinkedList<ProductModel[]> getProductsForMain() throws ClassNotFoundException, SQLException { 
        LinkedList<ProductModel> queriedProducts = getProducts();
        return productSorter(queriedProducts);
    }
    
    // Return only products which match query
    public LinkedList<ProductModel[]> getProductsForMain(String query) throws ClassNotFoundException, SQLException {
        LinkedList<ProductModel> queriedProducts = getProducts(query);
        return productSorter(queriedProducts);
    }
    
    // Sorts products into arrays of 3
    LinkedList<ProductModel[]> productSorter(LinkedList<ProductModel> products) throws ClassNotFoundException, SQLException {
        LinkedList<ProductModel[]> productsForMain = new LinkedList<ProductModel[]>();
        
        while (!products.isEmpty()) { 
            int rowSize;
            if (products.size() < 3) { 
                rowSize = products.size();
            }
            else {
                rowSize = 3;
            }
            
            ProductModel[] productsArray = new ProductModel[rowSize];
            
            int i = 0;
            
            while (i < rowSize) {
                productsArray[i] = products.getFirst();
                products.removeFirst();
                ++i;
            }
            
            productsForMain.add(productsArray);
        }
        
        return productsForMain;
    }
    
}
