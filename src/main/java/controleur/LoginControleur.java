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
import modele.TypeUtilisateur;

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
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        if (((HttpServletRequest) request).getMethod().equals("GET")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

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
            } else if (action.equalsIgnoreCase("Annuler")) {
                RepresentationDAO repDAO = new RepresentationDAO(ds);
                request.setAttribute("representations", repDAO.getRepresentationsAVenir());
                getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void logMeIn(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException, DAOException {

        UtilisateurDAO utilDAO = new UtilisateurDAO(ds);
        Utilisateur utilisateur = utilDAO.connexion(login, password);

        if (utilisateur != null) {
            request.getSession().setAttribute("LoggedIn", true);
            request.getSession().setAttribute("Login", utilisateur.getLogin());
            request.getSession().setAttribute("FailedLogIn", false);
            if (utilisateur.getType().equals(TypeUtilisateur.RESPONSABLE)) {
                request.getSession().setAttribute("Admin", true);
            } else {
                FlashImpl fl = new FlashImpl("Vous êtes loggué en tant que " + utilisateur.getLogin(), request, "success");
            }
            actionAfficher(request, response);
        } else {
            request.getSession().setAttribute("FailedLogIn", true);
            FlashImpl fl = new FlashImpl("Mauvais identifiants", request, "error");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response)
            throws DAOException, ServletException, IOException {

        String from = request.getParameter("redirectionVers");
        if (from.equalsIgnoreCase("")) {
            from = "indexAll";
        }

        // actions spécifiques selon page
        if (from.equalsIgnoreCase("confirmation")) {
            Object admin = request.getSession().getAttribute("Admin");
            //on test si on vient de se logguer en admin
            if (admin != null && admin.equals(true)) {
                FlashImpl fl = new FlashImpl("L'administrateur du système ne peut pas réserver de place", request, "error");
                RepresentationDAO repDAO = new RepresentationDAO(ds);
                request.setAttribute("representations", repDAO.getRepresentationsAVenir());
                request.setAttribute("titre", "Mes billets en ligne");
                getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
                return;
            } else {
                Panier panier = new Panier(request.getSession(), ds);
                request.setAttribute("map", panier.getPlacesParZone());
                request.setAttribute("prixTotal", panier.getPrixTotal());
                request.setAttribute("rep", panier.getRepresentation());
                request.setAttribute("titre", "Confirmation de réservation");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
        } else if (from.equalsIgnoreCase("indexAll")) {
            RepresentationDAO repDAO = new RepresentationDAO(ds);
            request.setAttribute("representations", repDAO.getRepresentationsAVenir());
            request.setAttribute("titre", "Mes billets en ligne");
            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
        }
    }
}
