    //Author     : ISD AUT2020 G45

package IOTBay.Models;
import java.util.*;

public class CustomerModel extends UserModel {
    // private fields
    UUID customerId; // note: this is different to a user Id, esp for db purposes
    String password;
    AddressModel address;
    PaymentModel payment = new PaymentModel(); 
    
    // Constructors
    // completely new customer all fields filled 
    //params: fname, lname, email, phone, pw, streetAddress, suburb, state, country, postcode
    public CustomerModel(String firstName, String lastName, String email, int phoneNumber, String password,
                         String streetAddress, String suburb, String state, String country, int postcode) {
        this(firstName, lastName, email, phoneNumber, password);
        this.address = new AddressModel(streetAddress, suburb, state, country, postcode);
    }
    
    //new customer without an address or payment
    //params: id, fname, lname, email, phone, pw
    public CustomerModel(String firstName, String lastName, String email, int phoneNumber, String password) {
        super(firstName, lastName, email, phoneNumber);
        this.customerId = UUID.randomUUID();
        this.password = password;
    }
    
    //constructor for db manager to use when querying - no need for new uuid - all separate fields
    //params: id, fname, lname, email, phone, pw, address, payment models
    public CustomerModel(UUID userId, UUID customerId, String firstName, String lastName,  String email, int phoneNumber,
                         String password, AddressModel address, PaymentModel payment) {
        this(firstName, lastName, email, phoneNumber, password);
        this.setCustomerId(customerId);
        this.setUserId(userId);
        this.setAddress(address);
        this.setPayment(payment);
    }
    
    // Constructor for Dictionary
    public CustomerModel(Dictionary values) {
        this(values.get("firstName").toString(), values.get("lastName").toString(), values.get("email").toString(), Integer.parseInt(values.get("phone").toString()), values.get("password").toString(), values.get("streetAddress").toString(), values.get("suburb").toString(), values.get("state").toString(), values.get("country").toString(), Integer.parseInt(values.get("postcode").toString()));
    }
    
    // Constructor (Default)
    public CustomerModel() {
        super();
        this.customerId = UUID.randomUUID();
    }
    
    // Functional Methods
    
    // UpdateCustomer: if customer updates private details in detail.jsp, call upon this when setting new attribute
    //params: fname, lname, email, phone, pw, streetAddress, suburb, state, country, postcode
    public void updateCustomer(String firstName, String lastName, String email, int phoneNumber, 
            String password, String streetAddress, String suburb, String state, String country, 
            int postcode, String cardNumber, int cvv, String expiry, String cardHolder){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = new AddressModel(streetAddress, suburb, state, country, postcode);
        setPayment(cardNumber, cvv, expiry, cardHolder);
    }
    
    // Gets whole name for Welcome Page
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    // Gets contact details for Welcome Page
    public String getContactDetails() {
        return "Email: " + email + " & Phone: " + phoneNumber;
    }

    public AddressModel getAddress() {
        return address;
    }
    
    public UUID getAddressId() {
        return this.getAddress().getId();
    }//todo - get rid of this and redirect all mentions
    
    public void setAddress(AddressModel address) {
        this.address = address;
    }
    
    public PaymentModel getPayment() {
        return payment;
    }
    
    public UUID getPaymentId() {
        return payment.getId();
    }//todo - get rid of this and redirect all mentions
    
    // Allows retrospective saving of payment to customer
    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }

    public void setPayment(String cardNum, int cvv, String expiry, String cardHolder) {
        this.payment = new PaymentModel(cardNum, cvv, expiry, cardHolder);
    }

    public String getPassword() {
        return password;
    }    

    public UUID getCustomerId() {
        return customerId;
    }
    
    private void setCustomerId(UUID id) {
        this.customerId = id;
    }
    
}
