//Paolo Campus


package test;



import logic.bean.CancellaAccountBean;
import logic.model.Persona;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class TestCancellaAccount {

	@Test
	public void testCancella() {
		
		Persona persona = new Persona(999999999, "NomeInesistente", "CognomeInesistente", "mailnonesistente", LocalDate.of(1900, 01, 01), "123654","0");
		CancellaAccountBean cancellaAccountBean = new CancellaAccountBean();
		
		boolean result=true;
		try {
			result = cancellaAccountBean.deleteAccount(persona.getId());
		} catch (SQLException e) {
			result = false;
		}
		
		assertEquals(false, result);
		
		
		
	}

}
