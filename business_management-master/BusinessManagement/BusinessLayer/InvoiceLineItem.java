
package BusinessLayer;


public class InvoiceLineItem {
	
	// Fields
	private String invoiceLineNumber;
	private String invoiceNumber;
	private String quantityPurchased;
	private String serviceItem;
	
	// Default Constructor
	public InvoiceLineItem() {
		this("", "", "", "");
	}
	
	// Overloaded Constructor
	public InvoiceLineItem(String invoiceLineNumber, String invoiceNumber,
			String quantityPurchased, String serviceItem) {
		this.invoiceLineNumber = invoiceLineNumber;
		this.invoiceNumber = invoiceNumber;
		this.quantityPurchased = quantityPurchased;
		this.serviceItem = serviceItem;
	}
	
	// Get and Set Accessors/Mutators
	public void setInvoiceLineNumber(String invoiceLineNumber) {
		this.invoiceLineNumber = invoiceLineNumber;
	}
	
	public String getInvoiceLineNumber() {
		return invoiceLineNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setQuantityPurchased(String quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	
	public String getQuantityPurchased() {
		return quantityPurchased;
	}
	
	public void setServiceItem(String productID) {
		this.serviceItem = serviceItem;
	}
	
	public String getServiceItem() {
		return serviceItem;
	}
	
	@Override
	public String toString() {
		return serviceItem + ", " + invoiceNumber + ", " + invoiceLineNumber + ", " +
				quantityPurchased;
	}

    
}
