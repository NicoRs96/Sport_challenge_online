//Paolo Campus

package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import bean.IscrivitiBean;

public class TestIscrizione {

	IscrivitiBean iscrivitiBean = new IscrivitiBean();
    
	
	@Test
	public void testAnnoNascitaNonValido() {
		
		iscrivitiBean.setNome("Nomeprova");
		iscrivitiBean.setCognome("cognomeprova");
		iscrivitiBean.setData(LocalDate.of(2010, 12, 31));
		iscrivitiBean.setMail("mail@prova.it");
		iscrivitiBean.setPw("pwprova");
		iscrivitiBean.setTelefono("123654");
		iscrivitiBean.setCb(false);
		
		
		
		assertEquals(1, iscrivitiBean.checkData());
		
		
	}
	@Test
	public void testEmailNonValida() {
		iscrivitiBean.setNome("Nomeprova");
		iscrivitiBean.setCognome("cognomeprova");
		iscrivitiBean.setData(LocalDate.of(2000, 12, 31));
		iscrivitiBean.setMail("mailatprova.it");
		iscrivitiBean.setPw("pwprova");
		iscrivitiBean.setTelefono("123654");
		iscrivitiBean.setCb(false);
		
		
		
		assertEquals(1, iscrivitiBean.checkMail());
	}

}
