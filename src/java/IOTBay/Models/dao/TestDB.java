    //Author     : ISD AUT2020 G45

package IOTBay.Models.dao;

import IOTBay.Models.*;
import IOTBay.Models.dao.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

 

public class TestDB {

    private static Scanner in = new Scanner(System.in);



    public static void main(String[] args) {
    try {

        DBConnector connector = new DBConnector();

        Connection conn = connector.openConnection();
            DBManager db = new DBManager(conn);

            System.out.print("User email: ");
            String email = in.nextLine();

            System.out.print("first name: ");
            String fname = in.nextLine();

            System.out.print("last name: ");
            String lname = in.nextLine();
            
            System.out.print("User password: ");
            String password = in.nextLine();

            System.out.print("User phone: ");
            String phone = in.nextLine();
            int phoneNum = Integer.parseInt(phone);
            
            CustomerModel newCustomer = new CustomerModel(fname, lname, email, 
                                                    phoneNum, password);
            
            System.out.print("payment card num (16 digits): ");
            String cardnum = in.nextLine();
            
            System.out.print("payment card cvv (3 digits): ");
            String cvv = in.nextLine();
            
            System.out.print("card holder name: ");
            String cardHolder = in.nextLine();
            
            System.out.print("card expiry (yyyy-mm-dd): ");
            String expiry  = in.nextLine();
            
            newCustomer.setPayment(cardnum, Integer.parseInt(cvv), expiry, cardHolder);
            
            db.addCustomer(newCustomer, false, true);
            System.out.println("User with address is added to the database.");
            
            CustomerModel customer = db.findCustomer(email);
            
            System.out.println("Customer " + customer.getCustomerId().toString()  + " retrieved.\n"
                    + "Name: " + customer.getFullName() + "\n"
                    + "email: " + customer.getEmail() + "\n"
                    + "ph: " + customer.getPhoneNumber() + "\n"
                    + "pw: " + customer.getPassword());
                                    
            connector.closeConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TestDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
