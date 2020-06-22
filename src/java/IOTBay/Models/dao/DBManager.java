    //Author     : ISD AUT2020 G45

package IOTBay.Models.dao;

import IOTBay.Models.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.UUID;

/* 
* DBManager is the primary DAO class to interact with the database. 
* Complete the existing methods of this classes to perform CRUD operations with the db.
*/

public class DBManager {

    private Statement statement;

    public DBManager(Connection conn) throws SQLException {       
       statement = conn.createStatement();   
    }
    
    //Find user by email and passwordi n the database
    //when using this method, you need to use a try catch to handle the case where
    //it throws an error when there is no such email in the db
    public CustomerModel findCustomer(String email) throws SQLException {       
        // Find user and check status
        UserModel user = findUser(email);
        if (user != null) {
            
            //run query for the registered specific data
            String query = "select * from iotbaycustomer where userid = '" + user.getUserId().toString() + "'";
            ResultSet customerResultSet = statement.executeQuery(query);

            //if there is a customer with such email
            if (customerResultSet.next()) {
                //customer table attributes
                String customerId = customerResultSet.getString("customerid");
                String password = customerResultSet.getString("customerpassword");
                String addressId = customerResultSet.getString("AddressId");
                String paymentId = customerResultSet.getString("PaymentId");

                AddressModel address = null;
                PaymentModel paymentMethod = null;

                //if address known
                if (addressId != null) {
                    address = findAddress(addressId);
                }

                //if payment method known
                if (paymentId != null) {
                    paymentMethod = findPaymentMethod(paymentId);
                }

                return new CustomerModel(user.getUserId(), UUID.fromString(customerId), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), password, address, paymentMethod);
            }
        }
        return null; // can't find customer
    }
    
    public CustomerModel findCustomer(UUID id) throws SQLException {
        String query = "SELECT USERID FROM IOTBAYCUSTOMER WHERE CUSTOMERID = '" + id.toString() + "'";
        ResultSet userIDResults = statement.executeQuery(query);
        
        if (userIDResults.next()) {
            String userID = userIDResults.getString("USERID");
            query = "SELECT EMAIL FROM IOTBAYUSER WHERE USERID = '" + userID + "'";
            ResultSet emailResult = statement.executeQuery(query);
            
            if (emailResult.next()) {
                String email = emailResult.getString("EMAIL");
                return findCustomer(email);
            }
        }
        return null;
    }
    
    public LinkedList<CustomerModel> getAllCustomers() throws SQLException {
        LinkedList<CustomerModel> customers = new LinkedList<>();
        String query = "SELECT EMAIL FROM IOTBAYUSER";
        ResultSet productResultSet = statement.executeQuery(query);
        LinkedList<String> userEmails = new LinkedList<>();
       
        while (productResultSet.next()) { 
            String email = productResultSet.getString("EMAIL");
            userEmails.add(email);
        }
        
        for (String email : userEmails) {
            CustomerModel customer = findCustomer(email);
            if (customer != null) {
                customers.add(customer);
            }
        }

        return customers;
    }
    
    public UserModel findUser(String email) throws SQLException { 
        String query = "select * from iotbayuser where email = '" + email + "'"; // finds user with email
        ResultSet userResultSet = statement.executeQuery(query);
        
        if (userResultSet.next()) {
            //get usertable attributes
            String userId = userResultSet.getString("userid");
            String fname = userResultSet.getString("firstname");
            String lname = userResultSet.getString("lastname");
            int phone = userResultSet.getInt("phone");
            
            return new UserModel(userId, fname, lname, email, phone);
        }
        return null;
    }
    
    public UserModel findUser(UUID id) throws SQLException { 
        String query = "select * from iotbayuser where userID = '" + id.toString() + "'"; // finds user with email
        ResultSet userResultSet = statement.executeQuery(query);
        
        if (userResultSet.next()) {
            //get usertable attributes
            String email = userResultSet.getString("email");
            String fname = userResultSet.getString("firstname");
            String lname = userResultSet.getString("lastname");
            int phone = userResultSet.getInt("phone");
            
            return new UserModel(id.toString(), fname, lname, email, phone);
        }
        return null;
    }
    
    // Get Staff Model using email
    public StaffModel findStaff(String email) throws SQLException { 
        UserModel user = findUser(email);
        if (user != null) {
            
            // run query for the registered specific data
            String query = "select * from iotbaystaff where userid = '" + user.getUserId().toString() + "'";
            ResultSet staffResultSet = statement.executeQuery(query);

            // if there is a staff with such email
            if (staffResultSet.next()) {
                // Staff table attributes
                String staffId = staffResultSet.getString("staffid");
                String password = staffResultSet.getString("staffpassword");
                boolean isAdmin = staffResultSet.getBoolean("isAdmin");
                String role = staffResultSet.getString("staffrole");
                
                return new StaffModel(user.getUserId().toString(), staffId, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), password, isAdmin, role);
            }
        }
        return null;
    }
    
    
    public AddressModel findAddress(String addressId) throws SQLException {
        String query = "select * from iotbayAddress where addressId = '"+addressId+"'";
        ResultSet addressResultSet = statement.executeQuery(query);
        
        if (addressResultSet.next()) {
            //unpack result set
            String streetAddress = addressResultSet.getString("Streetaddress");
            String suburb = addressResultSet.getString("suburb");
            String country = addressResultSet.getString("country");
            String state = addressResultSet.getString("addressstate");
            int postcode = addressResultSet.getInt("postcode");
            
            return new AddressModel(UUID.fromString(addressId), streetAddress, 
                                                    suburb, country, state, postcode);
        } else {
            return null;
        }
    }
    
    public PaymentModel findPaymentMethod(String paymentId) throws SQLException {
        PaymentModel payment = null;
        String query = "select * from iotbaypaymentmethod where paymentId = '"+paymentId+"'";
        ResultSet paymentResultSet = statement.executeQuery(query);
        
        if (paymentResultSet.next()) {
            String cardnum = paymentResultSet.getString("cardnum");
            int cvv = paymentResultSet.getInt("cvv");
            String cardHolderName = paymentResultSet.getString("cardname");
            String expiry = paymentResultSet.getString("expiry");
            
            payment = new PaymentModel(UUID.fromString(paymentId), cardnum, cvv, expiry, cardHolderName);
        }
        
        return payment;
    }
    
    // Add a user into the database
    public void addUser(UserModel user) throws SQLException {
        String query = "insert into iotbayuser (userid, firstname, lastname, phone, email) values " +
                "('" + user.getUserId().toString() + "', '" + user.getFirstName() + "', '" + user.getLastName()
                + "', '" + Integer.toString(user.getPhoneNumber()) + "', '" + user.getEmail() + "')";
        statement.executeUpdate(query);  
    }

    // Add a customer into the database   
    public void addCustomer(CustomerModel customer, boolean hasAddressData, boolean hasPaymentData) throws SQLException {  
        //this query updates the customer row in both the supertype user and the subtype customer
        //base query: user without address or payment info
        addUser(customer);  

        String query = "insert into iotbaycustomer (customerid, userid, customerpassword)"
                + "values ('" + customer.getCustomerId().toString() + "', '" + customer.getUserId().toString() + "', '"
                + customer.getPassword() + "')";
        statement.executeUpdate(query);   
        
        if (hasAddressData) {//if customer has an address, update that attribute too
            updateCustomerAddress(customer);
        }
        
        if (hasPaymentData) { // if the customer has a payment method, update that attribute too
            updateCustomerPaymentMethod(customer);
        }
    }
    
    // Update a Customer in the Database
    public void updateCustomer(CustomerModel customer, boolean hasAddressData, boolean hasPaymentData) throws SQLException {  
        String query = "update IOTBAYUSER SET firstname ='" + customer.getFirstName() + "', lastname ='" + customer.getLastName() + "', phone ='" + Integer.toString(customer.getPhoneNumber()) + "', email ='" + customer.getEmail() + "' WHERE userid = '" + customer.getUserId().toString() + "'";
        statement.executeUpdate(query);   

        query = "update iotbaycustomer SET customerpassword = '" + customer.getPassword() + "' WHERE customerid = '" + customer.getCustomerId().toString() + "'";
        statement.executeUpdate(query);   
        
        if (hasAddressData) { //if customer has an address, update that attribute too
            updateCustomerAddress(customer);
        }
        
        if (hasPaymentData) { // if the customer has a payment method, update that attribute too
            updateCustomerPaymentMethod(customer);
        }
    }
    
    //customer model must have a filled payment model
    public void updateCustomerPaymentMethod(CustomerModel customer) throws SQLException {  
        //look for the paymentid row existing
        PaymentModel payment = customer.getPayment();

        String paymentId = payment.getId().toString();
        String query = "select * from iotbaypaymentmethod where paymentId = '" + paymentId + "'";//gets payment with the given paymentid
        ResultSet addressResults  = statement.executeQuery(query);
        
        if (!addressResults.next()) { // must make a payment row before can add the FK to customer row
            addPayment(payment);
        }
        String update = "update iotbayCustomer set paymentId = '" + paymentId + "' where customerid = '" + customer.getCustomerId() + "'";
            statement.executeUpdate(update);
    }

    //customermodel must have a filled address model already
    public void updateCustomerAddress(CustomerModel customer) throws SQLException {
        //look for the address row existing
        AddressModel address = customer.getAddress();
        String addressId = address.getId().toString();
        String query = "select * from iotbayaddress where addressId = '" 
                + addressId + "'";//gets payment with the given paymentid
        ResultSet addressResults  = statement.executeQuery(query);
        
        if (!addressResults.next()) { // must make a payment row before can add the FK to customer row
            addAddress(address);
        }
        
        String update = "update iotbayCustomer set addressid = '" 
                + customer.getAddressId() + "' where customerid = '" + customer.getCustomerId() + "'";
        statement.executeUpdate(update);
    }
    
    //delete a customer from the database (using ID)
    public void deleteCustomer(UUID id) throws SQLException {       
       String delete = "delete from iotbaycustomer where customerid = '" + id.toString() + "'";
       statement.execute(delete);
    }
    
    // Get All Products
    public LinkedList<ProductModel> getAllProducts() throws SQLException { 
        LinkedList<ProductModel> products = new LinkedList<>();
        String query = "SELECT PRODUCTID FROM IOTBAYPRODUCT";
        ResultSet productResultSet = statement.executeQuery(query);
        LinkedList<String> productIDs = new LinkedList<>();
       
        while (productResultSet.next()) { 
            String productID = productResultSet.getString("productid");
            productIDs.add(productID);
        }
        
        for (String productID : productIDs) {
            ProductModel product = getProduct(productID);
            products.add(product);
        }

        return products;
    }
    
    // Get A Specific Product
    public ProductModel getProduct(String productID) throws SQLException { 
        String query = "SELECT * FROM IOTBAYPRODUCT WHERE PRODUCTID = '" + productID + "'";
        ResultSet productResultSet = statement.executeQuery(query);
        
        if (productResultSet.next()) { 
            String brand = productResultSet.getString("brand");
            String name = productResultSet.getString("productname");
            double price = productResultSet.getDouble("price");
            int stock = productResultSet.getInt("stock");
            String category = productResultSet.getString("category");
            String description = productResultSet.getString("productdescription");
            String imageURL = productResultSet.getString("imageURL");
            
            return new ProductModel(productID, name, brand, description, price, stock, category, imageURL);
        }
        return null;
    }
    
    // Delete a product based on ID
    public void deleteProduct(UUID id) throws SQLException {
        String delete = "DELETE FROM IOTBAYPRODUCT WHERE PRODUCTID = '" + id.toString() + "'";
        statement.execute(delete);
    }
    
    public void deletePaymentFromCustomer(CustomerModel customer) throws SQLException {
        PaymentModel payment = customer.getPayment();
        
        String delete = "UPDATE iotbaypaymentmethod SET CARDNUM = NULL, CVV = NULL, CARDNAME = NULL, EXPIRY = NULL where paymentid = '" + payment.getId().toString() + "'";
        statement.execute(delete);
    }
    
    public void deleteAddress(CustomerModel customer) throws SQLException {
        AddressModel address = customer.getAddress();
        
        String delete = "UPDATE IOTBAYADDRESS SET STREETADDRESS = NULL, SUBURB = NULL, COUNTRY = NULL, ADDRESSSTATE = NULL, POSTCODE = NULL WHERE ADDRESSID = '" + address.getId().toString() + "'";
        statement.execute(delete);
    }
    
    // Update a product passed in
    public void updateProduct(ProductModel product) throws SQLException {
        String update = "UPDATE IOTBAYPRODUCT set productname = '" + product.getProductName() + "', brand = '" + product.getProductBrand() + "', price = " + product.getPrice() + ", stock = " + product.getStock() + ", category = '" + product.getCategory() + "', productdescription = '" + product.getProductDescription() + "', imageURL = '" + product.getImageName() + "' WHERE PRODUCTID = '" + product.getID().toString() + "'";
        statement.executeUpdate(update);
    }
    
    public void updateProductStock(ProductModel product) throws SQLException {
        String update = "UPDATE IOTBAYPRODUCT set stock = " + product.getStock() + " WHERE PRODUCTID = '" + product.getID().toString() + "'";
        statement.executeUpdate(update);
    }
    
    // Add New Product
    public void addProduct(ProductModel product) throws SQLException {
        String query = "INSERT INTO IOTBAYPRODUCT VALUES ('" + product.getID().toString() + "', '" + product.getProductBrand() + "', '" + product.getProductName() + "', " + product.getPrice() + ", " + product.getStock() + ", '" + product.getCategory() + "', '" + product.getProductDescription() + "', '" + product.getImageName() + "')";
        statement.execute(query);
    }
    
    //find all logs for a user
    public LinkedList<LogModel> getUserLogs(String userId) throws SQLException { 
        LinkedList<LogModel> logs = new LinkedList<>();
        LinkedList<String> logIds = new LinkedList<>();
        String query = "select logId from iotbaylog where userid = '" + userId + "'";
        return makeLogListFromQuery(query);
    }
    
    // Get A Specific Product
    public LogModel getLog(String logId) throws SQLException { 
        String query = "select * from iotbaylog where logid = '" + logId + "'";
        ResultSet logResultSet = statement.executeQuery(query);
        
        if (logResultSet.next()) {
            String loginTime = logResultSet.getString("intime");
            String logoutTime = logResultSet.getString("outtime");
            String date = logResultSet.getString("logdate");
            String userid = logResultSet.getString("userid");
            
            return new LogModel(UUID.fromString(logId), loginTime, logoutTime, date, UUID.fromString(userid));
        }
        return null;
    }
    
    //find all logs for an admin to see
    public LinkedList<LogModel> getAllLogs() throws SQLException {
        String query = "select logId from iotbaylog";
        return makeLogListFromQuery(query);
    }
    
    public LinkedList<LogModel> makeLogListFromQuery(String query) throws SQLException {
        LinkedList<LogModel> logs = new LinkedList<>();
        LinkedList<String> logIds = new LinkedList<>();
        
        ResultSet logResultSet = statement.executeQuery(query);
       
        while (logResultSet.next()) {
            String logId = logResultSet.getString("logId");
            logIds.add(logId);
        }
            
        for (String logId : logIds) {
            LogModel log = getLog(logId);
            logs.add(log);
        }
        return logs;
    }
        
    // Create Order
    public void addOrder(OrderModel order) throws SQLException {
        AddressModel address = findAddress(order.getShipping().getAddress());
        order.setDeliveryAddress(address);
        UUID paymentID = findPayment(order.getPayment()).getId();
        
        addShipment(order.getShipping());
        
        String query = "INSERT INTO IOTBAYORDER (ORDERID, USERID, PAYMENTID, SHIPPINGDETAILID) VALUES ('" + order.getOrderID().toString() + "', '" + order.getUser().getUserId().toString() + "', '" + paymentID + "', '" + order.getShipping().getShippingId().toString() + "')";
        statement.execute(query);        
    }
    
    // Check if Address exists in DB already, otherwise creates one
    public AddressModel findAddress(AddressModel address) throws SQLException{
        String query = "SELECT * FROM IOTBAYADDRESS WHERE ADDRESSID = '" + address.getId().toString() + "'";
        ResultSet addressResultSet = statement.executeQuery(query);
        
        if (!addressResultSet.next()) {
            query = "INSERT INTO IOTBAYADDRESS VALUES('" + address.getId().toString() + "', '" + address.getStreetAddress() + "', '" + address.getSuburb() + "', '" + address.getCountry() + "', '" + address.getState() + "', '" + address.getPostcode() + "')";
            statement.execute(query);
        }
        
        return address;
    }
    
    // Check if Payment exists in DB already, otherwise creates one
    public PaymentModel findPayment(PaymentModel payment) throws SQLException{
        String query = "SELECT * FROM IOTBAYPAYMENTMETHOD WHERE PAYMENTID = '" + payment.getId().toString() + "'";
        ResultSet paymentResultSet = statement.executeQuery(query);
        
        if (!paymentResultSet.next()) {
            query = "INSERT INTO IOTBAYPAYMENTMETHOD VALUES('" + payment.getId().toString() + "', '" + payment.getCardNumber() + "', '" + payment.getCvv() + "', '" + payment.getCardHolder() + "', " + payment.getExpiry() + "'";
            statement.execute(query);
        }
        
        return payment;
    }
            
    //add log for a user - when logging in
    public void addLog(LogModel log) throws SQLException {
        String insert = "insert into iotbaylog (logId, logdate, intime, outtime, userid) values ('" 
                + log.getId().toString() + "', '" + log.getDate() + "', '" + log.getLoginTime()+ "', '" 
                + log.getLoginTime() + "', '" + log.getUserId() + "')";
        statement.execute(insert);
    }
    
    //update log for a user - when logging out - will update the logout time for the log
    public void updateLogForLogout(LogModel log) throws SQLException {
        String update = "update iotbaylog set outtime = '" +  log.getLogoutTime() + "' where logid = '" + log.getId() + "'";
        statement.execute(update);
    }
    
    // Add Shipping Details
    public void addShipment(ShippingDetailModel shipping) throws SQLException {
        String query = "INSERT INTO IOTBAYSHIPPINGDETAIL VALUES ('" + shipping.getShippingId().toString() + "', '" + shipping.getAddress().getId().toString() + "', '" + shipping.getShippingMethod() + "', '" + shipping.getShippingDate() + "')";
        statement.execute(query);
    }
    
    // Add New Address
    public void addAddress(AddressModel address) throws SQLException {
        String insert = "insert into iotbayaddress (addressid, streetaddress, suburb, country, addressstate, postcode) values " +
                    "('" + address.getId().toString() + "', '" + address.getStreetAddress() + "', '" + address.getSuburb() 
                    + "', '" + address.getCountry() + "', '" + address.getState() + "', '" 
                    + Integer.toString(address.getPostcode()) + "')";
        statement.execute(insert);
    }
    
    // Add New Payment
    public void addPayment(PaymentModel payment) throws SQLException {
        String insert = "insert into iotbayPaymentMethod (paymentId, cardnum, cvv, cardname, expiry) values ('" 
                    + payment.getId().toString() + "', '" + payment.getCardNumber() 
                    + "', " + payment.getCvv() + ", '" + payment.getCardHolder() 
                    + "', '" +  payment.getExpiry() + "')";
        statement.execute(insert);
    }
    
    // Add Cart Items Iteratively
    public void addCart(CartModel cart) throws SQLException {
        for (CartItemModel cartItem : cart.getCartItems()) {
            String query = "INSERT INTO IOTBAYCART VALUES ('" + cartItem.getID().toString() + "', '" + cartItem.getProduct().getID().toString() + "', '" + cartItem.getOrder().getOrderID().toString() + "', " + cartItem.getPrice() + ", " + cartItem.getQuantity() + ")";
            statement.execute(query);
        }
    }
    
    // Find Shipping Details
    public ShippingDetailModel findShippingDetails(String shippingID) throws SQLException {
        ShippingDetailModel shippingDetails = null;
        String query = "select * from iotbayshippingdetail where shippingdetailID = '"+ shippingID +"'";
        ResultSet shippingResultSet = statement.executeQuery(query);
        
        if (shippingResultSet.next()) {
            String addressID = shippingResultSet.getString("addressID");
            String shippingMethod = shippingResultSet.getString("shippingMethod");
            String shippingDate = shippingResultSet.getString("shippingDate");
            
            AddressModel address = findAddress(addressID);
            
            shippingDetails = new ShippingDetailModel(UUID.fromString(shippingID), address, shippingMethod, shippingDate);
        } 

        return shippingDetails;
    }
    
    // Find Cart for Order
    public CartModel findCart(OrderModel order) throws SQLException {
        CartModel cart = new CartModel(order.getUser());
        String query = "select cartID from iotbaycart where orderID = '"+ order.getOrderID().toString() +"'";
        ResultSet cartResultSet = statement.executeQuery(query);
        LinkedList<String> cartItemIDs = new LinkedList();
        
        while (cartResultSet.next()) {
            String cartItemID = cartResultSet.getString("cartID");
            cartItemIDs.add(cartItemID);
        }
        
        for (String cartItemID : cartItemIDs) {
            CartItemModel cartItem = findCartItem(cartItemID, cart);
            cart.addToCart(cartItem);
        }
        
        return cart;
    }
    
    public CartItemModel findCartItem(String id, CartModel cart) throws SQLException {
        CartItemModel cartItem = null;
        String query = "SELECT * FROM IOTBAYCART WHERE CARTID = '" + id + "'";
        ResultSet cartItemResultSet = statement.executeQuery(query);
        
        if (cartItemResultSet.next()) {
            String productID = cartItemResultSet.getString("productID");
            int quantity = cartItemResultSet.getInt("quantity");
            
            ProductModel product = getProduct(productID);
            cartItem = new CartItemModel(id, product, quantity, cart);
        }
        
        return cartItem;
    }
    
    // Get Orders for a Customer
    public LinkedList<OrderModel> getOrders(CustomerModel customer) throws SQLException {
        LinkedList<OrderModel> orders = new LinkedList<>();
        String userID = customer.getUserId().toString();
        String query = "SELECT ORDERID FROM IOTBAYORDER WHERE USERID = '" + userID + "'";
        ResultSet orderResultSet = statement.executeQuery(query);
        LinkedList<String> orderIDs = new LinkedList<>();
        
        while (orderResultSet.next()) {
            String orderID = orderResultSet.getString("orderID");
            orderIDs.add(orderID);
        }
        
        for (String orderID : orderIDs) {
            OrderModel order = findOrder(orderID, customer);                    
            order.setCart(findCart(order));
            orders.add(order);
        }
        
        return orders;
    }
    
    // Get an Individual Order
    public OrderModel findOrder(String orderID, CustomerModel customer) throws SQLException {
        OrderModel order = null;
        String query = "SELECT * FROM IOTBAYORDER WHERE ORDERID = '" + orderID + "'";
        ResultSet orderResultSet = statement.executeQuery(query);
        
        if (orderResultSet.next()) {
            String paymentID = orderResultSet.getString("paymentID");
            String shippingDetailID = orderResultSet.getString("shippingDetailID");

            PaymentModel payment = findPaymentMethod(paymentID);
            ShippingDetailModel shipping = findShippingDetails(shippingDetailID);

            order = new OrderModel(orderID, customer, payment, shipping);
        }
        
        return order;
    }
}