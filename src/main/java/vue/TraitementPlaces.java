/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.ArrayList;
import modele.Place;

/**
 *
 * @author arthur
 */
public class TraitementPlaces {

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
            }
        }

    }
}
