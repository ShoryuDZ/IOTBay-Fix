    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class PaymentModel {
    // private fields
    UUID id;
    String cardNumber;
    int cvv;
    String expiry; // date type in java sql is yyyy-mm-dd
    String cardHolder;
    
    // Constructors
    //default
    public PaymentModel(){
        this.id = UUID.randomUUID();
    }
    
    //new payment method
    public PaymentModel(String cardNumber, int cvv, String expiry, String cardHolder) {
        this();
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiry = expiry;
        this.cardHolder = cardHolder;
    }
    //constructor for loading payment method objects
    public PaymentModel(UUID id, String cardNumber, int cvv, String expiry, String cardHolder) {
        this(cardNumber, cvv, expiry, cardHolder);
        this.setId(id);
    }
    public PaymentModel(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public String getExpiry() {
        return expiry; // yyyy-mm-dd
    }

    public String getCardHolder() {
        return cardHolder;
    }    
    
    @Override
    public boolean equals(Object object) {
        try {
            PaymentModel otherPayment = (PaymentModel)object;
            return otherPayment.cardHolder.equals(this.cardHolder) && otherPayment.cardNumber.equals(this.cardNumber) && otherPayment.expiry.equals(this.expiry) && otherPayment.cvv == this.cvv;
        }
        catch (Exception e) {
            return false;
        }
    }
}
