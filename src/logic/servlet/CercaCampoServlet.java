package servlet;

import bean.CercaCampoBean;
import bean.CercaTorneoBean;
import bean.GestisciTorneiRenterBean;
import exception.ConnectionClosedFXException;
import model.Campo;
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


@WebServlet("/CercaCampo")
public class CercaCampoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CercaCampoBean cercaCampoBean = new CercaCampoBean();
    private int utenteId;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaCampoServlet() {
        super();
    }

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> opt = Arrays.stream(cookies).filter(x -> x.getName().equals("user")).findFirst();
        if(opt.isPresent()) {
            Cookie c = opt.get();
            utenteId = Integer.parseInt(c.getValue());
        }
        else
            response.sendRedirect("/index.jsp");

        if (request.getParameter("cerca") != null) {
            try {
                SortedMap<String, TreeMap<String, String>> campo = cercaCampoBean.getCampo(request.getParameter("citta"), request.getParameter("sport"), request.getParameter("data"));

                List<Campo> campi = new ArrayList<>();
                
                for(Map.Entry<String, TreeMap<String, String>> entry: campo.entrySet())
                 {
                	
                	String keyString=entry.getKey();
                	
                    TreeMap<String, String> x = campo.get(keyString);
                    Campo c = new Campo(
                            keyString,
                            x.get("COMUNE"),
                            x.get("INDIRIZZO"));

                    c.setDesc(x.get("DESC"));
                    c.setOra(x.get("ORA"));
                    c.setSport(request.getParameter("sport"));
                    c.setRenter(x.get("RENTER"));
                    c.setId(Integer.parseInt(x.get("ID")));
                    campi.add(c);
                }

                request.setAttribute("campi", campi);

            } catch (ConnectionClosedFXException|ClassNotFoundException  e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if(request.getParameter("seleziona") != null){
            if(request.getParameter("campoId") == null){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/CercaCampoSportivo.jsp");
                dispatcher.include(request, response);
                return;
            }
            try {
                cercaCampoBean.confermaPrenotazione(utenteId, Integer.parseInt(request.getParameter("campoId")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CercaCampoSportivo.jsp");
        dispatcher.include(request, response);



     }
}
