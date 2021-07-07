package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CancellaAccountBean;

/**
 * Servlet implementation class DisiscrivitiServlet
 */
@WebServlet("/HomePageSportman")
public class DisiscrivitiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CancellaAccountBean cancellaAccountBean = new CancellaAccountBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisiscrivitiServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(IndexServlet.getUser().get("ID"));

		try {
			cancellaAccountBean.deleteAccount(id);
		} catch (SQLException e) {
			//nothing
		}
        response.sendRedirect("index.jsp");

	}

	

}
