package controleur;

import dao.ClientDAO;
import dao.DAOException;
import dao.PlaceDAO;
import dao.RepresentationDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import modele.Client;
import modele.Place;
import modele.Representation;
import modele.Zone;

/**
 * Facilite le passage d'un groupe de places entre les étapes d'une réservation
 * ou d'un achat.
 *
 * Un panier est lié à une session. Si on construit deux paniers avec la même
 * session, alors ils ont le même contenu.
 *
 * @author jany, thibault
 */
public class Panier {
    private DataSource ds;
    private HttpSession session;

    /**
     * Crée un nouveau panier ou bien récupère le contenu du panier existant
     * pour cette session.
     */
    public Panier(HttpSession session, DataSource ds) {
        this.ds = ds;
        this.session = session;
    }

    /**
     * Vide le contenu du panier.
     */
    public void vider() {
        session.removeAttribute("mapPanier");
        session.removeAttribute("repPanier");
    }

    /**
     * Ajoute des places au panier.
     *
     * @param places Liste des places au format nP/nR/nZ!nP/nR/nZ...
     */
    public void ajouterPlaces(String places) throws DAOException {
        int noP, noR, noZ;
        String[] tmp;
        String[] strArray = places.split("!");
        PlaceDAO placeDAO = new PlaceDAO(ds);
        for (String el : strArray) {
            tmp = el.split("/");
            noP = Integer.parseInt(tmp[0]);
            noR = Integer.parseInt(tmp[1]);
            noZ = Integer.parseInt(tmp[2]);
            Place place = new Place(noP, noR, noZ);
            placeDAO.lire(place);
            ajouterPlace(place);
        }
    }

    /**
     * Ajoute une place au panier.
     *
     * @param p Place à ajouter.
     */
    public void ajouterPlace(Place place) {
        Map<Zone, List<Place>> map = getPlacesParZone();
        List<Place> listPlaces = map.get(place.getZone());
        if (listPlaces == null) {
            listPlaces = new LinkedList<Place>();
            listPlaces.add(place);
            map.put(place.getZone(), listPlaces);
        } else {
            listPlaces.add(place);
        }
    }

    /**
     * Renvoie la liste des places du panier.
     */
    public List<Place> getPlaces() {
        Map<Zone, List<Place>> map = getPlacesParZone();
        LinkedList<Place> result = new LinkedList<Place>();
        for (Map.Entry<Zone, List<Place>> entree : map.entrySet()) {
            result.addAll(entree.getValue());
        }
        return result;
    }

    /**
     * Renvoie la liste des places du panier groupées par zones.
     *
     * Renvoie la liste des places trouvée dans la session, ou bien en crée une
     * si besoin.
     */
    public Map<Zone, List<Place>> getPlacesParZone() {
        Map<Zone, List<Place>> map = (Map<Zone, List<Place>>) session.getAttribute("mapPanier");
        if (map == null) {
            map = new HashMap<Zone, List<Place>>();
            session.setAttribute("mapPanier", map);
        }
        return map;
    }

    /**
     * Renvoie le prix total des places du panier.
     */
    public float getPrixTotal() {
        float total = 0;
        for (Map.Entry<Zone, List<Place>> entry : getPlacesParZone().entrySet()) {
            Zone z = entry.getKey();
            for (Place p : entry.getValue()) {
                total += z.getTarifBase();
            }
        }
        return total;
    }

    /**
     * Changer la représentation associée aux places de ce panier.
     */
    public void setRepresentation(Representation rep) {
        session.setAttribute("repPanier", rep);
    }
    
    /**
     * Changer la représentation associée aux places de ce panier.
     */
    public Representation getRepresentation() {
        return (Representation) session.getAttribute("repPanier");
    }
}
