package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SportPreferitoBean;

/**
 * Servlet implementation class SportPreferitoServlet
 */
@WebServlet("/SportPreferito")
public class SportPreferitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SportPreferitoBean sportPreferitoBean = new SportPreferitoBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SportPreferitoServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(IndexServlet.getUser().get("ID"));
		String sportString = request.getParameter("selezionasport");
		
		try {
			sportPreferitoBean.setSportPreferito(id, sportString);
		} catch (SQLException e) {
			//nothing
		}
        response.sendRedirect("HomePageSportman.jsp");

		
	}

	
}
