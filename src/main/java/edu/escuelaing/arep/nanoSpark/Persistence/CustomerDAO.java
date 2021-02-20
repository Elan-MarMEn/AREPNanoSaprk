package edu.escuelaing.arep.nanoSpark.Persistence;

import edu.escuelaing.arep.nanoSpark.Entities.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Make a postgres connection
 * @author Alan Marin
 */
public class CustomerDAO {

    private static CustomerDAO instance = new CustomerDAO();
    Connection c = null;
    Statement stmt = null;

    /**
     * Inserts the values from a specific request
     * @param name
     * @param num
     */
    public void save(String name, int num){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-3-222-11-129.compute-1.amazonaws.com:5432/d1mmjb0a5ormab",
                            "rkaiievppbmubj", "2e36d77b2898646643fffa713f9c7857593dc0298f4494f8a4791f9e2661b1e5");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO CUSTOMER (NAME,NUMBER) "
                    + "VALUES ('"+name+"',"+num+");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    /**
     * Returns all datd store on the Database
     * @return List<Customer>
     */
    public List<Customer> getData(){
        List<Customer> customers = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://ec2-3-222-11-129.compute-1.amazonaws.com:5432/d1mmjb0a5ormab",
                            "rkaiievppbmubj", "2e36d77b2898646643fffa713f9c7857593dc0298f4494f8a4791f9e2661b1e5");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CUSTOMER;" );


            while ( rs.next() ) {
                String  name = rs.getString("name");
                int number = rs.getInt("number");
                customers.add(new Customer(name,number));
            }
            rs.close();
            stmt.close();
            c.close();
            return customers;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return customers;
    }

    public static CustomerDAO getInstance() {
        return instance;
    }
}
