    //Author     : ISD AUT2020 G45

package IOTBay.Models;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class LogModel implements Serializable {
    
    private UUID id;
    private String loginTime;   
    private String logoutTime; 
    private String date;
    private UUID userId;
    
    //for loading from db
    public LogModel(UUID id, String loginTime, String logoutTime, String date, UUID userid) {
        this.id = id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.date = date;
        this.userId = userid;
    }
    
    //new log - at login, log out time is set to the same time as login time
    public LogModel(UUID userid) {
        this.id = UUID.randomUUID();
        this.date = LocalDate.now().toString(); // yyyy-mm-dd
        this.loginTime = helpers.getTimeNow(); // HH:MM:SS
        this.userId = userid;
    }

    public void setLogoutTime() {
        this.logoutTime = helpers.getTimeNow();
    }
    
    public String getLoginTime() {
        return loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public String getDate() {
        return date;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }
}//end class
