    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.ProductModel;
import IOTBay.Models.dao.*;
import java.sql.SQLException;

public class ProductDetailsController {
    
    public ProductDetailsController() {
    }
    
    public ProductModel getProduct(String ID) throws ClassNotFoundException, SQLException {
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        return database.getProduct(ID);
    }
}
