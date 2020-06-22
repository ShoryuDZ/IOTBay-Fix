    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class StaffModel extends UserModel {
    UUID staffId; // note: this is different to a user Id, esp for db purposes
    String password;
    boolean isAdmin;
    String role;
    
    // Parametised Constructor
    public StaffModel(String userID, String staffId, String firstName, String lastName, String email, int phoneNumber, String password, boolean isAdmin, String role) {
        super(userID, firstName, lastName, email, phoneNumber);
        this.staffId = UUID.fromString(staffId);
        this.password = password;
        this.isAdmin = isAdmin;
        this.role = role;
    }
    
    // Getters and Setters
    public String getPassword() { 
        return password;
    }
    
    public boolean isStaffAdmin() {
        return isAdmin;
    }
    
}
