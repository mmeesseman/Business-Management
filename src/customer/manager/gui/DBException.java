/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

/**
 *
 * @author Michael
 */
public class DBException extends Exception {
    
    public DBException() {}
    
    public DBException(String message)
    {
        super(message);
    }
    
}

