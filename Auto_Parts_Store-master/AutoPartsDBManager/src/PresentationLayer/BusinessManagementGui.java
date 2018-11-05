
package PresentationLayer;

import java.util.ArrayList;
import java.sql.SQLException;

import javax.swing.UnsupportedLookAndFeelException;

import DatabaseLayer.*;
import BusinessLayer.*;


public class BusinessManagementGui {

	private static WriterDAO writerDAO;
	
	/**
     * @param args the command line arguments
	 * @throws SQLException 
     */
    @SuppressWarnings("unused")
	public static void main(String[] args) throws UnsupportedLookAndFeelException, SQLException {
        	
    	LoginFrame loginFrame = new LoginFrame();
    	initialize();
		writerDAO = DAOFactory.getWriterDAO();
	
		
		// read any incoming products
		
    }
	
	private static void initialize() {
	}
}
