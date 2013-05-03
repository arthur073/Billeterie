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
import vue.FlashImpl;

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
            //   try {
                   ResponsableDAO respo = new ResponsableDAO(ds);
                   String resultat = respo.getBackup();
                   respo.viderBase();
                   request.setAttribute("backupFile", resultat);
                   FlashImpl fl = new FlashImpl("Base correctement archivée.", request, "success");
                   getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

            /*   } catch (IOException ex) {
                   Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
                   throw new RuntimeException("IO exception", ex);
               }*/
        } catch (IOException ex) {
            Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
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
    public void supprimerResaNonPayees(DataSource ds,
    HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {
        try {
            ReservationDAO resDAO = new ReservationDAO(ds);
            //on supprime les réservations qui sont périmées
            resDAO.supprimerReservationsNonPayees();
            FlashImpl fl = new FlashImpl("Réservations non payées correctement annulées.", request, "success");
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Peuple la base
     * @param ds
     * @param request
     * @param response
     * @throws ServletException
     * @throws DAOException 
     */
    public void peuplerBD(DataSource ds,
    HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException {
        try {
            ResponsableDAO respo = new ResponsableDAO(ds);
            respo.peuplerBase();
            FlashImpl fl = new FlashImpl("Base correctement peuplée.", request, "success");
            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        } catch (IOException ex) {
            Logger.getLogger(AdminControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
