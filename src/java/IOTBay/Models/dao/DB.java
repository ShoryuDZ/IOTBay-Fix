    //Author     : ISD AUT2020 G45

package IOTBay.Models.dao;

import java.sql.Connection;

/** 
* Super class of DAO classes that stores the database information 
*  
*/

public abstract class DB {   
    protected String URL = "jdbc:derby://localhost:1527/IOTBayDatabase";// jdbc:derby local host url   
    protected String db = "IOTBayDatabase";//name of the database   
    protected String dbuser = "G45";//db root user   
    protected String dbpass = "G45"; //db root password   
    protected String driver = "org.apache.derby.jdbc.ClientDriver"; //jdbc client driver - built in with NetBeans   
    protected Connection conn; //connection null-instance to be initialized in sub-classes
}