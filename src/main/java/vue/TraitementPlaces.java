/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.ArrayList;
import java.util.List;
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

    public void TraiterPlaces(String places, ArrayList<Place> PlacesPoulailler,
            ArrayList<Place> PlacesOrchestre, ArrayList<Place> PlacesBalcon,
            ArrayList<Place> PlacesLoge) {
        int noP, noR, noZ;
        String[] tmp;
        String[] strArray = places.split("!");
        for (String el : strArray) {
            tmp = el.split(" ");
            noP = Integer.parseInt(tmp[0]);
            noR = Integer.parseInt(tmp[1]);
            noZ = Integer.parseInt(tmp[2]);
            if (noZ == 1) {
                PlacesPoulailler.add(new Place(noP, noR, noZ));
            } else if (noZ == 2) {
                PlacesOrchestre.add(new Place(noP, noR, noZ));
            } else if (noZ == 3) {
                PlacesBalcon.add(new Place(noP, noR, noZ));
            } else if (noZ == 4) {
                PlacesLoge.add(new Place(noP, noR, noZ));
            }
        }
        this.prixTotalPoulailler = this.getPrixPoulailler() * PlacesPoulailler.size();
        this.prixTotalOrchestre = this.getPrixOrchestre() * PlacesOrchestre.size();
        this.prixTotalBalcon = this.getPrixBalcon() * PlacesBalcon.size();
        this.prixTotalLoge = this.getPrixLoge() * PlacesLoge.size();
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
