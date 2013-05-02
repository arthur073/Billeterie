package modele;

import java.text.SimpleDateFormat;
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
    private Boolean annule;

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
    public Representation(int noSpectacle, int noRepresentation, Date date, 
            Boolean annule) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.date = date;
        this.annule = annule;
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
    /**
     * Format param only to override method
     * @param format
     * @return 
     */
    public String getDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(this.date);
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

    /**
     * @return the annule
     */
    public Boolean getAnnule() {
        return annule;
    }

    public void setAnnule(boolean annule) {
        this.annule = annule;
    }

  

}
