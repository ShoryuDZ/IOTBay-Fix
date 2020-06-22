    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.time.LocalTime;

public class helpers {
    
    public static String getTimeNow() {
        String time = LocalTime.now().toString();// hh:mm:ss.ssssss
        String[] split = time.split("\\.", 2);
        return split[0]; // hh:mm:ss
    }
}
