    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class CartItemModel {
    // Private Fields
    UUID cartItemID;
    ProductModel product;
    int quantity;
    double price;
    CartModel cart;
    OrderModel order;
    
    // Constructor (from DB)
    public CartItemModel(String cartItemID, ProductModel product, int quantity, CartModel cart) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.price * quantity;
        this.cart = cart;
        this.cartItemID = UUID.fromString(cartItemID);
    }
    
    // Constructor (for brand new)
    public CartItemModel(ProductModel product, int quantity, CartModel cart) {
        this(UUID.randomUUID().toString(), product, quantity, cart);
    }
    
    // Functional Methods
    
    // Gets the price of the cart item
    public double getPrice() {
        return price;
    }
    
    // Get the product inside of the product item
    public ProductModel getProduct() {
        return product;
    }
    
    public int getQuantity() {
        return quantity;
    }

    // Allows one to change the quantity of the cart item
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        price = quantity * product.getPrice();
        cart.updatePrice();
    }
    
    // Sets the order of the cart item
    public void setOrder(OrderModel order) {
        this.order = order;
    }
    
    //Gets ID
    public UUID getID() {
        return cartItemID;
    }
    
    public OrderModel getOrder() {
        return order;
    }
}
