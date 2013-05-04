/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.StatsDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author Michel
 */
@WebServlet(name = "StatsControleur", urlPatterns = {"/StatsControleur"})
public class StatsControleur extends HttpServlet {

	@Resource(name = "jdbc/billeterie")
	private DataSource ds;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("rafraichir")) {
                remplirRequeteDeStats(ds, request, response);
                getServletContext().getRequestDispatcher("/WEB-INF/statsWidget.jsp").forward(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (DAOException ex) {
            throw new RuntimeException(ex);
            // request.setAttribute("erreurMessage", ex.getMessage());
            // getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

	/**
	 * Ajoute à l'objet request donné les attributs nécessaires au rendu de
	 * la template statsWidget.jsp.
	 */
	public static void remplirRequeteDeStats(DataSource ds,
        HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {
        try {
            String dateFormat = "dd-MM-yyyy";
            DateFormat fmt = new SimpleDateFormat(dateFormat);
            Date debut, fin;
            String sDebut = (String) request.getParameter("dateDebut");
            String sFin = (String) request.getParameter("dateFin");
            Calendar c = Calendar.getInstance();
            int mois = c.get(Calendar.MONTH);
            int anneeDebut = 0;;
            if (sDebut == null) {
                //on a dépassé le 1er janvier de la saison        
                if( mois < 6 ) {
                    c.set(c.YEAR, c.get(Calendar.YEAR)-1);
                } else {
                    c.set(c.YEAR, c.get(Calendar.YEAR));
                }
                anneeDebut = c.get(Calendar.YEAR);
                sDebut = "01-09-" + anneeDebut;
            }
            debut = fmt.parse(sDebut);
            request.setAttribute("dateDebutLue", fmt.format(debut));
            if (sFin == null) {
                sFin = "01-07-"+(anneeDebut+1);
            }
            fin = fmt.parse(sFin);
            request.setAttribute("dateFinLue", fmt.format(fin));
            StatsDAO sDAO = new StatsDAO(ds);
            request.setAttribute("benefTotal", sDAO.getBenefTotalPeriode(debut, fin));
            request.setAttribute("totalPlacesVendues", sDAO.getNbAchatsPeriode(debut, fin));
            request.setAttribute("mieuxRemplis", sDAO.getStatsSpectaclesLesPlusRemplis(5, debut, fin));
            request.setAttribute("plusRentables", sDAO.getStatsSpectaclesLesPlusRentables(5, debut, fin));
            request.setAttribute("statsSpectacles", sDAO.getStatsTousSpectacles(debut, fin));
        } catch (ParseException ex) {
            request.setAttribute("erreurFormatageDate", "<p class=\"erreur\">"
                    + "Erreur : les dates données sont mal formattées."
                    + "</p>");
        }
    }
}
