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
    private String date;
    private String nomSpectacle;
    private String image;
    
    public int getNoSpectacle() {
        return noSpectacle;
    }
    
    public String getNomSpectacle() {
        return nomSpectacle;
    }
    
    public int getNoRepresentation() {
        return noRepresentation;
    }
    
    public String getDate() {
        return date;
    }
    
    public String getImage() {
        return image;
    }
    
    public Representation(int NoS, int NoR, String date, String nomSpectacle, String image) {
        this.noSpectacle = NoS;
        this.noRepresentation = NoR;
        this.date = date;
        this.nomSpectacle = nomSpectacle;
        this.image = image;
    }
    
    public void annulerRepresentation( Representation r) {
        // TODO
    }
    
    
    
    // to be continued
    
}
