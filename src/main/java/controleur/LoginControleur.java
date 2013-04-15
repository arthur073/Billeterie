/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import dao.UtilisateurDAO;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import modele.Utilisateur;
import vue.FlashImpl;

/**
 *
 * @author thib
 */
@WebServlet(name = "LoginControleur", urlPatterns = {"/LoginControleur"})
public class LoginControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("Valider")) {
                String login = request.getParameter("username");
                String password = request.getParameter("passwd");
                logMeIn(request, response, login, password);
            }
        } catch (DAOException e) {
            /*
             * Pour avoir une page d'infos bien détaillée.
             * TODO retirer ça pour le rendu.
             */
            throw new RuntimeException(e);
            // request.setAttribute("erreurMessage", e.getMessage());
            // getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void logMeIn(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException, DAOException {

        UtilisateurDAO utilDAO = new UtilisateurDAO(ds);
        Utilisateur utilisateur = utilDAO.connexion(login, password);
        // TODO à clarifier

        if (utilisateur != null) {
            request.getSession(true).setAttribute("LoggedIn", true);
            request.getSession(true).setAttribute("FailedLogIn", false);
            actionAfficher(request, response);
        } else {
            request.getSession(true).setAttribute("FailedLogIn", true);
            FlashImpl fl = new FlashImpl("Mauvais identifiants", request);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request,
            HttpServletResponse response) throws DAOException, ServletException, IOException {
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }
}
