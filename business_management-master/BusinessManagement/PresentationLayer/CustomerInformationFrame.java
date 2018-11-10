package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import BusinessLayer.Customer;
import DatabaseLayer.DAOFactory;


public class CustomerInformationFrame extends JFrame {
    
	//table initalization
	private JTable customerTable;
    private CustomerTableModel customerTableModel;
    
    //search field initialized.
    private JTextField searchField;
    private JComboBox searchCombo;
    private String selectedCustomerId;
    
   
    public CustomerInformationFrame() throws UnsupportedLookAndFeelException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Customer Information");
        setSize(768, 384);
        setLocationByPlatform(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        customerTable = buildCustomerTable();
        add(new JScrollPane(customerTable), BorderLayout.CENTER);
        add(buildSearchPanel(), BorderLayout.NORTH);
        setVisible(true);
                
    }
    
        public CustomerInformationFrame(String lookup) throws UnsupportedLookAndFeelException, SQLException, InterruptedException {
        
          
            
            try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Customer Information");
        setSize(768, 384);
        setLocationByPlatform(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.SOUTH);
        customerTable = buildCustomerTable();
        add(new JScrollPane(customerTable), BorderLayout.CENTER);
        add(buildSearchPanel(), BorderLayout.NORTH);
        setVisible(true);
        
          if(lookup.equals("lookup"))
                this.wait();
                
    }
    
   
    private JPanel buildButtonPanel() throws SQLException {
        JPanel panel = new JPanel();
        
           JButton selectButton = new JButton("Select");
        selectButton.addActionListener((ActionEvent) -> {
            doSelectButton(); 
            
        });
    
        panel.add(selectButton);
    
        //add button
        JButton addButton = new JButton("Add");
        addButton.addActionListener((ActionEvent) -> {
            doAddButton();
            try {
				fireDatabaseUpdatedEvent();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(addButton);
        
        //edit button
        JButton editButton = new JButton("Edit");
        editButton.setToolTipText("Edit selected customer");
        editButton.addActionListener((ActionEvent) -> {
            try {
                doEditButton();
                fireDatabaseUpdatedEvent();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        panel.add(editButton);
        
        // help button
        
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener((ActionEvent) -> {
            doHelpButton();
        });
    
        panel.add(helpButton);
         
        
        //exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            dispose();
        });
    
        panel.add(exitButton);
        
        return panel;
        
    }
    
    
    private void doAddButton() {
    	CustomerInfoForm customerForm = new CustomerInfoForm(this, "Add Customer", true);
        customerForm.setLocationRelativeTo(this);
        customerForm.setVisible(true);
    }
    
    
    private void doEditButton() throws SQLException {
    	int selectedRow = customerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "No Customer is currently "
                    + "selected.", "No customer selected", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Customer customer = customerTableModel.getCustomer(selectedRow);
            CustomerInfoForm customerForm = new CustomerInfoForm(this, "Edit Customer", true, customer);
            customerForm.setLocationRelativeTo(this);
            customerForm.setVisible(true);
        }
    }
    
    
    private void doHelpButton()
    {
    	JOptionPane.showMessageDialog(this, "Press the 'Add' button to add a customer. \n"
                + "Press the 'Edit' button after selecting a customer to edit their name. \n"
               
                + "Press the 'Exit' button to exit the program.", 
                    "Help Window", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void doSelectButton()
    {
        int selectedRow = customerTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "No invoice is currently "
                        + "selected.", "No Invoice selected", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
    	
                selectedCustomerId = (String) customerTable.getValueAt(customerTable.getSelectedRow(), 0);
                this.notifyAll();
        
                
                
            }
    
    }
    
  
    public void fireDatabaseUpdatedEvent() throws SQLException {
    	customerTableModel.databaseUpdated();
    }
       
    
   private JTable buildCustomerTable() throws SQLException {
        customerTableModel = new CustomerTableModel();
        JTable table = new JTable((javax.swing.table.TableModel) customerTableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(null);
        return table;
   }
   
   /**
    * Method to build the search panel.
    * @return panel	panel which populates the NORTH end of frame.
    * Written by Michael Meesseman
    */
   private JPanel buildSearchPanel() {
	   
	// drop down box fields
	   String[] fields = {"Customer ID", "Last Name", 
	    		"First Name", "Contact Info ID", "Address ID", "Street Address", 
	    		"City", "State", "Zip Code", "Unit Number", "Home Phone", "Cell Phone", 
	    		"Email Address"};
	   
	   JPanel panel = new JPanel();
	   
	 //text field initialize
	   searchField = new JTextField();
	   Dimension longField = new Dimension(300, 20);
       searchField.setPreferredSize(longField);
       searchField.setMinimumSize(longField);
       
       panel.add(searchField);
       
    // combo box initialize
       searchCombo = new JComboBox(fields);
       panel.add(searchCombo);
       
       
       //search button
       JButton searchButton = new JButton("Search");
       searchButton.addActionListener((ActionEvent) -> {
           try {
			doSearchButton();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       });
   
       panel.add(searchButton);
	   
	   return panel;
   }
   
  
   private void doSearchButton() throws SQLException {
	   
	   String column;
	   
	// switch to set search parameter to database field name.
	   switch(searchCombo.getSelectedIndex())
	   {
	   case 0:
		   column = "customer_id";
		   break;
	   case 1:
		   column = "last_name";
		   break;
	   case 2:
		   column = "first_name";
		   break;
	   case 3:
		   column = "contact_info_contact_info_id";
		   break;
	   case 4:
		   column = "Address_address_id";
		   break;
	   case 5:
		   column = "street_address";
		   break;
	   case 6:
		   column = "city";
		   break;
	   case 7:
		   column = "state";
		   break;
	   case 8:
		   column = "zip_code";
		   break;
	   case 9: 
		   column = "unit_number";
		   break;
	   case 10:
		   column = "phone_number";
		   break;
	   case 11:
		   column = "cell_phone_number";
		   break;
	   case 12:
		   column = "email_address";
		   break;
	   default:
		   column = "";
		   break;
		   
	   }
	   

	   if(searchField.getText().equals("")) {
		   customerTableModel.reset();
	   }
	   else
		   customerTableModel.refresh(column, searchField.getText());
	   if(customerTableModel.resultChecker(column, searchField.getText()).isEmpty()) {
	   		JOptionPane.showMessageDialog(this, "Customer does not exist.  Please try another search.", 
	                    "Search Input does not Exist.", JOptionPane.INFORMATION_MESSAGE);
	   		customerTableModel.reset();
	   		}
   }

    String getSelectedCustomerId() {
        
        return selectedCustomerId;
            
        
    }
}
