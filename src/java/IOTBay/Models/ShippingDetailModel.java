    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.io.Serializable;
import java.util.UUID;

public class ShippingDetailModel implements Serializable {

    UUID shippingID;  
    AddressModel address;
    String shippingMethod;
    String shippingDate;

    public ShippingDetailModel(UUID shippingID, AddressModel address, String deliveryMethod, String shippingDate) {
        this.shippingID = shippingID;
        this.address = address;
        this.shippingDate = shippingDate;
        this.shippingMethod = deliveryMethod;
    }
    
    public ShippingDetailModel(AddressModel address, String deliveryMethod, String shippingDate) { 
        this(UUID.randomUUID(), address, deliveryMethod, shippingDate);
    }
    
    // Getters & Setters
    public AddressModel getAddress() {
        return address;
    }
   
    public String getShippingDate() {
        return shippingDate;
    }
    
    public String getShippingMethod() {
        return shippingMethod;
    }
    
    public UUID getShippingId() {
        return shippingID;
    }
    
    public void setAddress(AddressModel address) {
        this.address = address;
    }
}

