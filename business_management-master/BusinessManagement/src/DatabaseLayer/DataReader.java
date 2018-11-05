
package DatabaseLayer;


import java.util.ArrayList;
import BusinessLayer.Company;
import BusinessLayer.Customer;
import BusinessLayer.Employee;
import BusinessLayer.Invoice;
import BusinessLayer.InvoiceLineItem;


public interface DataReader {

	public ArrayList<Company> obtainCompanyList();
	public ArrayList<Customer> obtainCustomerList();
	public ArrayList<Employee> obtainEmployeeList();
	public ArrayList<Invoice> obtainInvoiceList();
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemList(String invoiceNumberInput);
	public String obtainPassword(String username);
	public ArrayList<Customer> obtainCustomerFilter(String column, String search);
	public ArrayList<Employee> obtainEmployeeFilter(String column, String search);
	public ArrayList<InvoiceLineItem> obtainInvoiceLineItemFilter(String invoiceNumberInput,
			String column, String search);
	public ArrayList<Invoice> obtainInvoiceFilter(String column, String search);
	public ArrayList<Company> obtainCompanyFilter(String column, String search);

	public String obtainCompanyName(String companyID);
	
	public String obtainNewAddressID(String stAddress, String city, String state,
			String zipCode, String unitNumber);
	public String obtainNewContactInformationID(String phoneNumber, String cellPhone,
			String emailAddress);
	
	public String obtainNewInvoiceNumber(String date, String time, String customerID,
			String employeeID);
	public String obtainLineItemID(String invoiceID, String quantityPurchased, 
			String productID);
	
	public String obtainInvoiceLineID(String invoiceNumber, String purchasedQuantity,
			String productID);
}
