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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                getServletContext().getRequestDispatcher("/WEB-INF/stats.jsp").forward(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } catch (DAOException ex) {
            throw new RuntimeException(ex);
            // request.setAttribute("erreurMessage", ex.getMessage());
            // getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

	/**
	 * Ajoute à l'objet request donné les attributs nécessaires au rendu de
	 * la template stats.jsp.
	 */
	public static void remplirRequeteDeStats(DataSource ds,
        HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {
        try {
            DateFormat fmt = new SimpleDateFormat("yy-mm-dd");
            Date debut, fin;
            String sDebut = (String) request.getParameter("dateDebut");
            String sFin = (String) request.getParameter("dateFin");
            if (sDebut == null) {
                sDebut = "2013-01-01";
            }
            debut = fmt.parse(sDebut);
            if (sFin == null) {
                sFin = "2014-01-01";
            }
            fin = fmt.parse(sFin);
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
