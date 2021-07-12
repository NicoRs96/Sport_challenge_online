package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bean.GestisciTorneiRenterBean;
import model.Campo;
import model.Persona;
import model.Prenotazione;
import model.Torneo;

/**
 * Servlet implementation class GestisciCampiSportivi
 */
@WebServlet("/GestisciTornei")
public class GestisciTorneiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestisciTorneiRenterBean gestisciTorneiBean = new GestisciTorneiRenterBean();
    private int renterId;
    private static String costanteString="/GestisciTornei.jsp";
    private static String confermaString="conferma";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciTorneiServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> opt = Arrays.stream(cookies).filter(x -> x.getName().equals("user")).findFirst();
        if(opt.isPresent()) {
            Cookie c = opt.get();
            renterId = Integer.parseInt(c.getValue());
        }
        else
            response.sendRedirect("/index.jsp");

        try {
            List<Torneo> tornei = gestisciTorneiBean.getTorneiByRenterId(renterId);
            request.setAttribute("tornei", tornei);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(costanteString);
        dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("exit") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/HomePageRenter.jsp");
            dispatcher.include(request, response);
            return;
        }

        String torneoId = request.getParameter("torneoId");
        String partecipanteId = request.getParameter(confermaString) != null ? request.getParameter(confermaString)  : request.getParameter("cancella");
        if(torneoId == null && partecipanteId == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher(costanteString);
            dispatcher.include(request, response);
            return;
        }

        if(torneoId != null) {
            try {
                List<Persona> iscritti = gestisciTorneiBean.getIscrittiByTorneoId(Integer.parseInt(torneoId));
                request.setAttribute("iscritti", iscritti);
                Cookie cookie  = new Cookie("torneo", torneoId);
                response.addCookie(cookie);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        torneoId = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals("torneo")).findFirst().get().getValue();
        if(request.getParameter(confermaString) != null){
            try {

                gestisciTorneiBean.confermaIscrizione(Integer.parseInt(partecipanteId), Integer.parseInt(torneoId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(request.getParameter("cancella") != null){
            try{
                gestisciTorneiBean.cancellaIscrizione(Integer.parseInt(partecipanteId), Integer.parseInt(torneoId));

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            List<Torneo> tornei = gestisciTorneiBean.getTorneiByRenterId(renterId);
            request.setAttribute("tornei", tornei);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(costanteString);
        dispatcher.include(request, response);




    }

}
