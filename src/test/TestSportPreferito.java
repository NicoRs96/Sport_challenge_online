//Nicola Rossi
package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import bean.SportPreferitoBean;

public class TestSportPreferito {

	@Test
	public void testSportPreferito() {
		SportPreferitoBean sportPreferitoBean = new SportPreferitoBean();
		boolean test=true;
		
		try {
			sportPreferitoBean.setSportPreferito(11, "calcio");
		} catch (SQLException e) {
			test=false;
		}
	
		assertEquals(true, test);
	}

}
