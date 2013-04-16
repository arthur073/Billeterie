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
        if (noRang == 1) {
            return 1; // Poulailler
        } else if (noRang == 2) {
            return 2; //Orchestre
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
