    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class AddressModel {
    // private fields
    UUID id;
    String streetAddress;
    String suburb;
    String state;
    String country;
    int postcode;
    
    // Constructors
    //new address
    public AddressModel(String streetAddress, String suburb, String state, String country, int postcode){
        this.id = UUID.randomUUID();
        this.streetAddress = streetAddress;
        this.suburb = suburb;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
    }
    //constructor for db manager
    public AddressModel(UUID id, String streetAddress, String suburb, String state, String country, int postcode){
        this(streetAddress, suburb, state, country, postcode);
        setId(id);
    }
    
    public AddressModel(UUID id){
        this.id=id;
    }

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getStreetAddress() {
        return streetAddress;
    }
    
    public String getSuburb() {
        return suburb;
    }
    
    public String getState() {
        return state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public int getPostcode() {
        return postcode;
    }
    
    @Override
    public String toString() {
	      return streetAddress + " " + suburb + " " + state + " " + country + " " + postcode;
    }
  
    @Override
    public boolean equals(Object object) {
        try {
            AddressModel otherAddress = (AddressModel)object;
            return otherAddress.country.equals(this.country) && otherAddress.postcode == this.postcode && otherAddress.state.equals(this.state) && otherAddress.streetAddress.equals(this.streetAddress) && otherAddress.suburb.equals(this.suburb);
        }
        catch (Exception e) {
            return false;
        }
    }
}
