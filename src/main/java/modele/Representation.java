package modele;

import java.util.Date;

/**
 * Correspond à une représentation d'un spectable.
 */
public class Representation {

    int noSpectacle;
    int no;
    Date date = null;

    /**
     * Spectacle associé à cette représentation.
     */
    Spectacle spectacle = null;
    
    /**
     * Constructeur minimal.
     */
    public Representation(int noSpectacle, int no) {
        this.noSpectacle = noSpectacle;
        this.no = no;
    }

    /**
     * Constructeur complet.
     */
    public Representation(int noSpectacle, int no, Date date) {
        this.noSpectacle = noSpectacle;
        this.no = no;
        this.date = date;
    }

    public int getNoSpectacle() {
        return noSpectacle;
    }

    public int getNo() {
        return no;
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

    public Representation setNo(int no) {
        this.no = no;
        return this;
    }
 
    public Representation setDate(Date date) {
        this.date = date;
        return this;
    }

    /**
     * FIXME : j'aurais préféré que ce soit dans RepresentationDAO vu que pour
     * bien faire il faudrait que si spectacle == null, on aille le chercher
     * dans la BDD.
     */
    public Representation setSpectacle(Spectacle spectacle) {
        this.spectacle = spectacle;
        return this;
    }

}
