    //Author     : ISD AUT2020 G45

package IOTBay.Controllers;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.util.LinkedList;

public class WelcomeController {
    
    public WelcomeController() {}
    
    public LinkedList<OrderModel> getOrders(CustomerModel customer) {
        try {
            DBConnector conn = new DBConnector();
            DBManager database = new DBManager(conn.openConnection());
            
            LinkedList<OrderModel> orders = database.getOrders(customer);
            return orders;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public LinkedList<OrderModel> filterOrdersByOrderQuery(LinkedList<OrderModel> orderList, String query) {
        // check for order id or date
        LinkedList<OrderModel> queriedOrders = new LinkedList<>();
        
        for (OrderModel order : orderList) {
            if (order.getOrderID().toString().toLowerCase().contains(query.toLowerCase()) || order.getShipping().getShippingDate().toLowerCase().contains(query.toLowerCase())) {
                queriedOrders.add(order);
            }
        }
        
        return queriedOrders;
    }
    
    public LinkedList<OrderModel> filterOrdersByPaymentQuery(LinkedList<OrderModel> orderList, String query) {
        // check for payment id or date
        LinkedList<OrderModel> queriedOrders = new LinkedList<>();
        
        for (OrderModel order : orderList) {
            if (order.getPayment().getId().toString().toLowerCase().contains(query.toLowerCase()) || order.getShipping().getShippingDate().toLowerCase().contains(query.toLowerCase())) {
                queriedOrders.add(order);
            }
        }
        
        return queriedOrders;
    }
    
    public LinkedList<OrderModel> filterOrdersByShippingQuery(LinkedList<OrderModel> orderList, String query) {
        // check for shipping id or date
        LinkedList<OrderModel> queriedOrders = new LinkedList<>();
        
        for (OrderModel order : orderList) {
            if (order.getShipping().getShippingId().toString().toLowerCase().contains(query.toLowerCase()) || order.getShipping().getShippingDate().toLowerCase().contains(query.toLowerCase())) {
                queriedOrders.add(order);
            }
        }
        
        return queriedOrders;
    }
    
}
