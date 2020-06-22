    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.DBConnector;
import IOTBay.Models.dao.DBManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class StaffDashboardController {
    
    public StaffDashboardController() {
    }
    
    public LinkedList<ProductModel> getProducts() throws ClassNotFoundException, SQLException { 
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.getAllProducts();
    }
    
    public LinkedList<CustomerModel> getCustomers() throws ClassNotFoundException, SQLException { 
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.getAllCustomers();
    }
}
