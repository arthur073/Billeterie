/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.AchatDAO;
import dao.ClientDAO;
import dao.DAOException;
import dao.PlaceDAO;
import dao.RepresentationDAO;
import dao.ReservationDAO;
import dao.ZoneDAO;
import dao.SpectacleDAO;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Achat;
import modele.Client;
import modele.Place;
import modele.Representation;
import modele.Reservation;

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
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
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
                sortirCarteBleue(request, response);
            } else if (action.equalsIgnoreCase("Proceder au paiement")) {
                payerPlaces(request, response);
            } else if (action.equalsIgnoreCase("Annuler")) {
                annulerRepresentation(request,response);
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
        //int annule = Integer.parseInt(request.getParameter("Annule"));
        Representation rep = new Representation(noSpectacle, noRepresentation,0);
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
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        int nbPlacesRestantes = repDAO.getNbPlacesRestantes(rep.getNoSpectacle(), rep.getNoRepresentation());
        
        // Toutes les zones
        ZoneDAO zDAO = new ZoneDAO(ds);
        request.setAttribute("Zones", zDAO.getZones());
        // Toutes les places
        PlaceDAO pDAO = new PlaceDAO(ds);
        request.setAttribute("ToutesPlaces", pDAO.getMatricePlaces());
        // Les places actuellement occupées
        ReservationDAO resDAO = new ReservationDAO(ds);
        request.setAttribute("PlacesOccupees", resDAO.getPlacesReserveesPourRepresentation(rep));
        if (nbPlacesRestantes == 0) {
            FlashImpl fl = new FlashImpl("Plus de places disponibles pour cette représentation", request, "error");
            actionReserver(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/choixPlaces.jsp").forward(request, response);
        }
       
    }
    
    private void actionConfirmation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        Object loggedIn = request.getSession().getAttribute("LoggedIn");
        // Pour la suite (paiement, réservation, login si besoin...), toutes les
        // infos concernant la transaction en cours seront dans la session.
        // De plus on la copie pour régler un bug
        Panier panier = new Panier(request.getSession(), ds);
        panier.vider();
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        Representation rep = new Representation(
                Integer.parseInt(request.getParameter("NoSpectacle")),
                Integer.parseInt(request.getParameter("NoRepresentation")),0);
        repDAO.lire(rep);
        panier.setRepresentation(rep);
        panier.ajouterPlaces(request.getParameter("places"));
        
        if (loggedIn == null || loggedIn.equals(false)) {
            request.setAttribute("redirectionVers", "confirmation");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            request.setAttribute("map", panier.getPlacesParZone());
            request.setAttribute("prixTotal", panier.getPrixTotal());
            request.setAttribute("rep", rep);
            request.setAttribute("titre", "Confirmation de la réservation");
            int NbPlacesRestantes = repDAO.getNbPlacesRestantes(
                    rep.getNoSpectacle(), rep.getNoRepresentation());
            int NbPlacesMap = panier.getPlaces().size();
            if (NbPlacesRestantes <= 0) {
                FlashImpl fl = new FlashImpl("Plus de places disponibles à la réservation en ligne", request, "error");
                actionChoixPlaces(request, response);
            }
            if (NbPlacesRestantes - NbPlacesMap < 0) {
                FlashImpl fl = new FlashImpl("Il reste seulement " + NbPlacesRestantes + " places disponibles à la réservation en ligne", request, "error");
                actionChoixPlaces(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);
            }
        }
    }
    
    private void reserverPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ReservationDAO resDAO = new ReservationDAO(ds);
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        Panier panier = new Panier(request.getSession(), ds);
        String login = (String) request.getSession().getAttribute("Login");
        ClientDAO cDAO = new ClientDAO(ds);
        Client client = new Client(login);
        cDAO.lire(client);
        for (Place place : panier.getPlaces()) {
            Reservation res = new Reservation(client, panier.getRepresentation(), place);
            resDAO.creer(res);
        }
        panier.vider();
        FlashImpl fl = new FlashImpl("Places correctement réservées! Vous pouvez les payer depuis votre compte jusqu'à une heure avant le début de la représentation. Au dela de ce délai, vos places seront remises en vente.", request, "success");
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }
    
    private void sortirCarteBleue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        // Pas besoin de passer d'autres infos, tout est dans le panier.
        request.setAttribute("panier", new Panier(request.getSession(), ds));
        getServletContext().getRequestDispatcher("/WEB-INF/payer.jsp").forward(request, response);
    }
    
    private void payerPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        AchatDAO achatDAO = new AchatDAO(ds);
        Panier panier = new Panier(request.getSession(), ds);
        Map<Zone, List<Place>> map = panier.getPlacesParZone();
        String login = (String) request.getSession().getAttribute("Login");
        if (!map.isEmpty() && panier.getRepresentation() != null && login != null) {
            int NoSpectacle = panier.getRepresentation().getNoSpectacle();
            int NoRepresentation = panier.getRepresentation().getNoRepresentation();
            int NoSerie = 1;
            int NoDossier = achatDAO.getProchainNumDossier(NoSpectacle, NoRepresentation);
            for (Map.Entry<Zone, List<Place>> entry : map.entrySet()) {
                Zone z = entry.getKey();
                for (Place p : entry.getValue()) {
                    Achat achat = new Achat(login, NoSpectacle, NoRepresentation, z.getNoZone(), p.getNoRang(),
                            p.getNoPlace(), NoDossier, NoSerie, new Date());
                    NoSerie++;
                    achatDAO.creer(achat);
                    // Si on vient de la liste des réservations
                    if (!request.getParameter("resAsupprimer").isEmpty()) {
                        ReservationDAO resDAO = new ReservationDAO(ds);
                        Reservation resa = new Reservation(login, NoSpectacle,
                                NoRepresentation, z.getNoZone(), p.getNoRang(),
                                p.getNoPlace());
                        resDAO.supprimer(resa);
                    }
                }
            }
            panier.vider();
            FlashImpl fl = new FlashImpl("Places correctement payées!", request, "success");
        } else {
            FlashImpl fl = new FlashImpl("veuillez choisir vos places.", request, "error");
        }
        // Après l'achat, redirection vers l'index
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
        
    }
    
    private void annulerRepresentation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        // Pas besoin de passer d'autres infos, tout est dans le panier.
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        int noSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));
        int noRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));
        repDAO.annuler(noSpectacle, noRepresentation);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        FlashImpl fl = new FlashImpl("La representation a bien été annulée", request, "success");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }
}
