
package DatabaseLayer;


public class DAOFactory {

	
	public static WriterDAO getWriterDAO() {
		
		WriterDAO wDAO = new DatabaseWriter();
		return wDAO;
	}
	
	
	public static ReaderDAO getReaderDAO() {
		
		DatabaseReader rDAO = new DatabaseReader();
		return rDAO;
	}
	
	
	
}
