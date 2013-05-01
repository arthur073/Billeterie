/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.ReservationDAO;
import dao.ResponsableDAO;
import java.io.IOException;
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
@WebServlet(name = "AdminControleur", urlPatterns = {"/AdminControleur"})
public class AdminControleur extends HttpServlet {

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
            if (action.equalsIgnoreCase("archiverBD")) {
                archiverBD(ds, request, response);
            } else if( action.equalsIgnoreCase("annulerResaNonPayees")){
                supprimerResaNonPayees(ds, request, response);
            } else if( action.equalsIgnoreCase("peuplerBD")) {
                peuplerBD(ds, request, response);
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
 * Archive et vide la BD
 * @param ds
 * @param request
 * @param response
 * @throws ServletException
 * @throws DAOException 
 */
	public void archiverBD(DataSource ds,
        HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {
            try {
                ResponsableDAO respo = new ResponsableDAO(ds);
                String resultat = respo.getBackup();
                request.setAttribute("backupFile", resultat);
                getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

            } catch (IOException ex) {
                Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException("IO exception", ex);
            }
    }
        
/**
 * Supprime les réservations non payées moins d'1h avant la représentation
 * @param ds
 * @param request
 * @param response
 * @throws ServletException
 * @throws DAOException 
 */
    public static void supprimerResaNonPayees(DataSource ds,
    HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {

    ReservationDAO resDAO = new ReservationDAO(ds);
    //on supprime les réservations qui sont périmées
    resDAO.supprimerReservationsNonPayees();
    }
    
    /**
     * Peuple la base
     * @param ds
     * @param request
     * @param response
     * @throws ServletException
     * @throws DAOException 
     */
    public static void peuplerBD(DataSource ds,
    HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {

    ReservationDAO resDAO = new ReservationDAO(ds);
    //on supprime les réservations qui sont périmées
    resDAO.supprimerReservationsNonPayees();
    }
}
