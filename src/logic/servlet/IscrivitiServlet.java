package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.IscrivitiBean;

/**
 * Servlet implementation class IscrivitiServlet
 */
@WebServlet("/Iscriviti")
public class IscrivitiServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private IscrivitiBean iscrivitiBean;


	    
	    @Override
	    public void init() {
	    	
	        iscrivitiBean = new IscrivitiBean();
		   

	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
			PrintWriter out;

			 out = response.getWriter();


	    	String nome = request.getParameter("Nome");
	    	String cognomeString = request.getParameter("Cognome");
	    	LocalDate datanascitaDate = LocalDate.parse(request.getParameter("mydatetime"));
	    	String teleString = request.getParameter("Telefono");
	    	String emailString = request.getParameter("Email");	    	   	
	        String password = request.getParameter("Password");
	        Boolean cBoolean = Boolean.valueOf(request.getParameter("cb"));
	        
	        iscrivitiBean.setNome(nome);
	        iscrivitiBean.setCognome(cognomeString);
	        iscrivitiBean.setData(datanascitaDate);
	        iscrivitiBean.setTelefono(teleString);
	        iscrivitiBean.setMail(emailString);
	        iscrivitiBean.setPw(password);
	        iscrivitiBean.setCb(cBoolean);
	        
	        if(iscrivitiBean.checkData()==1) {
	            out.println("Data nascita sbagliata. Devi avere età minima 14 anni");

	        	return;
	        }
	        	        
	        if (iscrivitiBean.checkMail()==1) {
	        	out.println("Mail sbagliata");
	        	return;
	        }
	        
	        try {
				iscrivitiBean.aggiungiUtente();
				
				HttpSession session = request.getSession();
                session.setAttribute("Email",emailString);
                response.sendRedirect("index.jsp");
			} catch (SQLException e) {

	        	out.println("Errore nella registrazione");
			}
	        
	    }
}
