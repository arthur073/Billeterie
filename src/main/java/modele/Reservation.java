/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Michel
 */
public class Reservation {
    
    private String login;
    private int noSpectacle;
    private int noRepresentation;
    private int noZone;
    private int noRang;
    private int noPlace;

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

    public Reservation(String login, int noSpectacle, int noRepresentation, int noZone, int noRang, int noPlace) {
        this.login = login;
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.noZone = noZone;
        this.noRang = noRang;
        this.noPlace = noPlace;
    }
    
    
}
