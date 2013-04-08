package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "PagesControleur", urlPatterns = {"/PagesControleur"})
public class PagesControleur extends HttpServlet {

    // TODO: Changer quand on aura une bd correcte ! 
    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");

        try {
            if (action == null) {
                actionAfficher(request, response);
            } else if (action.equals("goToLogin")) {
                goToLogIn(request, response);
            } else if (action.equalsIgnoreCase("valider")) {
                logMeIn(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request,
        HttpServletResponse response) throws DAOException, ServletException, IOException {
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getListeRepresentations());
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }

    private void goToLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    private void logMeIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("annuler")) {
            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
            return;
        }
        
        if (action.equalsIgnoreCase("valider")) {
            request.getSession(true).setAttribute("LoggedIn", true);
            actionAfficher(request, response);
            return;
        }
    }
    
}
