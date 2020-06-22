    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class UserModel {
    // private fields
    UUID userId;
    String firstName;
    String lastName;
    int phoneNumber;
    String email;
    
    // Constructor (Default)
    public UserModel() {
        this.userId = UUID.randomUUID();
    }
  
    public UserModel(String userId, String firstName, String lastName, String email, int phoneNumber) {
        this.userId = UUID.fromString(userId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    // Constructor (4 Paramters)
    public UserModel(String firstName, String lastName, String email, int phoneNumber) {
        this(UUID.randomUUID().toString(), firstName, lastName, email, phoneNumber);
    }
   
    // Functional Methods
    
    // Getters & Setters for Public Access
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public int getPhoneNumber() {
        return phoneNumber;
    }
  
    public UUID getUserId() {
        return userId;
    }
    
    public void setUserId(UUID id) {
        this.userId = id;
    }
    
    @Override
    public boolean equals(Object object) {
        try {
            UserModel otheruser = (UserModel)object;
            return otheruser.email.equals(this.email) && otheruser.firstName.equals(this.firstName) && otheruser.lastName.equals(this.lastName) && otheruser.phoneNumber == this.phoneNumber;
        }
        catch (Exception e) {
            return false;
        }
    }

}
