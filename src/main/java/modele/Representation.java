/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Date;

/**
 *
 * @author arthur
 */
public class Representation {
    
    private int NoSpectacle;
    private int NoRepresentation;
    private Date date;
    
    public Representation(int NoS, int NoR, Date date) {
        this.NoSpectacle = NoS;
        this.NoRepresentation = NoR;
        this.date = date;
    }
    
    public void annulerRepresentation( Representation r) {
        
    }
    
    // to be continued
    
}
