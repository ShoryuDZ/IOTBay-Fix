    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.CustomerModel;
import IOTBay.Models.dao.*;
import java.sql.SQLException;
import java.util.UUID;

public class CustomerDetailsController {
    
    public CustomerDetailsController() {
    }
    
    public CustomerModel getCustomer(String ID) throws ClassNotFoundException, SQLException {
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        UUID id = UUID.fromString(ID);
        return database.findCustomer(id);
    }
}
