package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.InserisciCampoBean;
import model.Campo;

/**
 * Servlet implementation class InserisciCampoSportivoServlet
 */
@WebServlet("/InserisciCampoSportivo")
public class InserisciCampoSportivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserisciCampoSportivoServlet() {
        super();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(IndexServlet.getUser().get("ID"));

		
		InserisciCampoBean inseriscoCampoBean = new InserisciCampoBean();
		
		String nomeString=request.getParameter("NomeCampo");
		String indirizzoString= request.getParameter("Indirizzo");
		String cittaString=request.getParameter("citta");
		LocalDate dataDate = LocalDate.parse(request.getParameter("mydatetime"));
		String oraString= request.getParameter("ora");
		String sportString =request.getParameter("Selezionasport");
		String prezzoString=request.getParameter("prezzo");
		String modPString = request.getParameter("Modpagamento");
		String descString=request.getParameter("descrizione");
		String cbTorneoString= Boolean.toString(Boolean.valueOf(request.getParameter("cbTorneo")));
		
		for (String ora: oraString.split(" ")) {
    		Campo campo = new Campo(nomeString,cittaString,indirizzoString);
    		campo.setSport(sportString);
    		campo.setDesc(descString);
    		campo.setId(id);
    		campo.setData(dataDate.toString());
    		campo.setOra(oraString);
    		campo.setPrezzo(prezzoString);
    		campo.setModPagamento(modPString);
    		campo.setRenter(id+"");
            try {
				if(inseriscoCampoBean.inserisciCampo(campo, cbTorneoString)){
	                response.sendRedirect("HomePageRenter.jsp");
				}
			} catch (SQLException e) {
				//nothing
							}
		
	}

}
}
