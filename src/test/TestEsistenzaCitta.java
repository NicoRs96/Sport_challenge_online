//Nicola Rossi

package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import dao.CercaCampoDao;

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
