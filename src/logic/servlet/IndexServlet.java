package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.SortedMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bean.LoginBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginBean loginBean;
	private static SortedMap<String, String> user;
	
	

    @Override
    public void init() {
    	
        loginBean = new LoginBean();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        
        
        try {
            if (loginBean.userExist() == 1) {
        		
                HttpSession session = request.getSession();
                session.setAttribute("username",username);
                response.sendRedirect("index.jsp");

            } else {
            	user = loginBean.getUser();

                Cookie cookie = new Cookie("user", user.get("ID"));
                response.addCookie(cookie);
            	if((user.get("RENT")).equals("0")){
	                HttpSession session = request.getSession();
	                session.setAttribute("user", username);
	                response.sendRedirect("HomePageSportman.jsp");
	             
            }
            	else {
            		HttpSession session = request.getSession();
	                session.setAttribute("user", username);
	                response.sendRedirect("HomePageRenter.jsp");
            	}
        }} catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static SortedMap<String, String> getUser() {
		return user;
	}
}
