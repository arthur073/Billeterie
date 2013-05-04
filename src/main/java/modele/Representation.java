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
    /* Status de la représentation : 0 réservation ouverte, 1 reservation annulée */
    private Boolean annule;

    /**
     * Spectacle associé à cette représentation.
     */
    Spectacle spectacle = null;
    
    /**
     * Constructeur minimal.
     */
    public Representation(int noSpectacle, int noRepresentation, Boolean annule) {
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.annule = annule;
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
    
    public Boolean getAnnule(){
        return annule;
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
    
    public Representation setAnnule(Boolean annule) {
        this.annule = annule;
        return this;
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
