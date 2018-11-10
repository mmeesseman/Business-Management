
package BusinessLayer;

public class Invoice {

	// Fields
	private String invoiceNumber;
	private String date;
	private String time;
	private String customerID;
	private String employeeID;
        private String notes;
        private String comments;
	
	// Default Constructor
	public Invoice() {
		this("", "", "", "", "", "", "");
	}
	
	// Overloaded Constructor
	public Invoice(String invoiceNumber, String date, String time, 
			String customerID, String employeeID, String notes, String comments) {
		this.invoiceNumber = invoiceNumber;
		this.date = date;
		this.time = time;
		this.customerID = customerID;
		this.employeeID = employeeID;
                this.notes = notes;
                this.comments = comments;
	}
	
	// Get and Set Accessors/Mutators
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getEmployeeID() {
		return employeeID;
	}
        
        public void setNotes(String notes) {
            this.notes = notes;
        }
        
        public String getNotes(){
            return notes;
        }
        
        public void setComments(String comments){
            this.comments = comments;
        }
        
        public String getComments(){
            return comments;
        }
        
	
	@Override
	public String toString() {
		return invoiceNumber;
	}
}
