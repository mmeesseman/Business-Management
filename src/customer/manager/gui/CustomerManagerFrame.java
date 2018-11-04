/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Michael
 */
public class CustomerManagerFrame extends JFrame {
    
    private JTable customerTable;
    private CustomerTableModel customerTableModel;
    
    public CustomerManagerFrame() throws UnsupportedLookAndFeelException, DBException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Customer Manager");
        setSize(768, 384);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        customerTable = buildCustomerTable();
        add(new JScrollPane(customerTable), BorderLayout.CENTER);
        setVisible(true);
                
    }
    
    private JPanel buildButtonPanel() throws DBException {
        JPanel panel = new JPanel();
    
        JButton addButton = new JButton("Add");
        addButton.addActionListener((ActionEvent) -> {
            doAddButton();
        });
    
        panel.add(addButton);
        
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit selected customer");
        editButton.addActionListener((ActionEvent) -> {
            try {
                doEditButton();
            } catch (DBException e) {
                System.out.println(e);
            }
        });
        panel.add(editButton);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setToolTipText("Delete selected customer");
        deleteButton.addActionListener((ActionEvent) -> {
            try {
                doDeleteButton();
            } catch (DBException e) {
                System.out.println(e);
            }
        });
        panel.add(deleteButton);
        
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener((ActionEvent) -> {
            doHelpButton();
        });
    
        panel.add(helpButton);
         
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            System.exit(0);
        });
    
        panel.add(exitButton);
        
        return panel;
        
    }
    
    private void doAddButton() {
        CustomerForm customerForm = new CustomerForm(this, "Add Customer", true);
        customerForm.setLocationRelativeTo(this);
        customerForm.setVisible(true);
    }
    
    private void doEditButton() throws DBException {
        int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No Customer is currently "
                    + "selected.", "No customer selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Customer customer = customerTableModel.getCustomer(selectedRow);
            CustomerForm customerForm = new CustomerForm(this, "Edit Customer", true, customer);
            customerForm.setLocationRelativeTo(this);
            customerForm.setVisible(true);
        }
    }
    
    private void doDeleteButton() throws DBException {
        int selectedRow = customerTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No customer is currently selected", 
                    "No customer selected.", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Customer customer = customerTableModel.getCustomer(selectedRow);
            int ask = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " 
                    + customer.getName() + " from the database?", "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.YES_OPTION) {
                try {
                    CustomerDB.deleteCustomer(customer);
                    fireDatabaseUpdatedEvent();
                }
                catch (DBException e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    private void doHelpButton()
    {
        JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a customer. \n"
                + "Press the 'Edit' button after selecting a customer to edit their name. \n"
                + "Press the 'Delete' button after selecting a customer to delete that customer. \n"
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void fireDatabaseUpdatedEvent()
    {
        customerTableModel.databaseUpdated();
    }
    
    private JTable buildCustomerTable() throws DBException {
        customerTableModel = new CustomerTableModel();
        JTable table = new JTable(customerTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
    }
}

    

