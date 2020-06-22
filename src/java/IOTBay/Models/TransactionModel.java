    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class TransactionModel {
    
    // Private Fields
    UUID transactionID;
    PaymentModel paymentMethod;
    AddressModel billingAddress;
    double total;
    boolean paymentApproved;
    
    // Constructor (Default)
    public TransactionModel() {
        transactionID = UUID.randomUUID();
    }
    
    // Constructor (All Data)
    public TransactionModel(String transactionID, PaymentModel paymentMethod, AddressModel billingAddress, double total, boolean paymentApproved) {
        this.transactionID = UUID.fromString(transactionID);
        this.paymentMethod = paymentMethod;
        this.billingAddress = billingAddress;
        this.total = total;
        this.paymentApproved = paymentApproved;
    }
    
    // Constructor (New)
    public TransactionModel(PaymentModel paymentMethod, AddressModel billingAddress, double total, boolean paymentApproved) {
        this(UUID.randomUUID().toString(), paymentMethod, billingAddress, total, paymentApproved);
    }
}
