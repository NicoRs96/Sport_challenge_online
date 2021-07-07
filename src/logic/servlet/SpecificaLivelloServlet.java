package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ScegliLivelloBean;

/**
 * Servlet implementation class SpecificaLivelloServlet
 */
@WebServlet("/SpecificaLivello")
public class SpecificaLivelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScegliLivelloBean scegliLivelloBean = new ScegliLivelloBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecificaLivelloServlet() {
        super();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   
		



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(IndexServlet.getUser().get("ID"));
		String sportString = req.getParameter("selezionasport");
		String livelloString = req.getParameter("livello");
		
		try {
			
			scegliLivelloBean.setLivello(id, sportString, livelloString);
			
		} catch (SQLException e) {
			//nothing
			
			}
        resp.sendRedirect("HomePageSportman.jsp");

	}
		
	

}
