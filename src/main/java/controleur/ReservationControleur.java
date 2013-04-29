/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.AchatDAO;
import dao.DAOException;
import dao.PlaceDAO;
import dao.RepresentationDAO;
import dao.ReservationDAO;
import dao.ZoneDAO;
import dao.SpectacleDAO;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Achat;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.Spectacle;

import modele.Zone;
import vue.FlashImpl;
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


        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            request.setAttribute("NoSpectacle", request.getParameter("NoSpectacle"));
                request.setAttribute("NoRepresentation", request.getParameter("NoRepresentation"));
                
            if (action.equalsIgnoreCase("Reserver")) {
                actionReserver(request, response);
            } else if (action.equalsIgnoreCase("Choisir mes places")) {

                actionChoixPlaces(request, response);
            } else if (action.equalsIgnoreCase("Valider mes places")) {
                //request.setAttribute("NoSpectacle", request.getParameter("NoSpectacle"));
                //request.setAttribute("NoRepresentation", request.getParameter("NoRepresentation"));
                actionConfirmation(request, response);
            } else if (action.equalsIgnoreCase("Reserver mes places")) {
                reserverPlaces(request, response);
            } else if (action.equalsIgnoreCase("Payer mes places")) {
                request.setAttribute("places", request.getParameter("places"));
                request.setAttribute("resAsupprimer", request.getParameter("resAsupprimer"));
                sortirCarteBleue(request, response);
            } else if (action.equalsIgnoreCase("Proceder au paiement")) {
                payerPlaces(request, response);
            } else {
                throw new DAOException("méthode non reconnue");
            }
        } catch (DAOException e) {
            //throw new RuntimeException(e);
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    /**
     * Construit l'objet Représentation à partir de ses identifiants reçus par
     * GET ou POST, dans les variables NoSpectacle et NoRepresentation.
     */
    private Representation getRepresentation(HttpServletRequest request) throws DAOException {
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        int noSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));
        int noRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));
        Representation rep = new Representation(noSpectacle, noRepresentation);
        repDAO.lire(rep);
        return rep;
    }

    private void actionReserver(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {
        request.setAttribute("titre", "Reservation de billets");
        // La représentation demandée
        Representation rep = getRepresentation(request);
        request.setAttribute("rep", rep);
        // Les autres représentations du spectacle
        SpectacleDAO specDAO = new SpectacleDAO(ds);
        request.setAttribute("representations", specDAO.getAutresRepresentationsPour(rep));
        // Les infos sur les catégories de places
        ZoneDAO zone = new ZoneDAO(ds);
        request.setAttribute("listeZones", zone.getZones());
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }

    private void actionChoixPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.setAttribute("titre", "Reservation de billets");
        // La représentation demandée
        Representation rep = getRepresentation(request);
        request.setAttribute("rep", rep);
        // Toutes les zones
        ZoneDAO zDAO = new ZoneDAO(ds);
        request.setAttribute("Zones", zDAO.getZones());
        // Toutes les places
        PlaceDAO pDAO = new PlaceDAO(ds);
        request.setAttribute("ToutesPlaces", pDAO.getMatricePlaces());
        // Les places actuellement occupées
        ReservationDAO resDAO = new ReservationDAO(ds);
        request.setAttribute("PlacesOccupees", resDAO.getPlacesReserveesPourRepresentation(rep));
        getServletContext().getRequestDispatcher("/WEB-INF/choixPlaces.jsp").forward(request, response);
    }

    private Map<String, String> parseToMap(Map<String, String[]> inputMap) {
        Map<String, String> outputMap = new LinkedHashMap<String, String>();

        for (Map.Entry<String, String[]> entry : inputMap.entrySet()) {
            if (!entry.getKey().equalsIgnoreCase("params")) {
                outputMap.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        return outputMap;
    }

    private void actionConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Object loggedIn = request.getSession().getAttribute("LoggedIn");

        if (loggedIn == null || (loggedIn != null && loggedIn.equals(false))) {
            String from = request.getParameter("from");
            Map<String, String> params = parseToMap(request.getParameterMap());

            request.setAttribute("from", from);
            request.setAttribute("params", params);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            String places = request.getParameter("places");

            Map<Zone, List<Place>> map = TraitementPlaces.TraiterPlaces(ds, places);
            float prixTotal = TraitementPlaces.getPrixTotalPlaces(map);

            request.setAttribute("NoSpectacle", request.getParameter("NoSpectacle"));
            request.setAttribute("NoRepresentation", request.getParameter("NoRepresentation"));
            request.setAttribute("map", map);
            request.setAttribute("places", places);
            request.setAttribute("prixTotal", prixTotal);
            request.setAttribute("Image", request.getParameter("Image"));
            request.setAttribute("Date", request.getParameter("Date"));
            request.setAttribute("NomSpectacle", request.getParameter("NomSpectacle"));
            request.setAttribute("titre", "Confirmation de la réservation");
            getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
        }
    }

    private void reserverPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ReservationDAO resDAO = new ReservationDAO(ds);
        String places = request.getParameter("places");
        Map<Zone, List<Place>> map = TraitementPlaces.TraiterPlaces(ds, places);
        String login = (String) request.getSession().getAttribute("Login");
        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));
        for (Map.Entry<Zone, List<Place>> entry : map.entrySet()) {
            Zone z = entry.getKey();
            for (Place p : entry.getValue()) {
                Reservation res = new Reservation(login, NoSpectacle, NoRepresentation, z.getNoZone(), p.getNoRang(), p.getNoPlace(), z.getTarifBase());
                resDAO.creer(res);
            }

        }
        FlashImpl fl = new FlashImpl("Places correctement réservées! Vous pouvez les payer depuis votre compte jusqu'à une heure avant le début de la représentation. Au dela de ce délai, vos places seront remises en vente.", request, "success");
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);


    }

    private void sortirCarteBleue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        getServletContext().getRequestDispatcher("/WEB-INF/payer.jsp").forward(request, response);
    }

    private void payerPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        AchatDAO achatDAO = new AchatDAO(ds);
        String places = request.getParameter("places");
        Map<Zone, List<Place>> map = TraitementPlaces.TraiterPlaces(ds, places);
        String login = (String) request.getSession().getAttribute("Login");
        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));
        int NoSerie = 1;
        int NoDossier = achatDAO.getProchainNumDossier(NoSpectacle, NoRepresentation);
        for (Map.Entry<Zone, List<Place>> entry : map.entrySet()) {
            Zone z = entry.getKey();
            for (Place p : entry.getValue()) {
                Achat achat = new Achat(login, NoSpectacle, NoRepresentation, z.getNoZone(), p.getNoRang(),
                        p.getNoPlace(), NoDossier, NoSerie, new Date(), z.getTarifBase());
                NoSerie++;
                achatDAO.creer(achat);

                if (Integer.parseInt(request.getParameter("resAsupprimer")) == 1) {
                    ReservationDAO resDAO = new ReservationDAO(ds);
                    Reservation resa = new Reservation(login, NoSpectacle, NoRepresentation, z.getNoZone(), p.getNoRang(), p.getNoPlace(), z.getTarifBase());
                    resDAO.supprimer(resa);
                }
            }
        }


        FlashImpl fl = new FlashImpl("Places correctement payées!", request, "success");
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);

    }
}
