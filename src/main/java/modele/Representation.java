package modele;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Correspond à une représentation d'un spectable.
 */
public class Representation {

    int noSpectacle;
    int noRepresentation;
    Date date = null;
    private List<Zone> listeZones;
    /* Status de la représentation : 0 réservation ouverte, 1 reservation annulée */
    int status;

    /**
     * Spectacle associé à cette représentation.
     */
    Spectacle spectacle = null;
    
    /**
     * Constructeur minimal.
     */
    public Representation(int noSpectacle, int noRepresentation, int status) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.status = status;
    }

    /**
     * Constructeur complet.
     */
    public Representation(int noSpectacle, int noRepresentation, Date date, int status) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.date = date;
        this.status = status;
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
    
    public int getStatus(){
        return status;
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
    
    public boolean isDateLessThanAnHour() {
        try {
            //get and format now date
            Calendar now=Calendar.getInstance();
            now.add(Calendar.HOUR, +1);

            Date dateFormatted;
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
            Date nowFormatted = df.parse(df.format(now.getTime()));
            
            return nowFormatted.after(getDate());
        } catch (ParseException ex) {
            Logger.getLogger(Representation.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Parsing error", ex);
        }
    }
}
