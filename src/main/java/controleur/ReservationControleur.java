/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import dao.SpectacleDAO;

import java.io.IOException;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Map;

import modele.Zone;

/**
 *
 * @author arthur
 */
@WebServlet(name = "ReservationControleur", urlPatterns = {"/ReservationControleur"})
public class ReservationControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        //    if (request.getSession(false).getAttribute("LoggedIn") == null) {
        //        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        //        return;
        //    }
        String action = request.getParameter("action");

        try {
            if (action.equalsIgnoreCase("Reserver")) {
                actionReserver(request, response);
            } else if (action.equalsIgnoreCase("Choisir mes places")) {
                actionChoixPlaces(request, response);
            } else if (action.equalsIgnoreCase("Valider mes places")) {
                actionConfirmation(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    private void actionReserver(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {

        RepresentationDAO rep = new RepresentationDAO(ds);
        SpectacleDAO spec = new SpectacleDAO(ds);
        Map<Zone, Float> listePrix =
            rep.getPrixParZones(Integer.parseInt(request.getParameter("NoSpectacle").toString()),
                    Integer.parseInt(request.getParameter("NoRepresentation").toString()));

             Iterator<Map.Entry<Zone, Float> > i = listePrix.entrySet().iterator();
        request.setAttribute("pelouse", i.next().getValue());
        request.setAttribute("orchestre", i.next().getValue());
        request.setAttribute("balcon", i.next().getValue());

        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle").toString());
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation").toString());
        request.setAttribute("representations", spec.getRepresentationsPour(NoSpectacle, NoRepresentation));
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }

    private void actionChoixPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/choixPlaces.jsp").forward(request, response);
    }

    private void actionConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
    }


}
