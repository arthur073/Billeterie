/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.util.Iterator;
import java.util.LinkedList;
import modele.Reservation;

/**
 *
 * @author arthur
 */
public class RangToZone {

    public int rangToZone(int noRang){
        if (noRang <= 12) {
            return 1; // Poulailler
        } else if (12 < noRang && noRang <= 21 ) {
            return 2; //Orchestre
        } else if (21 < noRang && noRang <= 27 ) {
            return 4; // Loge
        }
        return 3;  //Balcon
    }
    
    public String etatSiege(LinkedList<Reservation> PlacesOccupees, int noSpectacle, int noRepresentation, int noPlace, int noRang, int noZone){
        Reservation res;
        Iterator<Reservation> it = PlacesOccupees.iterator();
        while (it.hasNext()){
            res = it.next();
            int noP = res.getNoPlace();
            int noR = res.getNoRang();
            int noZ = res.getNoZone();
            if (noPlace == noP && noRang == noR && noZone == noZ) {
                return "occupe";
            }
        }
        
        if (noZone == 1) {
            return "poulailler";
        } else if (noZone == 2) {
            return "orchestre";
        } else if (noZone == 3) {
            return "balcon";
        } else if (noZone == 4) {
            return "loge";
        }
        return "";
    }
   
}
