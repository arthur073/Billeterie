/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.ReservationDAO;
import dao.ZoneDAO;
import dao.SpectacleDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Place;
import modele.Reservation;

import modele.Zone;
import vue.TraitementPlaces;

/**
 *
 * @author arthur
 */
@WebServlet(name = "ReservationControleur", urlPatterns = {"/ReservationControleur"})
public class ReservationControleur extends HttpServlet {

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

        //    if (request.getSession(false).getAttribute("LoggedIn") == null) {
        //        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        //        return;
        //    }
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if (action.equalsIgnoreCase("Reserver")) {
                actionReserver(request, response);
            } else if (action.equalsIgnoreCase("Choisir mes places")) {
                /* TODO : a corriger !!! */
                request.setAttribute("NoSpectacle", request.getParameter("NoSpectacle"));
                request.setAttribute("NoReservation", request.getParameter("NoRepresentation"));
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
        ZoneDAO zone = new ZoneDAO(ds);
        SpectacleDAO spec = new SpectacleDAO(ds);
        List<Zone> listeZones = zone.getZones();

        request.setAttribute("listeZones", listeZones);
        request.setAttribute("titre", "Reservation de billets");

        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle").toString());
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation").toString());
        request.setAttribute("NoSpectacle", NoSpectacle);
        request.setAttribute("NoRepresentation", NoRepresentation);
        request.setAttribute("representations", spec.getRepresentationsPour(NoSpectacle, NoRepresentation));
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }

    private void actionChoixPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.setAttribute("titre", "Reservation de billets");
        ReservationDAO resDAO = new ReservationDAO(ds);
        /* TODO : à corriger !! */
        //int NoSpectacle = 1;
        //int NoRepresentation = 1;
        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));
        LinkedList<Reservation> PlacesOccupees = resDAO.getListeReservationsPourRepresentation(NoSpectacle, NoRepresentation);

        request.setAttribute("PlacesOccupees", PlacesOccupees);
        getServletContext().getRequestDispatcher("/WEB-INF/choixPlaces.jsp").forward(request, response);
    }

    private void actionConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object loggedIn = request.getSession().getAttribute("LoggedIn");
        if (loggedIn == null || (loggedIn != null && loggedIn.equals(false))) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            String places = request.getParameter("places");
            ArrayList<Place> PlacesBD = new ArrayList<Place>();
            TraitementPlaces tp = new TraitementPlaces();
            tp.TraiterPlacesPourBD(places, PlacesBD);
            /* TODO thib : a finir */

            getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
        }
    }
}
