package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import BusinessLayer.InvoiceLineItem;
import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.DatabaseWriter;
import DatabaseLayer.ReaderDAO;
import DatabaseLayer.WriterDAO;


/**
 * Class extends JDialog for sales items entry
 * Written by Michael Meesseman
 */
public class SalesItemForm extends JDialog{
	
				
	// text field initialization
			    private JTextField invoiceLineItemNumberField;
			    private JTextField invoiceNumberField;
			    private JTextField purchasedQtyField;
			    private JTextField serviceItemField;
			    private JButton confirmButton;
			    private JButton cancelButton;
			    
			    //Added by Rick
			    private String invoiceNumber = "";
			    private String date = "";
			    private String time = "";
			    private String customerID = "";
			    private String employeeID = "";
			    private boolean dataEntered = true;
			    private static WriterDAO writerDAO;
			    private ReaderDAO readerDAO;
			    
			    private InvoiceLineItem invoiceLineItem = new InvoiceLineItem();
			    
			    
			    public SalesItemForm(java.awt.Frame parent, String title, boolean modal, String invoiceNumberInput) {
			        super(parent, title, modal);
			        invoiceNumber = invoiceNumberInput;
			        //this.date = date;
			        //this.time = time;
			        //this.customerID = customerID;
			        //this.employeeID = employeeID;
			        initComponents(invoiceNumberInput);
			        
			     
			        writerDAO = DAOFactory.getWriterDAO();
			        readerDAO = DAOFactory.getReaderDAO();
			    }
			    
			    
			    public SalesItemForm(java.awt.Frame parent, String title, boolean modal, InvoiceLineItem invoiceLineItem) {
			        this(parent, title, modal, title);
			        this.invoiceLineItem = invoiceLineItem;
			        confirmButton.setText("Save");
			        invoiceLineItemNumberField.setText(invoiceLineItem.getInvoiceLineNumber());
			        invoiceNumberField.setText(invoiceLineItem.getInvoiceNumber());
			        purchasedQtyField.setText(invoiceLineItem.getQuantityPurchased());
			        serviceItemField.setText(invoiceLineItem.getServiceItem());
			        
			        //fields cannot be edited. 
			        invoiceLineItemNumberField.setEditable(false);
			        invoiceNumberField.setEditable(false);
			        
			        }
			    
			    
			    private void initComponents(String invoiceNumberInput) {
			    	
			    	//focus listeners to remove red text after validation
			    	invoiceLineItemNumberField = new JTextField();
			    	invoiceLineItemNumberField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(invoiceLineItemNumberField);
						}
					});
			        invoiceNumberField = new JTextField();
			        invoiceNumberField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(invoiceNumberField);
						}
					});
			        invoiceNumberField.setText(invoiceNumberInput);
			        purchasedQtyField = new JTextField();
			        purchasedQtyField.setName("Purchased Quantity");
			        purchasedQtyField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(purchasedQtyField);
						}
					});
			        serviceItemField = new JTextField();
			        serviceItemField.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent arg0) {
							checkField(serviceItemField);
						}
					});
			        cancelButton = new JButton();
			        confirmButton = new JButton();
			        
			        //fields cannot be edited. 
			        invoiceLineItemNumberField.setEditable(false);
			        invoiceNumberField.setEditable(false);
			        
			        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			       
			        // sets field size for window size changes.
			        Dimension longField = new Dimension(300, 20);
			        invoiceLineItemNumberField.setPreferredSize(longField);
			        invoiceLineItemNumberField.setMinimumSize(longField);
			        invoiceNumberField.setPreferredSize(longField);
			        invoiceNumberField.setMinimumSize(longField);
			        purchasedQtyField.setPreferredSize(longField);
			        purchasedQtyField.setMinimumSize(longField);
			        serviceItemField.setPreferredSize(longField);
			        serviceItemField.setMinimumSize(longField);
			        
			        
			        //cancel button
			        cancelButton.setText("Cancel");
			        cancelButton.addActionListener((ActionEvent) -> {
			            cancelButtonActionPerformed();
			        });
			        
			        //add button
			        confirmButton.setText("Add");
			        confirmButton.addActionListener((ActionEvent) -> {
			            try {
							confirmButtonActionPerformed();
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        });
			        
			        
			     // grid layout for labels and fields
			        JPanel salesItemPanel = new JPanel();
			        salesItemPanel.setLayout(new GridBagLayout());
			        salesItemPanel.add(new JLabel("Invoice Line Item Number:"), getConstraints(0, 0, GridBagConstraints.LINE_END));
			        salesItemPanel.add(invoiceLineItemNumberField, getConstraints(1, 0, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Invoice Number:"), getConstraints(0, 1, GridBagConstraints.LINE_END));
			        salesItemPanel.add(invoiceNumberField, getConstraints(1, 1, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Purchased Quantity:"), getConstraints(0, 2, GridBagConstraints.LINE_END));
			        salesItemPanel.add(purchasedQtyField, getConstraints(1, 2, GridBagConstraints.LINE_START));
			        salesItemPanel.add(new JLabel("Service Item:"), getConstraints(0, 3, GridBagConstraints.LINE_END));
			        salesItemPanel.add(serviceItemField, getConstraints(1, 3, GridBagConstraints.LINE_START));
			        
			        JPanel buttonPanel = new JPanel();
			        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			        buttonPanel.add(confirmButton);
			        buttonPanel.add(cancelButton);
			        
			        setLayout(new BorderLayout());
			        add(salesItemPanel, BorderLayout.CENTER);
			        add(buttonPanel, BorderLayout.SOUTH);
			        pack();
			    }
			    
			   
			    private GridBagConstraints getConstraints(int x, int y, int anchor) {
			        GridBagConstraints c = new GridBagConstraints();
			        c.insets = new Insets(5,5,0,5);
			        c.gridx = x;
			        c.gridy = y;
			        c.anchor = anchor;
			        return c;
			    }
			    
			    private void cancelButtonActionPerformed() {
			        dispose();
			    }
			    
			    private void confirmButtonActionPerformed() throws SQLException {
			        
			    	processData();
			    	
			    	
			    	
			    	if(dataEntered) {
			    		
			    		//Write to the DB
			    		System.out.println("All data entered!");
			    		
			    	}
			    	
			    	dataEntered = true;
			  
			    }
			    
			    private void processData() {
			    	
			    	//verifies fields not empty
			    	String purchasedQuantity = verifyEntry(purchasedQtyField);
			    	String serviceItem = verifyEntry(serviceItemField);
			    	

			    	//verifies qty is in stock before sale.
	
			    	
			    		dispose();
			    	
			    	
			    	
			    }
			    
			    private String verifyEntry(JTextField name) {
			    	String dataItem = "";
			    	boolean valid = true;
			    		
			    	dataItem = name.getText();
			    	
			    	if(dataItem.length() == 0) {
						name.setForeground(Color.RED);
						name.setText("Data Missing");
						dataEntered = false;
			    	}
			    	else if(dataItem.equals("Data Missing")) {
			    		dataEntered = false;
			    	}
				
			    	return dataItem;
			    }
			   
			
			    	private void checkField(JTextField name) {			
					if(name.getText().equals("Data Missing")) {  
						name.setText("");
						name.setForeground(Color.BLACK);
					}
				}   




}
