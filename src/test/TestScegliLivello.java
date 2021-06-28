//Paolo Campus

package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import bean.ScegliLivelloBean;

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
