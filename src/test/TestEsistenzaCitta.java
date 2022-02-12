//Nicola Rossi

package test;


import logic.dao.CercaCampoDao;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class TestEsistenzaCitta {

	@Test
	public void testEsiste() {

		CercaCampoDao cercaCampoDao = new CercaCampoDao();
		boolean result=true;
		
		try {
			result = cercaCampoDao.isCityAvailable("Roma");
		} catch (SQLException e) {
			result=false;
		}
		assertEquals(true, result);
	}
	
	@Test
	public void testNonEsiste() {

		CercaCampoDao cercaCampoDao = new CercaCampoDao();
		boolean result=true;
		
		try {
			result = cercaCampoDao.isCityAvailable("Atlantide");
		} catch (SQLException e) {
			result=false;
		}
		assertEquals(false, result);
	}

}
