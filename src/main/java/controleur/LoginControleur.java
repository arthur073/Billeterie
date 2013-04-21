/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import dao.UtilisateurDAO;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Place;
import modele.TypeUtilisateur;

import modele.Utilisateur;
import modele.Zone;
import vue.FlashImpl;
import vue.TraitementPlaces;

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
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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

        if (utilisateur != null) {
            request.getSession().setAttribute("LoggedIn", true);
            request.getSession().setAttribute("Login", utilisateur.getLogin());
            request.getSession().setAttribute("FailedLogIn", false);
            if (utilisateur.getType().equals(TypeUtilisateur.RESPONSABLE)) {
                request.getSession().setAttribute("Admin", true);
            }
            FlashImpl fl = new FlashImpl("Vous êtes loggué en tant que " + utilisateur.getLogin(), request, "success");
            actionAfficher(request, response);
        } else {
            request.getSession().setAttribute("FailedLogIn", true);
            FlashImpl fl = new FlashImpl("Mauvais identifiants", request, "error");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
    private Map<String, String> splitMap(String params) {
        Map<String, String> mapTemp = new LinkedHashMap<String, String>();
        for (String pair : params.split(", ")) {
            String[] kv = pair.split("=");
            mapTemp.put(kv[0].replace("{", ""), kv[1]);
        }
        return mapTemp;
    }

    private void actionAfficher(HttpServletRequest request, HttpServletResponse response)
            throws DAOException, ServletException, IOException {

        String from = request.getParameter("from");
        String params = request.getParameter("params");

        // on set les attributes de la request 
        for (Map.Entry<String, String> entry : splitMap(params).entrySet()) {
            request.setAttribute(entry.getKey().toString(), entry.getValue().toString());
        }


        // actions spécifiques selon page
        if (from.equalsIgnoreCase("confirmation")) {
            String places = splitMap(params).get("places");
            Map<Zone, List<Place>> map = TraitementPlaces.TraiterPlaces(ds, places);
            float prixTotal = TraitementPlaces.getPrixTotalPlaces(map);
            request.setAttribute("map", map);
            request.setAttribute("prixTotal", prixTotal);
        }


        // on redirige vers la bonne page
        getServletContext().getRequestDispatcher("/WEB-INF/" + from + ".jsp").forward(request, response);
    }
}
