/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.ClientDAO;
import dao.DAOException;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import modele.Client;

/**
 *
 * @author arthur
 */
@WebServlet(name = "UtilisateursControleur", urlPatterns = {"/UtilisateursControleur"})
public class UtilisateursControleur extends HttpServlet {
    
    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doPost(HttpServletRequest request,
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
        ClientDAO clientDAO = new ClientDAO(ds);
        Client client = new Client(
        (String) request.getParameter("username"),
        Client.chiffrerMotDePasse((String) request.getParameter("passwd")),
        (String) request.getParameter("nom"),
        (String) request.getParameter("prenom"),
                (String) request.getParameter("email"));

        clientDAO.creer(client);
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }
}
