/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.DAOException;
import dao.PlaceDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import modele.Place;
import modele.Zone;

/**
 *
 * @author arthur
 */
public class TraitementPlaces {

    private float prixPoulailler;
    private float prixOrchestre;
    private float prixBalcon;
    private float prixLoge;
    private float prixTotalPoulailler;
    private float prixTotalOrchestre;
    private float prixTotalBalcon;
    private float prixTotalLoge;

    public void setPrixPlaces(List<Zone> listeCateg, float prixPoulailler, float prixOrchestre,
            float prixBalcon, float prixLoge) {

        for (Zone zone : listeCateg) {
            if ("Poulailler".equals(zone.getCategorie())) {
                this.prixPoulailler = zone.getTarifBase();
                prixPoulailler = zone.getTarifBase();
            } else if ("Orchestre".equals(zone.getCategorie())) {
                this.prixOrchestre = zone.getTarifBase();
                prixOrchestre = zone.getTarifBase();
            } else if ("Balcon".equals(zone.getCategorie())) {
                this.prixBalcon = zone.getTarifBase();
                prixBalcon = zone.getTarifBase();
            } else if ("Loge".equals(zone.getCategorie())) {
                this.prixLoge = zone.getTarifBase();
                prixLoge = zone.getTarifBase();
            }
        }
    }

    public static Map<Zone, List<Place>> TraiterPlaces(DataSource ds, String places) throws DAOException {
        int noP, noR, noZ;
        String[] tmp;
        String[] strArray = places.split("!");
        PlaceDAO placeDAO = new PlaceDAO(ds);
        Map<Zone, List<Place>> map = new HashMap<Zone, List<Place>>();
        for (String el : strArray) {
            tmp = el.split("/");
            noP = Integer.parseInt(tmp[0]);
            noR = Integer.parseInt(tmp[1]);
            noZ = Integer.parseInt(tmp[2]);
            Place place = new Place(noP, noR, noZ);
            placeDAO.lire(place);
            List<Place> listPlaces = map.get(place.getZone());
            if (listPlaces == null) {
                listPlaces = new ArrayList<Place>();
                listPlaces.add(place);
                map.put(place.getZone(), listPlaces);
            } else {
                listPlaces.add(place);
            }
        }
        return map;
        //  this.prixTotalPoulailler = this.getPrixPoulailler() * PlacesPoulailler.size();
        //  this.prixTotalOrchestre = this.getPrixOrchestre() * PlacesOrchestre.size();
        //  this.prixTotalBalcon = this.getPrixBalcon() * PlacesBalcon.size();
        //  this.prixTotalLoge = this.getPrixLoge() * PlacesLoge.size();
    }

    public void TraiterPlacesPourBD(String places, ArrayList<Place> PlacesBD) {
        int noP, noR, noZ;
        String[] tmp;
        String[] strArray = places.split("!");
        for (String el : strArray) {
            tmp = el.split("/");
            noP = Integer.parseInt(tmp[0]);
            noR = Integer.parseInt(tmp[1]);
            noZ = Integer.parseInt(tmp[2]);
            PlacesBD.add(new Place(noP, noR, noZ));
        }
    }
    
    public static float getPrixTotalPlaces(Map<Zone, List<Place>> map){
        float total = 0;
        for (Map.Entry<Zone, List<Place>> entry : map.entrySet()) {
            Zone z = entry.getKey();
            for (Place p : entry.getValue()){
                total += z.getTarifBase();
            }
            
        }
        return total;
    }

    /**
     * @return the prixPoulailler
     */
    public float getPrixPoulailler() {
        return prixPoulailler;
    }

    /**
     * @return the prixOrchestre
     */
    public float getPrixOrchestre() {
        return prixOrchestre;
    }

    /**
     * @return the prixBalcon
     */
    public float getPrixBalcon() {
        return prixBalcon;
    }

    /**
     * @return the prixLoge
     */
    public float getPrixLoge() {
        return prixLoge;
    }

    /**
     * @return the prixTotalPoulailler
     */
    public float getPrixTotalPoulailler() {
        return prixTotalPoulailler;
    }

    /**
     * @return the prixTotalOrchestre
     */
    public float getPrixTotalOrchestre() {
        return prixTotalOrchestre;
    }

    /**
     * @return the prixTotalBalcon
     */
    public float getPrixTotalBalcon() {
        return prixTotalBalcon;
    }

    /**
     * @return the prixTotalLoge
     */
    public float getPrixTotalLoge() {
        return prixTotalLoge;
    }
}
