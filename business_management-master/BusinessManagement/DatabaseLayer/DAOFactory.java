
package DatabaseLayer;

import DatabaseLayer.WriterDAO;


public class DAOFactory {

	
	public static WriterDAO getWriterDAO() {
		
		WriterDAO wDAO = (WriterDAO) new DatabaseWriter();
		return wDAO;
	}
	
	
	public static ReaderDAO getReaderDAO() {
		
		DatabaseReader rDAO = new DatabaseReader() {};
		return rDAO;
	}
	
	
	
}
