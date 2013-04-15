/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 *
 * @author arthur
 */
public class RangToZone {

    public int rangToZone(int noRang){
        if (noRang <= 3) {
            return 1; // Poulailler
        } else if (3 < noRang && noRang <= 6) {
            return 2; //Orchestre
        } 
        return 3;  //Balcon
    }
   
}
