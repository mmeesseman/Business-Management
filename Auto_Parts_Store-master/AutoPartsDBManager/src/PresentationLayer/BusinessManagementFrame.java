package PresentationLayer;


import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class BusinessManagementFrame extends JFrame{
 
     
	
    public BusinessManagementFrame() throws UnsupportedLookAndFeelException, SQLException {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException 
                | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println(e);
        }
        setTitle("Business Management");
        setSize(768, 100);
        setLocationByPlatform(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(buildButtonPanel(), BorderLayout.CENTER);
        setVisible(true);
                
    }
    
    private JPanel buildButtonPanel() throws SQLException {
        JPanel panel = new JPanel();
    
        // Customer information button
        JButton customerInfoButton = new JButton("Customer Information");
        customerInfoButton.addActionListener((ActionEvent) -> {
            try {
                doCustomerInfoButton();
            } catch (SQLException e) {
                System.out.println(e);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(BusinessManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
        });
    
        panel.add(customerInfoButton);
        
        // employee information button
        JButton employeeButton = new JButton("Employee");
        employeeButton.addActionListener((ActionEvent) -> {
            try {
                doEmployeeButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(BusinessManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
        });
    
        panel.add(employeeButton);
        
        
        
        // company information button
        JButton companyButton = new JButton("Companies");
        companyButton.addActionListener((ActionEvent) -> {
            try {
                doCompanyButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(BusinessManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(companyButton);
         
        // sales invoices button
        JButton salesButton = new JButton("Sales");
        salesButton.addActionListener((ActionEvent) -> {
            try {
                doSalesButton();
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(BusinessManagementFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    
        panel.add(salesButton);
        
        // exit button. closes program.
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((ActionEvent) -> {
            System.exit(0);
        });
    
        panel.add(exitButton);
    
        return panel;
        
    }
    
    private void doCustomerInfoButton() throws UnsupportedLookAndFeelException, SQLException {
        CustomerInformationFrame custInfoFrame = new CustomerInformationFrame();
    }

    private void doEmployeeButton() throws UnsupportedLookAndFeelException, SQLException {
        EmployeeFrame employeeFrame = new EmployeeFrame();
    }
    

    private void doCompanyButton() throws UnsupportedLookAndFeelException, SQLException {
       CompanyFrame companyFrame = new CompanyFrame();
   }


    private void doSalesButton() throws UnsupportedLookAndFeelException, SQLException
    {
        SalesFrame salesFrame = new SalesFrame();
    }
    
   
}