/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Michael
 */
public class CustomerManagerGUI {

    /**
     * @param args the command line arguments
     * @throws javax.swing.UnsupportedLookAndFeelException
     * @throws customer.manager.gui.DBException
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException, DBException {
        CustomerManagerFrame frame = new CustomerManagerFrame();
    }
    
}
