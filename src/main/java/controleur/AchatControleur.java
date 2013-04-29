/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.ZoneDAO;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import modele.Zone;

/**
 * @author Michel
 */
@WebServlet(name = "AchatControleur", urlPatterns = {"/AchatControleur"})
public class AchatControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getSession(false).getAttribute("LoggedIn") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");

        try {
            // TODO : if à mettre
            actionAcheter(request, response);
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            //getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void actionAcheter(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {
        ZoneDAO zone = new ZoneDAO(ds);
        List<Zone> listeZones = zone.getZones();
        // TODO : prendre les prix de la requête de thib
        request.setAttribute("p1", listeZones.remove(0));
        request.setAttribute("p2", listeZones.remove(0));
        request.setAttribute("p3", listeZones.remove(0));
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }
}
