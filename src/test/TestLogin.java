//Nicola Rossi

package test;


import logic.bean.LoginBean;
import org.junit.Test;


import java.sql.SQLException;

import static org.junit.Assert.assertEquals;


public class TestLogin {

	@Test
	public void testLogin() {
		LoginBean loginBean = new LoginBean();
		
		loginBean.setUsername("pasquale.roma@hotmail.it");
		loginBean.setPassword("pasquale");
		int i;
		try {
			i=loginBean.userExist();
		} catch (SQLException e) {
			i=1;
		}
		assertEquals(1, i);
	}

}
