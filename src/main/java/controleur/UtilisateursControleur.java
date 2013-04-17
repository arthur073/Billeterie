/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.AchatDAO;
import dao.ClientDAO;
import dao.DAOException;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Achat;

import modele.Client;
import modele.Reservation;
import modele.Utilisateur;
import vue.FlashImpl;

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
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("goToMyAccount")) {
            try {
                goToMyAccount(request, response);
            } catch (DAOException ex) {
                Logger.getLogger(UtilisateursControleur.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (action.equalsIgnoreCase("goToAdmin")) {
                goToAdmin(request, response);
        }
        else
        {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

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

    private void goToMyAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Object loggedIn = request.getSession().getAttribute("LoggedIn");

        if (loggedIn == null || (loggedIn != null && loggedIn.equals(false))) {
            FlashImpl fl = new FlashImpl("Veuillez vous identifier pour accéder à ce service", request, "error");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        // On recherche les attributs de l'utilisateur
        String login = (String) request.getSession().getAttribute("Login");
        Utilisateur u = new Utilisateur(login);
        UtilisateurDAO uDAO = new UtilisateurDAO(ds);
        uDAO.lire(u);
        
        // on regarde ses places achetées
        ReservationDAO repDAO = new ReservationDAO(ds);
        AchatDAO achDAO = new AchatDAO(ds);
        
        //List<Reservation> listRep = repDAO.getListeReservationsClient(login);
        List<Achat> listAchatPrec = achDAO.getListeAchatsClientAvecHistorique(login);
        List<Achat> listAchatSuiv = achDAO.getListeAchatsClientSansHistorique(login);
        request.setAttribute("login", login);
        request.setAttribute("nom", u.getNom());
        request.setAttribute("prenom", u.getPrenom());
        request.setAttribute("email", u.getEmail());
        //request.setAttribute("listRep", listRep);
        request.setAttribute("listAchatPrec", listAchatPrec);
        request.setAttribute("listAchatSuiv", listAchatSuiv);
        
        request.setAttribute("titre", "Mon compte");
        getServletContext().getRequestDispatcher("/WEB-INF/monCompte.jsp").forward(request, response);
    }
    private void goToAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titre", "Admin");
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }
}
