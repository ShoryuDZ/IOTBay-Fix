    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.DBConnector;
import IOTBay.Models.dao.DBManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class LogsController {
    
    public LogsController() {}
    
    // Get all logs for admin view
    public LinkedList<LogModel> getAllLogs() throws ClassNotFoundException, SQLException { 
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        return database.getAllLogs();
    }
    
    // Get logs for a user
    public LinkedList<LogModel> getUserLogs(UserModel user) throws ClassNotFoundException, SQLException {
        DBConnector conn = new DBConnector();
        DBManager database = new DBManager(conn.openConnection());
        
        String userID = user.getUserId().toString();
        LinkedList<LogModel> userLogs = database.makeLogListFromQuery("SELECT LOGID FROM IOTBAYLOG WHERE USERID = '" + userID + "'");
        return userLogs;
    }
    
    public LinkedList<LogModel> filterLogsByDateQuery(LinkedList<LogModel> logList, String query) {
        // check for order id or date
        LinkedList<LogModel> queriedLogs = new LinkedList<>();
        
        for (LogModel log : logList) {
            if (log.getDate().toLowerCase().contains(query.toLowerCase())) {
                queriedLogs.add(log);
            }
        }
        
        return queriedLogs;
    }
}
