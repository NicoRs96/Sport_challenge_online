package servlet;

import bean.CercaTorneoBean;
import bean.GestisciTorneiRenterBean;
import model.Prenotazione;
import model.Torneo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;


@WebServlet("/CercaTorneo")
    public class CercaTorneoServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
        private CercaTorneoBean cercaTorneoBean = new CercaTorneoBean();
        private int utenteId;

        /**
         * @see HttpServlet#HttpServlet()
         */
        public CercaTorneoServlet() {
            super();
        }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            response.getWriter().append("Served at: ").append(request.getContextPath());


        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        

        if (request.getParameter("cerca") != null) {
            try {
                SortedMap<Integer, TreeMap<String, String>> torneo = cercaTorneoBean.getTornei(request.getParameter("citta"), request.getParameter("data"));

                List<Torneo> tornei = new ArrayList<>();
                for(Map.Entry<Integer, TreeMap<String, String>> entry: torneo.entrySet())
                {
               	
               	Integer keyString=entry.getKey();
                
                    TreeMap<String, String> x = torneo.get(keyString);
                    Torneo t = new Torneo(
                            x.get("NOME"),
                            x.get("CAMPO"),
                            LocalDate.parse(x.get("DATA")),
                            x.get("ORA"),
                            Double.parseDouble(x.get("PREZZO")),
                            Integer.parseInt(x.get("ETA")),
                            Integer.parseInt(x.get("NUMEROMIN")));

                    t.setId(keyString);
                    t.setDataScadenza(x.get("DATASCADENZA"));
                    tornei.add(t);
                }

                request.setAttribute("tornei", tornei);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else if(request.getParameter("conferma") != null){
            if(request.getParameter("torneoId") == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/CercaTorneo.jsp");
                dispatcher.include(request, response);
                return;
            }
            try {
                cercaTorneoBean.confermaIscrizione(utenteId, Integer.parseInt(request.getParameter("torneoId")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CercaTorneo.jsp");
        dispatcher.include(request, response);

    }
}
