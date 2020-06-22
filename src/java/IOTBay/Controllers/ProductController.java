    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.sql.SQLException;

public class ProductController {
    
    // Constructor (Default)
    public ProductController() { 
    }
    
    // Get Product from ID
    public ProductModel getProduct(String productID) throws ClassNotFoundException, SQLException { 
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.getProduct(productID);
    }
}
