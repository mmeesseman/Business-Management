/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


/**
 *
 * @author Michael
 */
public class CustomerDB {
    
    public static ArrayList<Customer> getCustomers() throws DBException
    {
        String sql = "SELECT * FROM customers ORDER BY lastName";
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        
        try (PreparedStatement ps = connection.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery())
        {
            while (rs.next())
            {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                
                Customer c = new Customer();
                c.setFirstName(firstName);
                c.setLastName(lastName);
                c.setEmail(email);
                
                customers.add(c);
            }
     
            return customers;
        }
        catch (SQLException e)
        {
            throw new DBException();
        }
    }
    
    public static void addCustomer(Customer customer) throws DBException
    {
        String sql = "INSERT INTO customers (firstName, lastName, email) VALUES (?,?,?)";
        Connection connection = DBUtil.getConnection();
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DBException();
        }
    }
    
    public static void deleteCustomer(Customer customer) throws DBException
    {
        
        String email = customer.getEmail();
        String sql = "DELETE FROM customers WHERE email = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, email);
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DBException();
        }
       
    }

    
    public static Customer getCustomer(String email) throws DBException {
        
        ArrayList<Customer> customers = new ArrayList<>();
        customers = getCustomers();
        for (Customer c : customers) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return c;
            }
        }
        return null;
    }
    
    public static void updateCustomer(Customer customer) throws DBException {
       
        String sql = "UPDATE customers set firstName = ?, lastName = ? where email = ?;";
        Connection connection = DBUtil.getConnection();
        
        try (PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getEmail());
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
    }
}

