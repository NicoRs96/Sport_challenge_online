//Nicola Rossi
package test;


import logic.bean.SportPreferitoBean;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


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
