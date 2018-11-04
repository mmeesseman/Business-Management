/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Michael
 */
public class DBUtil {
    
    private static Connection connection;
    
    private DBUtil() {}
    
    public static synchronized Connection getConnection() throws DBException
    {
        
        if (connection != null)
        {
            return connection;
        }
        else
        {
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/mma";
                String username = "mma_user";
                String password = "sesame";
                
                connection = DriverManager.getConnection(url, username, password);
                return connection;
            }
            catch (SQLException e) 
            {
                throw new DBException("Connection parameters not valid.");
            }
        }
    }
    
    public static synchronized void closeConnection() throws DBException 
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                throw new DBException();
            }
            finally
            {
                connection = null;
            }
        }
    }
    
}

