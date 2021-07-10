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


import bean.GestisciCampiBean;
import model.Campo;
import model.Prenotazione;
import sun.security.provider.certpath.ResponderId;

/**
 * Servlet implementation class GestisciCampiSportivi
 */
@WebServlet("/GestisciCampiSportivi")
public class GestisciCampiSportiviServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestisciCampiBean gestisciCampiBean = new GestisciCampiBean();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestisciCampiSportiviServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Cookie[] cookies = request.getCookies();
		Optional<Cookie> opt = Arrays.stream(cookies).filter(x -> x.getName().equals("user")).findFirst();
		int renterId = 0;
		if(opt.isPresent()) {
			Cookie c = opt.get();
			renterId = Integer.parseInt(c.getValue());
		}
		else
			response.sendRedirect("/index.jsp");

		try {
			SortedMap<Integer, ArrayList<TreeMap<String, String>>> campo = gestisciCampiBean.getCampi(renterId);
			List<Campo> campi = new ArrayList<>();

			campo.values().forEach(x ->
					x.stream().forEach(y -> {
						Campo c = new Campo(Integer.parseInt(y.get("ID")),
								y.get("NOME"),
								y.get("COMUNE"),
								y.get("INDIRIZZO"),
								"1",
								Integer.parseInt(y.get("AFFITTABILE")));

						c.setDesc(y.get("DESC"));
						c.setData(y.get("DATA"));
						c.setOra(y.get("ORA"));
						c.setModPagamento(y.get("METODODIPAGAMENTO"));
						campi.add(c);

					}));

			request.setAttribute("campi", campi);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try{
			SortedMap<Integer, TreeMap<String, String>> prenotazione = gestisciCampiBean.getPrenotazioni(renterId);
			List<Prenotazione> prenotazioni = new ArrayList<>();
			for (Integer i: prenotazione.keySet()) {
				TreeMap<String, String> x = prenotazione.get(i);
				Prenotazione p = new Prenotazione(
						i,
						x.get("CAMPO"),
						x.get("DATA"),
						x.get("ORA"),
						x.get("PREZZO"),
						x.get("NOMECLIENTE"),
						x.get("COGNOMECLIENTE"));
				p.setTelefonoCliente(x.get("TELEFONO"));
				prenotazioni.add(p);
			}
			request.setAttribute("prenotazioni", prenotazioni);


		}catch (SQLException e){
			e.printStackTrace();
			}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/GestisciCampiSportivi.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		String id = request.getParameter("campoRadio");
		String idPrenotazione = request.getParameter("pRadio");
		if(request.getParameter("cancellaP")== null && id == null){
			RequestDispatcher dispatcher = request.getRequestDispatcher("/GestisciCampiSportivi.jsp");
			dispatcher.include(request, response);
			return;
		}
		if(request.getParameter("affittabile") != null){
			try {
				gestisciCampiBean.setCampoAffittabile(Integer.parseInt(id));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestisciCampiSportivi.jsp");
				dispatcher.include(request, response);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}else if(request.getParameter("cancella")!= null){
			try {
				gestisciCampiBean.setCampoNonAffittabile(Integer.parseInt(id));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/GestisciCampiSportivi.jsp");
				dispatcher.include(request, response);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}else if(request.getParameter("cancellaP") != null){
			if(idPrenotazione != null) {
				try {
					gestisciCampiBean.cancellaPrenotazione(Integer.parseInt(idPrenotazione));
					RequestDispatcher dispatcher = request.getRequestDispatcher("/GestisciCampiSportivi.jsp");
					dispatcher.include(request, response);
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
			}
		}

	}

}
