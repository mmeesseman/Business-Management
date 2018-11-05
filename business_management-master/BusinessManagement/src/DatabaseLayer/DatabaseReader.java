
package DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;



public class DatabaseReader implements ReaderDAO {
	
	// Declare variables
	private static Connection connObj = null;	
	
	
	public DatabaseReader() {
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
		
		closeConnection(connObj);
		return connection;
	}
	
	
	public ArrayList<Company> obtainCompanyList() {
	
		String query = "select * from company, address, contact_info "
			+ "where Address_address_id = address.address_id "
			+ "and contact_info_contact_info_id = contact_info.contact_info_id";
	
		ArrayList<Company> company = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String companyID = rs.getString(1);
				String addressID = rs.getString(2);
				String contactID = rs.getString(3);
				String companyName = rs.getString(4);
				String streetAddress = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zipCode = rs.getString(9);
				String unitNumber = rs.getString(10);
				String homePhone = rs.getString(12);
				String cellPhone = rs.getString(13);
				String emailAddress = rs.getString(14);
			
				Company c = new Company();
				c.setCompanyID(companyID);
				c.setAddressID(addressID);
				c.setContactInfoID(contactID);
				c.setCompanyName(companyName);
				c.setStreetAddres(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
				
				company.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
		
		return company;
	}
	
	
	public ArrayList<Customer> obtainCustomerList() {
		
		String query = "SELECT  * FROM customer, address, contact_info "
				+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
				+ "and customer.Address_address_id = address.address_id";
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		Statement stmt = null;
		
		connObj =  getDBConnection();
						
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String customerID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String contactInfoID = rs.getString(4);
				String addressID = rs.getString(5);
				String streetAddress = rs.getString(7);
				String city = rs.getString(8);
				String state = rs.getString(9);
				String zipCode = rs.getString(10);
				String unitNumber = rs.getString(11);
				String homePhone = rs.getString(13);
				String cellPhone = rs.getString(14);
				String emailAddress = rs.getString(15);
				
				Customer c = new Customer();
				c.setCustomerID(customerID);
				c.setLastName(lastName);
				c.setFirstName(firstName);
				c.setContactInfoID(contactInfoID);
				c.setAddressID(addressID);
				c.setStreetAddress(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
				
				customers.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
		
		closeConnection(connObj);
				
		return customers;
	}

	public ArrayList<Employee> obtainEmployeeList() {
	
		String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id";
		ArrayList<Employee> employees = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String employeeID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String contactInfoID = rs.getString(4);
				String addressID = rs.getString(5);
				String streetAddress = rs.getString(7);
				String city = rs.getString(8);
				String state = rs.getString(9);
				String zipCode = rs.getString(10);
				String unitNumber = rs.getString(11);
				String homePhone = rs.getString(13);
				String cellPhone = rs.getString(14);
				String emailAddress = rs.getString(15);
			
				Employee e = new Employee();
				e.setEmployeeID(employeeID);
				e.setLastName(lastName);
				e.setFirstName(firstName);
				e.setContactInfoID(contactInfoID);
				e.setAddressID(addressID);
				e.setStreetAddress(streetAddress);
				e.setCity(city);
				e.setState(state);
				e.setZipCode(zipCode);
				e.setUnitNumber(unitNumber);
				e.setPhoneNumber(homePhone);
				e.setCellPhoneNumber(cellPhone);
				e.setEmailAddress(emailAddress);
			
				employees.add(e);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return employees;
	}
	

	public ArrayList<Invoice> obtainInvoiceList() {
	
		String query = "SELECT * FROM invoice";
		ArrayList<Invoice> invoices = new ArrayList<>();
		
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String invoiceNumber = rs.getString(1);
				String date = rs.getString(2);
				String time = rs.getString(3);
				String customerID = rs.getString(4);
				String employeeID = rs.getString(5);
		
				Invoice i = new Invoice();
				i.setInvoiceNumber(invoiceNumber);
				i.setDate(date);
				i.setTime(time);
				i.setCustomerID(customerID);
				i.setEmployeeID(employeeID);
			
				invoices.add(i);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return invoices;
	}

	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput) {
	
		
		String query = "SELECT * FROM invoice_line_item where invoice_invoice_number = '" + invoiceNumberInput + "'";
		ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj =  getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String invoiceLineNumber = rs.getString(1);
				String invoiceNumber = rs.getString(2);
				String quantityPurchased = rs.getString(3);
				String productID = rs.getString(4);
			
				InvoiceLineItem i = new InvoiceLineItem();
				i.setInvoiceLineNumber(invoiceLineNumber);
				i.setInvoiceNumber(invoiceNumber);
				i.setQuantityPurchased(quantityPurchased);
				i.setProductID(productID);
			
			
				invoiceLineItems.add(i);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return invoiceLineItems;
	}

	public String obtainPassword(String username) {
	
		String query = "SELECT password FROM login WHERE username = '" + username + "'";
		String password = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				password = rs.getString(1);
			}	
		}
	
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
	
		return password;
	}


	public ArrayList<Customer> obtainCustomerFilter(String column, String search)
	{
		String query = "SELECT  * FROM customer, address, contact_info "
			+ "where customer.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and customer.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
		ArrayList<Customer> customers = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String customerID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String contactInfoID = rs.getString(4);
				String addressID = rs.getString(5);
				String streetAddress = rs.getString(7);
				String city = rs.getString(8);
				String state = rs.getString(9);
				String zipCode = rs.getString(10);
				String unitNumber = rs.getString(11);
				String homePhone = rs.getString(13);
				String cellPhone = rs.getString(14);
				String emailAddress = rs.getString(15);
			
				Customer c = new Customer();
				c.setCustomerID(customerID);
				c.setLastName(lastName);
				c.setFirstName(firstName);
				c.setContactInfoID(contactInfoID);
				c.setAddressID(addressID);
				c.setStreetAddress(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
				
				customers.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return customers;
	}

	public ArrayList<Employee> obtainEmployeeFilter(String column, String search)
	{
		String query = "SELECT  * FROM employee, address, contact_info "
			+ "where employee.contact_info_contact_info_id = contact_info.contact_info_id "
			+ "and employee.Address_address_id = address.address_id and " + column + " = '" + search + "'";
	
		ArrayList<Employee> employees = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String employeeID = rs.getString(1);
				String lastName = rs.getString(2);
				String firstName = rs.getString(3);
				String contactInfoID = rs.getString(4);
				String addressID = rs.getString(5);
				String streetAddress = rs.getString(7);
				String city = rs.getString(8);
				String state = rs.getString(9);
				String zipCode = rs.getString(10);
				String unitNumber = rs.getString(11);
				String homePhone = rs.getString(13);
				String cellPhone = rs.getString(14);
				String emailAddress = rs.getString(15);
				
				Employee e = new Employee();
				e.setEmployeeID(employeeID);
				e.setLastName(lastName);
				e.setFirstName(firstName);
				e.setContactInfoID(contactInfoID);
				e.setAddressID(addressID);
				e.setStreetAddress(streetAddress);
				e.setCity(city);
				e.setState(state);
				e.setZipCode(zipCode);
				e.setUnitNumber(unitNumber);
				e.setPhoneNumber(homePhone);
				e.setCellPhoneNumber(cellPhone);
				e.setEmailAddress(emailAddress);
			
				employees.add(e);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return employees;
	}

	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
		String column, String search) {
	
	
		String query = "SELECT * FROM invoice_line_item where invoice_invoice_number "
			+ "= '" + invoiceNumberInput  + "' and " + column + " = '" + search + "'";
		ArrayList<InvoiceLineItem> invoiceLineItems = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String invoiceLineNumber = rs.getString(1);
				String invoiceNumber = rs.getString(2);
				String quantityPurchased = rs.getString(3);
				String productID = rs.getString(4);
			
				InvoiceLineItem i = new InvoiceLineItem();
				i.setInvoiceLineNumber(invoiceLineNumber);
				i.setInvoiceNumber(invoiceNumber);
				i.setQuantityPurchased(quantityPurchased);
				i.setProductID(productID);
				
			
				invoiceLineItems.add(i);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return invoiceLineItems;
	}

	
	public ArrayList<Invoice> obtainInvoiceFilter(String column, String search) {
	
		String query = "SELECT * FROM invoice where " + column + " = '" + search + "'";
		ArrayList<Invoice> invoices = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String invoiceNumber = rs.getString(1);
				String date = rs.getString(2);
				String time = rs.getString(3);
				String customerID = rs.getString(4);
				String employeeID = rs.getString(5);
		
				Invoice i = new Invoice();
				i.setInvoiceNumber(invoiceNumber);
				i.setDate(date);
				i.setTime(time);
				i.setCustomerID(customerID);
				i.setEmployeeID(employeeID);
			
				invoices.add(i);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return invoices;
	}


	public ArrayList<Company> obtainCompanyFilter(String column, String search) {
	
		String query = "select * from company, address, contact_info "
			+ "where Address_address_id = address.address_id "
			+ "and contact_info_contact_info_id = contact_info.contact_info_id and " + column + " = '" + search + "'";
	
		ArrayList<Company> company = new ArrayList<>();
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String companyID = rs.getString(1);
				String addressID = rs.getString(2);
				String contactID = rs.getString(3);
				String companyName = rs.getString(4);
				String streetAddress = rs.getString(6);
				String city = rs.getString(7);
				String state = rs.getString(8);
				String zipCode = rs.getString(9);
				String unitNumber = rs.getString(10);
				String homePhone = rs.getString(12);
				String cellPhone = rs.getString(13);
				String emailAddress = rs.getString(14);
			
				Company c = new Company();
			
				c.setCompanyID(companyID);
				c.setAddressID(addressID);
				c.setContactInfoID(contactID);
				c.setCompanyName(companyName);
				c.setStreetAddres(streetAddress);
				c.setCity(city);
				c.setState(state);
				c.setZipCode(zipCode);
				c.setUnitNumber(unitNumber);
				c.setPhoneNumber(homePhone);
				c.setCellPhoneNumber(cellPhone);
				c.setEmailAddress(emailAddress);
			
				company.add(c);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return company;
	}

	
	public String obtainCompanyName(String companyID) {
	
		String query = "SELECT company_name FROM company where company_id  = '" 
			+ companyID +"'";
	
		String companyName = "";
	
		Statement stmt = null;
	
		connObj = getDBConnection();
					
		try {	
			stmt = connObj.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				companyName = rs.getString(1);
			}
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		}
	
		closeConnection(connObj);
			
		return companyName;
	}

	
	public String obtainNewAddressID(String stAddress, String city, String state, 
			String zipCode, String unitNumber) {
		
		String addressID = null;
		String query = null;
		ResultSet rs = null;
		
		if(unitNumber.equals("NULL")) {
			query = "select address_id " +
					"from address " +
					"where street_address = '" + stAddress + "' and city = '" + city + "' and state = '" + state +
					"' and zip_code = '" + zipCode + "' and unit_number  is null';";
		}
		else {
			query = "select address_id from address where street_address = '" + stAddress +
					"' and city = '" + city + "' and state = '" + state +
					"' and zip_code = '" + zipCode +
					"' and unit_number = '" + unitNumber + "';";
		}
		
		Statement stmt = null;
								
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				addressID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);		
				
		return addressID;
	}
	
	
	public String obtainNewContactInformationID(String phoneNumber, String cellPhone, 
			String emailAddress) {
		
		String contactInfoID = null;
		String query = null;
		ResultSet rs = null;
		
		query = "select contact_info_id " +
				"from contact_info " +
				"where phone_number = '" + phoneNumber + "' and cell_phone_number = '" + cellPhone +
				"' and email_address = '" + emailAddress + "';";
		
		Statement stmt = null;
										
		connObj = getDBConnection();
										
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				contactInfoID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
						
		closeConnection(connObj);		
		
		return contactInfoID;
	}
	
	
	public String obtainNewInvoiceNumber(String date, String time, String customerID,
			String employeeID) {
		
		String invoiceNum = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select invoice_number " +
				"from invoice " +
				"where date = '" + date + "' and time = '" + time +
				"' and customer_customer_id = '" + customerID + "' and employee_employee_id = '" +
				employeeID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				invoiceNum = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		return invoiceNum;
	}
	
	
	public String obtainLineItemID(String invoiceID, String quantityPurchased, String productID) {
		
		String lineID = "";
		String query = null;
		ResultSet rs = null;
		
		query = "select invoice_line_number " +
				"from invoice_line_item " +
				"where invoice_invoice_number = '" + invoiceID + "' and quantity_purchased = '" +
				quantityPurchased + "' and product_product = '" + productID + "';";
		
		Statement stmt = null;  
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				lineID = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		return lineID;
	}
	

	public String obtainInvoiceLineID(String invoiceNumber, String purchasedQuantity,
			String productID) {
		
		String lineNumber = "";
		
		String query = null;
		ResultSet rs = null;
		
		query = "SELECT invoice_line_number "
				+ "FROM invoice_line_item "
				+ "WHERE invoice_invoice_number = '" + invoiceNumber 
				+ "' AND quantity_purchased = '" + purchasedQuantity 
				+ "' AND product_product = '" + productID + "';";
		
		Statement stmt = null;
		
		connObj = getDBConnection();
								
		try {	
			stmt = connObj.createStatement();
			rs = stmt.executeQuery(query);			
			while(rs.next()) {
				lineNumber = rs.getString(1);
			}
		}											
		catch (SQLException e) {					
			System.out.println(e.toString());
		}
				
		closeConnection(connObj);
		
		return lineNumber;
	}
}
