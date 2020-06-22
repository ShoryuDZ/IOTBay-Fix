    //Author     : ISD AUT2020 G45

package IOTBay.Models;

import java.util.UUID;

public class ProductModel {
    // Private Fields
    UUID id;
    String productName;
    String productBrand;
    String productDescription;
    int stock;
    double price;
    String category;
    String imageName;
    
    public ProductModel(){
        this.id = UUID.randomUUID();
    }
    
    // Constructor
    public ProductModel(String productID, String productName, String productbrand, String productDescription, double price, int stock, String category, String imageName) {
        this.id = UUID.fromString(productID);
        this.productName = productName;
        this.productBrand = productbrand;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageName = imageName;
    }
    
    public ProductModel(String productName, String productbrand, String productDescription, double price, int stock, String category, String imageName) {
        this(UUID.randomUUID().toString(), productName, productbrand, productDescription, price, stock, category, imageName);
    }
    
    // Updating Product Details
    public void updateProduct(String productName, String productbrand, String productDescription, double price, int stock, String category, String imageName) {
        this.productName = productName;
        this.productBrand = productbrand;
        this.productDescription = productDescription;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageName = imageName;
    }
    
    public UUID getID(){
        return id;
    }
    
    public String getProductName(){
        return productName;
    }
    
    public String getProductBrand(){
        return productBrand;
    }
    
    public String getProductDescription(){
        return productDescription;
    }
    
    public Double getPrice(){
        return price;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void sellStock(int stockSold) {
        this.stock -= stockSold;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getImageName(){
        return imageName;
    }
    
    public String getFullName() { 
        return productBrand + " " + productName;
    }
}
