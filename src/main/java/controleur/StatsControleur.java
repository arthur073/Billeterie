/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.SpectacleDAO;
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
@WebServlet(name = "StatsControleur", urlPatterns = {"/StatsControleur"})
public class StatsControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response); 
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null || action.equalsIgnoreCase("rafraichir")) {
            rafraichirStats(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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

    private void rafraichirStats(HttpServletRequest request, HttpServletResponse response) {
        SpectacleDAO spec = new SpectacleDAO(ds);
        //request.set
    }
}
