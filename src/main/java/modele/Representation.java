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
    
    private int noSpectacle;
    private int noRepresentation;
    private Date date;
    
    public int getNoSpectacle() {
        return noSpectacle;
    }
    
    public int getNoRepresentation() {
        return noRepresentation;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Representation(int NoS, int NoR, Date date) {
        this.noSpectacle = NoS;
        this.noRepresentation = NoR;
        this.date = date;
    }
    
    public void annulerRepresentation( Representation r) {
        // TODO
    }
    
    
    
    // to be continued
    
}
