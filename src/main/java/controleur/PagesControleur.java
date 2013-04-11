package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import dao.UtilisateurDAO;
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
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        

        try {
            if (action == null || action.equalsIgnoreCase("annuler")) {
                actionAfficher(request, response);
            } else if (action.equalsIgnoreCase("goToLogin")) {
                goToLogIn(request, response);
            } else if (action.equalsIgnoreCase("valider")) {
                String login = request.getParameter("username");
                String password = request.getParameter("passwd");
                logMeIn(request, response,login,password);
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

    private void logMeIn(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException, DAOException {
        String action = request.getParameter("action");
    
        // Thib : je pense que ce bloc sert a rien car on vient du doGet et on
        // a déjà fait le test de l'action. De meme le if suivant sert a rien je
        // pense. Si quelqu'un valide il peut faire le ménage ;-)
//        if (action.equalsIgnoreCase("annuler")) {
//            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
//            return;
//        }
        
        if (action.equalsIgnoreCase("valider")) {
            UtilisateurDAO utilDAO = new UtilisateurDAO(ds);
            Boolean logged =  utilDAO.ClientIdentification(login, password);
            System.out.println(logged);
            if (logged) {
                request.getSession(true).setAttribute("LoggedIn", true);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
            actionAfficher(request, response);
            return;
        }
    }
    
    private void validerReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("annuler")) {
            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
            return;
        }
        
        if (action.equalsIgnoreCase("valider")) {
            getServletContext().getRequestDispatcher("/WEB-INF/reserver2.jsp").forward(request, response);
            return;
        }
    }
    
    
}
