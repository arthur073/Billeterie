/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.UtilisateurDAO;
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
 *
 * @author arthur
 */
@WebServlet(name = "UtilisateursControleur", urlPatterns = {"/UtilisateursControleur"})
public class UtilisateursControleur extends HttpServlet {
    
    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("Creer")) {
                FormulaireCreerUnCompte(request, response);
            } else if (action.equalsIgnoreCase("Annuler")) {
                // Retour à la page précédente
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

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
