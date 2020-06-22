    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.LinkedList;
import java.util.UUID;

public class CartModel {
    // Private Fields
    LinkedList<CartItemModel> cartItems;
    UserModel user;
    double totalPrice;
    OrderModel order;
    
    // Constructor
    public CartModel(UserModel user) {
        this.cartItems = new LinkedList<>();
        this.user = user;
        this.totalPrice = 0.00;
    }
    
    // Functional Methods
    
    // Add a cartItem to the cart
    public void addToCart(CartItemModel cartItem) {
        cartItems.add(cartItem);
        totalPrice += cartItem.price;
    }
    
    // Add a product directly to the cart
    public void addToCart(ProductModel product, int quantity) {
        CartItemModel cartItem = new CartItemModel(product, quantity, this);
        addToCart(cartItem);
    }
    
    // Remove a known cartItem from the cart
    public void removeFromCart(CartItemModel cartItem) {
        cartItems.remove(cartItem);
        totalPrice -= cartItem.price;
    }
    
    // Clear all contents from the cart
    public void clearCart() {
        cartItems = new LinkedList<CartItemModel>();
    }
    
    // Get the total price of the cart
    public double getPrice() {
        return totalPrice;
    }
    
    // Get Cart Items
    public LinkedList<CartItemModel> getCartItems() {
        return cartItems;
    }
    
    // Get CartItem based on ID
    public CartItemModel getCartItemFromID(UUID id) {
        for (CartItemModel cartItem : cartItems) {
            if (cartItem.getID().equals(id)) {
                return cartItem;
            }
        }
        return null;
    }
    
    // Update Price if changes made
    public void updatePrice(){
        totalPrice = 0.0;
        for (CartItemModel cartItem : cartItems) { 
            totalPrice += cartItem.getPrice();
        }
    }
    
    // Set Cart Order
    public void setOrder(OrderModel order) {
        this.order = order;
        for (CartItemModel cartItem : cartItems) {
            cartItem.setOrder(order);
        }
    }
}
