package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.GestisciCampiBean;
import model.Campo;
import sun.security.provider.certpath.ResponderId;

/**
 * Servlet implementation class GestisciCampiSportivi
 */
@WebServlet("/GestisciCampiSportivi")
public class GestisciCampiSportivi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciCampiSportivi() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		GestisciCampiBean gestisciCampiBean = new GestisciCampiBean();
		try {
			SortedMap<Integer, ArrayList<TreeMap<String, String>>> campo = gestisciCampiBean.getCampi(13);
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<String> testList= new ArrayList<>();
		testList.add("test1");
		testList.add("test2");
		testList.add("test3");
		
		System.out.println("test");
		request.setAttribute("campo",testList );
		RequestDispatcher dispatcher = request.getRequestDispatcher("GestisciCampiSportivi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
