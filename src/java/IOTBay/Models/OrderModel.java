    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class OrderModel {
    
    // Private Fields
    UUID orderID;
    UserModel user;
    CartModel cart;
    PaymentModel payment;
    ShippingDetailModel shipping;
    
    // Constructor (Default)
    public OrderModel() {
        orderID = UUID.randomUUID();
    }
    
    // Constructor (All Data)
    public OrderModel(String orderID, UserModel user, CartModel cart, PaymentModel payment, ShippingDetailModel shipping) {
        this.orderID = UUID.fromString(orderID);
        this.user = user;
        this.cart = cart;
        this.payment = payment;
        this.shipping = shipping;
    }
    
    // Constructor (New)
    public OrderModel(UserModel user, CartModel cart, PaymentModel payment, ShippingDetailModel shipping) {
        this(UUID.randomUUID().toString(), user, cart, payment, shipping);
    }
    
    // Constructor (to add cart later)
    public OrderModel(String orderID, UserModel user, PaymentModel payment, ShippingDetailModel shipping) {
        this(orderID, user, null, payment, shipping);
    }
    
    // Get All Values
    public ShippingDetailModel getShipping() {
        return shipping;
    }
    
    public UUID getOrderID() {
        return orderID;
    }
    
    public UserModel getUser() {
        return user;
    }
    
    public CartModel getCart() {
        return cart;
    }
    
    public PaymentModel getPayment() {
        return payment;
    }
    
    public void setDeliveryAddress(AddressModel address) {
        shipping.setAddress(address);
    }
    
    public void setCart(CartModel cart) {
        this.cart = cart;
    }
}
