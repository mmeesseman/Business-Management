
package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class DatabaseWriter implements WriterDAO {
	private Connection connObj = null;	
	private WriteHelper writerHelper = null;
	private ReaderDAO readerDAO;
	
	public DatabaseWriter() {
		
		writerHelper = new WriteHelper();
		readerDAO = DAOFactory.getReaderDAO();
	}
	
	
	public static void closeConnection(Connection connObj) {
		
		if(connObj != null) {
			try {
				connObj.close();
			}
			catch (SQLException ignore) {
			}
		}
	}
	
	
	public static Connection getDBConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		String url = "jdbc:mysql://localhost:3306/business_management_schema";
		String username = "business";
		String password = "business";
		
		try {
			connection = DriverManager.getConnection(url, username, password);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		return connection;
	}
	
	
	public void manageNewPersonCreation(String choice, String lastName, String firstName,
			String stAddress, String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhone, String emailAddress, String companyID){
		
		String addressID = null;
		String contactInfoID = null;
		
		// write new address and contact records, obtain the id's
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = readerDAO.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = readerDAO.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		
		// determine type of person to create
		if(choice == "Customer") {
			writerHelper.writeCustomerInformation(addressID, contactInfoID, lastName, firstName);
		}
		else if (choice == "Employee") {
			writerHelper.writeEmployeeInformation(addressID, contactInfoID, lastName, firstName);
		}
		
	}
	
	
	public void createNewCompany(String stAddress, String city, String state,
			String zipCode, String unitNumber, String phoneNumber, String cellPhone,
			String emailAddress, String companyName) {
		
		String addressID = null;
		String contactInfoID = null;
		
		// write new address and contact information and obtain their ID's
		writerHelper.writeAddressInformation(stAddress, city, state, zipCode, unitNumber);
		addressID = readerDAO.obtainNewAddressID(stAddress, city, state, zipCode, unitNumber);
		writerHelper.writeContactInformation(phoneNumber, cellPhone, emailAddress);
		contactInfoID = readerDAO.obtainNewContactInformationID(phoneNumber, cellPhone, emailAddress);
		
		// write the new company information.
		writerHelper.writeCompanyInformation(addressID, contactInfoID, companyName);
	}
	
	
	public void createInvoice(String date, String time, String customerID, String employeeID) {
		
		String newInvoiceUpdate = null;
		
		newInvoiceUpdate = "insert into invoice " +
				"(invoice_number, date, time, customer_customer_id, employee_employee_id) " +
				"values (DEFAULT, '" + date + "', '" + time + "', '" + customerID + "', '" +
				employeeID + "');";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
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
		
		connObj = getDBConnection();
								
		try {
			stmt = connObj.createStatement();
			stmt.executeUpdate(newInvoiceLineItemUpdate);
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
	}
	
	public boolean checkCompanyExists(String companyID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String companyName = "";
		String companyIDQuery = null;
		
		companyIDQuery = "select company_name " +
				"from company " +
				"where company_id = '" + companyID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(companyIDQuery);			
			while(rs.next()) {
				companyName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(companyName.length() != 0) {
			valid = true;
		}
		return valid;
	}
	
	
	public boolean checkCustomerExists(String customerID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String customerName = "";
		String customerIDQuery = null;
		
		customerIDQuery = "select last_name " +
				"from customer " +
				"where customer_id = '" + customerID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(customerIDQuery);			
			while(rs.next()) {
				customerName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(customerName.length() != 0) {
			valid = true;
		}
		return valid;
	}
	

	public boolean checkEmployeeExists(String employeeID) {
		
		boolean valid = false;
		ResultSet rs = null;
		String employeeName = "";
		String employeeIDQuery = null;
		
		employeeIDQuery = "select last_name " +
				"from employee " +
				"where employee_id = '" + employeeID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(employeeIDQuery);			
			while(rs.next()) {
				employeeName = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		if(employeeName.length() != 0) {
			valid = true;
		}
		return valid;
	}

	
	public void manageEditingCustomer(String customerID, String contactID, String addressID, 
			String lastName, String firstName, String streetAddress, 
			String city, String state, String zipCode, String unitNumber, 
			String homePhone, String cellPhone, String email) {
		
		// write over the existing records to record alterations such as spelling errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editCustomer(customerID, lastName, firstName, contactID, addressID);
	}


	public void manageEditingEmployee(String employeeID, String contactID, String addressID,
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email) {

		// write over the existing records to record alterations such as spelling errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editEmployee(employeeID, lastName, firstName, contactID, addressID);
	}
	
	public void manageEditingCompany(String companyID, String addressID, 
			String contactID, String streetAddress, String city, String state, 
			String zipCode, String unitNumber, String homePhone, String cellPhone,
			String email, String companyName) {
		
		// overwrite the existing records to fix data entry errors
		writerHelper.editAddress(addressID, streetAddress, city, state, zipCode, unitNumber);
		writerHelper.editContactInfo(contactID, homePhone, cellPhone, email);
		writerHelper.editCompany(companyID, companyName);
		
	}
	
}