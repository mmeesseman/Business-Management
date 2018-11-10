package PresentationLayer;

import java.sql.SQLException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import BusinessLayer.InvoiceLineItem;

import DatabaseLayer.DAOFactory;
import DatabaseLayer.DatabaseReader;
import DatabaseLayer.ReaderDAO;


public class SalesItemTableModel extends AbstractTableModel {
	
				
	//return list for some methods initialized
				private List<InvoiceLineItem> invoiceLineItems;

				private ReaderDAO readerDAO = DAOFactory.getReaderDAO();
				
				//table column names
				private final String[] COLUMN_NAMES = {"Invoice Line Number", "Invoice Number", 
					  	"Quantity Purchased", "Service Item"};
				String invoiceNumberInput;
					    
				
				public SalesItemTableModel(String invoiceNumberInput) throws SQLException{
					 this.invoiceNumberInput = invoiceNumberInput;
				     invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
				     
				}
					    
				
					    @Override 
					    public int getRowCount() {
					        return invoiceLineItems.size();
					    }
					    
					    
					    @Override
					    public int getColumnCount() {
					        return COLUMN_NAMES.length;
					    }
					    
					    
					    @Override
					    public String getColumnName(int columnIndex) {
					        return COLUMN_NAMES[columnIndex];
					    }
					    
					    
					    @Override
					    public Object getValueAt(int rowIndex, int columnIndex) {
					        switch (columnIndex) {
					            case 0:
					                return invoiceLineItems.get(rowIndex).getInvoiceLineNumber();
					            case 1:
					                return invoiceLineItems.get(rowIndex).getInvoiceNumber();
					            case 2:
					                return invoiceLineItems.get(rowIndex).getQuantityPurchased();
					            case 3: 
					            	return invoiceLineItems.get(rowIndex).getServiceItem();
					            default:
					                return null;
					        }
					    }
					    
					    InvoiceLineItem getInvoiceLineItem(int rowIndex) throws SQLException {
					        return invoiceLineItems.get(rowIndex);
					    }
					    
					    
					    void databaseUpdated() throws SQLException{
					        invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
							fireTableDataChanged();
					    }
					    
					    
					    public void refresh(String column, String search){
					        invoiceLineItems = readerDAO.obtainInvoiceLineItemFilter(invoiceNumberInput, column, search);
					        fireTableDataChanged();
					    }
					    
					    
					    public List<InvoiceLineItem> resultChecker(String column, String search) {
					    	
					    	List<InvoiceLineItem> lineItem = readerDAO.obtainInvoiceLineItemFilter(invoiceNumberInput, column, search);
					    	
					    	return lineItem;
					    }
					    
					    
					    public void reset(){
					    	 invoiceLineItems = readerDAO.obtainInvoiceLineItemList(invoiceNumberInput);
					        fireTableDataChanged();
					    }
					}











	

