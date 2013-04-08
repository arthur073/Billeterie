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
    private String nomSpectacle;
    
    public int getNoSpectacle() {
        return noSpectacle;
    }
    
    public String getNomSpectacle() {
        return nomSpectacle;
    }
    
    public int getNoRepresentation() {
        return noRepresentation;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Representation(int NoS, int NoR, Date date, String nomSpectacle) {
        this.noSpectacle = NoS;
        this.noRepresentation = NoR;
        this.date = date;
        this.nomSpectacle = nomSpectacle;
    }
    
    public void annulerRepresentation( Representation r) {
        // TODO
    }
    
    
    
    // to be continued
    
}
