    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.PaymentModel;
import IOTBay.Models.dao.DBConnector;
import IOTBay.Models.dao.DBManager;
import java.sql.SQLException;

public class PaymentDetailsController {

    public PaymentDetailsController() {
    }
    
    public PaymentModel getPaymentModel(String id) throws ClassNotFoundException, SQLException {
        
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.findPaymentMethod(id);
    }
    
}
