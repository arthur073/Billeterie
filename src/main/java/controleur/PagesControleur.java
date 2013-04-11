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
            } else if (action.equalsIgnoreCase("goToLogOut")) {
                goToLogOut(request, response);
            } else if (action.equalsIgnoreCase("Creer un compte")) {
                CreerUnCompte(request, response);
            } else if (action.equalsIgnoreCase("FormCreateUser")) {
                FormulaireCreerUnCompte(request, response);
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
    
    private void goToLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.getSession().setAttribute("LoggedIn", false);
        actionAfficher(request,response);
    }

    private void logMeIn(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException, DAOException {
        String action = request.getParameter("action");
    
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
    
    private void CreerUnCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
            getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(request, response); 
    }
    
    private void FormulaireCreerUnCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        UtilisateurDAO userDAO = new UtilisateurDAO(ds);
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        String login = (String) request.getParameter("username");
        String passwd = (String) request.getParameter("passwd");
        String email = (String) request.getParameter("email");
        userDAO.ClientCreation(login, passwd, nom, prenom, email);
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response); 
    }
    
}
