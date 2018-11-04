/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer.manager.gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michael
 */
public class CustomerTableModel extends AbstractTableModel {
    
    private List<Customer> customers;
    private final String[] COLUMN_NAMES = {"First Name", "Last Name", "Email"};
    
    public CustomerTableModel() throws DBException{
        try {
            customers = CustomerDB.getCustomers();
        }
        catch (DBException e)
        {
            System.out.println(e);
        }
    }
    
    @Override 
    public int getRowCount() {
        return customers.size();
    }
    
    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return customers.get(rowIndex).getFirstName();
            case 1:
                return customers.get(rowIndex).getLastName();
            case 2:
                return customers.get(rowIndex).getEmail();
            default:
                return null;
        }
    }
    
    Customer getCustomer(int rowIndex) throws DBException {
        return customers.get(rowIndex);
    }
    
    void databaseUpdated() {
        try {
            customers = CustomerDB.getCustomers();
            fireTableDataChanged();
        }
        catch (DBException e) {
            System.out.println(e);
        }
    }
}
