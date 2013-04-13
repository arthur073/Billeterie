/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Date;

/**
 *
 * @author Michel
 */
public class Achat {
    
    private String login;
    private int noSpectacle;
    private int noRepresentation;
    private int noZone;
    private int noRang;
    private int noPlace;
    private int noDossier;
    private int noSerie;
    private Date dateAchat;

    public String getLogin() {
        return login;
    }

    public int getNoSpectacle() {
        return noSpectacle;
    }

    public int getNoRepresentation() {
        return noRepresentation;
    }

    public int getNoZone() {
        return noZone;
    }

    public int getNoRang() {
        return noRang;
    }

    public int getNoPlace() {
        return noPlace;
    }

    public int getNoDossier() {
        return noDossier;
    }

    public int getNoSerie() {
        return noSerie;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public Achat(String login, int noSpectacle, int noRepresentation, int noZone, int noRang, int noPlace, int noDossier, int noSerie, Date dateAchat) {
        this.login = login;
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.noZone = noZone;
        this.noRang = noRang;
        this.noPlace = noPlace;
        this.noDossier = noDossier;
        this.noSerie = noSerie;
        this.dateAchat = dateAchat;
    }


    
    
}
