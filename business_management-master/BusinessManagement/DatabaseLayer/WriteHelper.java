
package DatabaseLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class WriteHelper {
	
	private Connection connObj = null;
	private static ReaderDAO readerDAO;
	
	/**
	 * Constructor called from the DatabaseWriter class to create an instance of this class.
	 */
	public WriteHelper() {
		readerDAO = DAOFactory.getReaderDAO();
		
	}

	
	public void writeAddressInformation(String streetAddress, String city, String state, 
			String zipCode, String unitNumber) {
	
		String newAddressUpdate = null;
	
		newAddressUpdate = "insert into address " +
				"(address_id, street_address, city, state, zip_code, unit_number) " +
				"values (DEFAULT, '" + streetAddress + "', '" + city + "', '" + state + "', '" + zipCode +
				"', '" + unitNumber + "');";
		
		Statement stmt = null;
							
		connObj = DatabaseWriter.getDBConnection();
							
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newAddressUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
			
		DatabaseWriter.closeConnection(connObj);		
	}
	
	
	public void writeContactInformation(String phoneNumber, String cellPhone, 
			String emailAddress) {
		
		String newContactInfoUpdate = null;
		
		newContactInfoUpdate = "insert into contact_info " +
				"(contact_info_id, phone_number, cell_phone_number, email_address) " +
				"values (DEFAULT, '" + phoneNumber + "', '" + cellPhone +
				"', '" + emailAddress + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newContactInfoUpdate);
		}
		catch(SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	
	public void writeCustomerInformation(String addressID, String contactInfoID, 
			String lastName, String firstName) {
		
		String newCustomerUpdate = null;
		
		newCustomerUpdate = "insert into customer " +
				"(customer_id, Address_address_id, contact_info_contact_info_id, last_name, first_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				lastName + "', '" + firstName + "');";
		
		Statement stmt = null;
										
		connObj = DatabaseWriter.getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newCustomerUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		DatabaseWriter.closeConnection(connObj);		
	}
	
	
	public void writeEmployeeInformation(String addressID, String contactInfoID, 
			String lastName, String firstName) {
		
		String newEmployeeUpdate = null;
		
		newEmployeeUpdate = "insert into employee " +
				"(employee_id, Address_address_id, contact_info_contact_info_id, last_name, first_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				lastName + "', '" + firstName + "');";
		
		Statement stmt = null;
										
		connObj = DatabaseWriter.getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			stmt.executeUpdate(newEmployeeUpdate);			
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		DatabaseWriter.closeConnection(connObj);		
	}
	
	
	public void writeCompanyInformation(String addressID, String contactInfoID, 
			String companyName) {
		
		String newCompanyUpdate = null;
		
		newCompanyUpdate = "insert into company " +
				"(company_id, Address_address_id, contact_info_contact_info_id, company_name) " +
				"values (DEFAULT, '" + addressID + "', '" + contactInfoID + "', '" +
				companyName + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newCompanyUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	
	public void createInvoice(String date, String time, String customerID, String employeeID) {
		
		String newInvoiceUpdate = null;
		
		newInvoiceUpdate = "insert into invoice " +
				"(invoice_number, date, time, customer_customer_id, employee_employee_id) " +
				"values (DEFAULT, '" + date + "', '" + time + "', '" + customerID + "', '" +
				employeeID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
	public void createInvoiceLineItem(String invoiceNum, String quantityPurchased,
			String productID) {
		
		String newInvoiceLineItemUpdate = null;
		
		newInvoiceLineItemUpdate = "insert into invoice_line_item " +
				"(invoice_line_number, invoice_invoice_number, quantity_purchased, " +
				"product_product) " +
				"values (DEFAULT, '" + invoiceNum + "', '" + quantityPurchased +
				"', '" + productID + "');";
		
		Statement stmt = null;
		
		connObj = DatabaseWriter.getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceLineItemUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		DatabaseWriter.closeConnection(connObj);
	}
	
	
	public void editAddress(String addressID, String streetAddress, String city,
			String state, String zipCode, String unitNumber) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE address "
				+ "SET street_address = '" + streetAddress + "', "
				+ "city = '" + city + "', state = '" + state + "', zip_code = '"
				+ zipCode + "', unit_number = '" + unitNumber
				+ "' WHERE address_id = " + addressID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	
	public void editContactInfo(String contactID, String homePhone, String cellPhone,
			String email) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE contact_info "
				+ "SET phone_number = '" + homePhone + "', "
				+ "cell_phone_number = '" + cellPhone + "', "
				+ "email_address = '" + email
				+ "' WHERE contact_info_id = " + contactID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
	
	public void editCustomer(String customerID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE customer "
				+ "SET last_name = '" + lastName + "', "
				+ "first_name = '" + firstName 
				+ "' WHERE customer_id = " + customerID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	

	public void editEmployee(String employeeID, String lastName, String firstName,
			String contactID, String addressID) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE employee "
				+ "SET last_name = '" + lastName + "', "
				+ "first_name = '" + firstName 
				+ "' WHERE employee_id = " + employeeID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}

	public void editCompany(String companyID, String companyName) {
		
		String update = null;
		Statement stmt = null;
		
		update = "UPDATE company "
				+ "SET company_name = '" + companyName 
				+ "' WHERE company_id = " + companyID + ";";
		
		connObj = DatabaseWriter.getDBConnection();
		
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(update);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		DatabaseWriter.closeConnection(connObj);
	}
	
}