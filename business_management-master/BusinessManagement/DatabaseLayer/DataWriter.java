
package DatabaseLayer;

import java.util.ArrayList;


public interface DataWriter {

	public void manageNewPersonCreation(String choice, String lastName, String firstName,
			String stAddress, String city, String state, String zipCode, String unitNumber,
			String phoneNumber, String cellPhone, String emailAddress, String companyID);
	
	public void createNewCompany(String stAddress, String city, String state,
			String zipCode, String unitNumber, String phoneNumber, String cellPhone,
			String emailAddress, String companyName);
	
	
	public void createInvoice(String date, String time, String customerID,
			String employeeID, String notes, String comments);
	
	// copy from writeHelper due to gui changes
	public void createInvoiceLineItem(String invoiceNumber, String purchasedQuantity,
			String serviceItem);
	
	//New for different gui
	public boolean checkCompanyExists(String companyID);
	
	
	public boolean checkCustomerExists(String customerID);
	
	//New for different gui
	public boolean checkEmployeeExists(String employeeID);
		
	
	public void manageEditingCustomer(String customerID, String contactID, String addressID, 
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email);
	
	public void manageEditingEmployee(String employeeID, String contactID, String addressID,
			String lastName, String firstName, String streetAddress, String city,
			String state, String zipCode, String unitNumber, String homePhone,
			String cellPhone, String email);

	
	public void manageEditingCompany(String companyID, String addressID, 
			String contactID, String streetAddress, String city, String state, 
			String zipCode, String unitNumber, String homePhone, String cellPhone,
			String email, String companyName);

}
