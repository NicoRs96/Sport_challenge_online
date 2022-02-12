//Paolo Campus

package test;


import logic.bean.ScegliLivelloBean;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class TestScegliLivello {

	@Test
	public void testCambiaLivello() {
		
		ScegliLivelloBean scegliLivelloBean=new ScegliLivelloBean();
		boolean test=true;
		try {
			scegliLivelloBean.setLivello(12, "calcio", "dilettante");
		} catch (SQLException e) {
			test=false;
		}
		
		assertEquals(true, test);
	}

}
