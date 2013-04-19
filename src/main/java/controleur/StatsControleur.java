/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.SpectacleDAO;
import dao.ZoneDAO;
import java.io.IOException;
import java.io.PrintWriter;
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


/**
 * @author Michel
 */
@WebServlet(name = "StatsControleur", urlPatterns = {"/StatsControleur"})
public class StatsControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("rafraichir")) {
            rafraichirStats(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
    
    
   /* private void actionAcheter(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {
        ZoneDAO zone = new ZoneDAO(ds);
        List<Zone> listeZones = zone.getZones();
        // TODO : prendre les prix de la requête de thib
        request.setAttribute("p1", listeZones.remove(0));
        request.setAttribute("p2", listeZones.remove(0));
        request.setAttribute("p3", listeZones.remove(0));
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }*/

    private void rafraichirStats(HttpServletRequest request, HttpServletResponse response) throws ServletException {
            SpectacleDAO spec = new SpectacleDAO(ds);
        try {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            //TODO Jany : mettre les vraies fonctions pour tout !

             String xmlString = 
                     "<root>"
                     + "<benefTotal>300</benefTotal>"
                     + "<listeSpectaclesPlacesVendues>"
                        + "<spectacle>"
                            + "<nom>Urban Peace 3</nom>"
                            + "<placesVendues>245</placesVendues>"
                        + "</spectacle>"
                            + "<nom>Patrick Sebastien</nom>"
                            + "<donnee>132</donnee>"
                        + "</spectacle>"              
                     + "</listeSpectaclesPlacesVendues>"
                     + "<listeSpectaclesLesPlusRentables>"
                        + "<spectacle>"
                            + "<nom>Urban Peace 3</nom>"
                            + "<donnee>50.032€</donnee>"
                        + "</spectacle>"
                        + "<spectacle>"
                            + "<nom>Patrick Sebastien</nom>"
                            + "<donnee>24.031€</donnee>"
                        + "</spectacle>"
                     + "</listeSpectaclesLesPlusRentables>"
                     + "<listeSpectaclesTauxRemplissage>"
                        + "<spectacle>"
                            + "<nom>Urban Peace 3</nom>"
                            + "<donnee>89,04%</donnee>"
                        + "</spectacle>"
                        + "<spectacle>"
                            + "<nom>Patrick Sebastien</nom>"
                            + "<donnee>62,3%</donnee>"
                        + "</spectacle>"
                     + "</listeSpectaclesTauxRemplissage>"
                     + "</root>";
            out.print(xmlString);


            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(StatsControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

