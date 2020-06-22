    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.ShippingDetailModel;
import IOTBay.Models.dao.DBConnector;
import IOTBay.Models.dao.DBManager;
import java.sql.SQLException;

public class ShippingDetailsController {

    public ShippingDetailsController() {
    }
    
    public ShippingDetailModel getShippingDetailModel(String id) throws ClassNotFoundException, SQLException {
        
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.findShippingDetails(id);
    }
    
}
