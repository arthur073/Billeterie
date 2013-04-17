package modele;

import java.util.Date;
import java.util.List;

/**
 * Correspond à une représentation d'un spectable.
 */
public class Representation {

    int noSpectacle;
    int noRepresentation;
    Date date = null;
    private List<Zone> listeZones;

    /**
     * Spectacle associé à cette représentation.
     */
    Spectacle spectacle = null;
    
    /**
     * Constructeur minimal.
     */
    public Representation(int noSpectacle, int noRepresentation) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
    }

    /**
     * Constructeur complet.
     */
    public Representation(int noSpectacle, int noRepresentation, Date date) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.date = date;
    }

    public int getNoSpectacle() {
        return noSpectacle;
    }

    public int getNoRepresentation() {
        return noRepresentation;
    }

    public Date getDate() {
        return date;
    }

    public Spectacle getSpectacle() {
        return spectacle;
    }

    public Representation setNoSpectacle(int noSpectacle) {
        this.noSpectacle = noSpectacle;
        return this;
    }

    public Representation setNoRepresentation(int noRepresentation) {
        this.noRepresentation = noRepresentation;
        return this;
    }
 
    public Representation setDate(Date date) {
        this.date = date;
        return this;
    }

    public Representation setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
        return this;
    }

    /**
     * @return the listeZones
     */
    public List<Zone> getListeZones() {
        return listeZones;
    }

    /**
     * @param listeZones the listeZones to set
     */
    public void setListeZones(List<Zone> listeZones) {
        this.listeZones = listeZones;
    }

}
