package servlet;

import bean.GestisciCampiBean;
import bean.GestisciMieiEventiBean;
import model.Campo;
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
import java.text.ParseException;
import java.util.*;

@WebServlet("/IMieiEventi")
public class MieiEventiServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GestisciMieiEventiBean gestisciMieiEventiBean = new GestisciMieiEventiBean();
    private int utenteId;

    public MieiEventiServlet() {
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
            utenteId = Integer.parseInt(c.getValue());
        }
        else
            response.sendRedirect("/index.jsp");

        try{
            SortedMap<Integer, ArrayList<TreeMap<String, String>>> campo = gestisciMieiEventiBean.getCampi(utenteId);
            List<Campo> campi = new ArrayList<>();

            campo.values().forEach(x ->
                    x.stream().forEach(y -> {
                        Campo c = new Campo(
                                y.get("NOME"),
                                y.get("COMUNE"),
                                y.get("INDIRIZZO"));

                        c.setSport(y.get("SPORT"));
                        c.setId(Integer.parseInt(y.get("ID")));
                        c.setDesc(y.get("DESC"));
                        c.setData(y.get("DATA"));
                        c.setOra(y.get("ORA"));
                        c.setModPagamento(y.get("METODODIPAGAMENTO"));
                        campi.add(c);

                    }));

            request.setAttribute("campi", campi);


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            List<Torneo> tornei = gestisciMieiEventiBean.getTornei(utenteId);
            request.setAttribute("tornei", tornei);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("/IMieiEventi.jsp");
        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

        String campoId = request.getParameter("campoId");
        String torneoId = request.getParameter("torneoId");

        System.out.println(request.getParameter("exit"));
        if(request.getParameter("exit") != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/HomePageSportman.jsp");
            dispatcher.include(request, response);
            return;
        }

        if(campoId == null && torneoId == null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/MieiEventi");
            dispatcher.include(request, response);
            return;
        }

        if(request.getParameter("cancellaC") != null) {
            try{
                gestisciMieiEventiBean.cancellaPrenotazioneCampo(Integer.parseInt(campoId));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if(request.getParameter("cancellaT") != null) {
            try{
                gestisciMieiEventiBean.cancellaPrenotazioneTorneo(Integer.parseInt(torneoId), utenteId);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/MieiEventi");
        dispatcher.include(request, response);
        return;

    }

}