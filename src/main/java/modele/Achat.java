package modele;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Représente l'achat d'une place, effectué par un client pour une
 * représentation.
 */
public class Achat extends Reservation {

    private Integer noDossier = null;
    private Integer noSerie = null;
    private Date dateAchat = null;

    
    /**
     * Constructeur minimal.
     */
    public Achat(String login, int noSpectacle, int noRepresentation, int noZone, int noRang, int noPlace, float tarifBase) {
        super(login, noSpectacle, noRepresentation, noZone, noRang, noPlace, tarifBase);
    }

    /**
     * Constructeur complet.
     */
    public Achat(String login, int noSpectacle, int noRepresentation, int noZone, int noRang, int noPlace, 
            int noDossier, int noSerie, Date dateAchat, float tarifBase) {
        super(login, noSpectacle, noRepresentation, noZone, noRang, noPlace, tarifBase);
        this.noDossier = noDossier;
        this.noSerie = noSerie;
        this.dateAchat = dateAchat;
    }


    /**
     * Constructeur minimal par objet.
     */
    public Achat(Reservation r) {
        super(r.getLogin(), r.getNoSpectacle(), r.getNoRepresentation(), r.getNoZone(), r.getNoRang(), r.getNoPlace(), r.getTarifBase());
        setClient(r.getClient());
        setRepresentation(r.getRepresentation());
        setPlace(r.getPlace());
    }

    /**
     * Constructeur complet par objet.
     */
    public Achat(Reservation r, int noDossier, int noSerie, Date dateAchat) {
        super(r.getLogin(), r.getNoSpectacle(), r.getNoRepresentation(), r.getNoZone(), r.getNoRang(), r.getNoPlace(), r.getTarifBase());
        setClient(r.getClient());
        setRepresentation(r.getRepresentation());
        setPlace(r.getPlace());
        this.noDossier = noDossier;
        this.noSerie = noSerie;
        this.dateAchat = dateAchat;
    }

    /**
     * Constructeur minimal par objets.
     */
    public Achat(Client c, Representation r, Place p, float tarifBase) {
        super(c, r, p, tarifBase);
    }

    /**
     * Constructeur complet par objets.
     */
    public Achat(Client c, Representation r, Place p, int noDossier, int noSerie, Date dateAchat, float tarifBase) {
        super(c, r, p, tarifBase);
        this.noDossier = noDossier;
        this.noSerie = noSerie;
        this.dateAchat = dateAchat;
    }

    /**
     * @return the noDossier
     */
    public Integer getNoDossier() {
        return noDossier;
    }

    /**
     * @param noDossier the noDossier to set
     */
    public void setNoDossier(Integer noDossier) {
        this.noDossier = noDossier;
    }

    /**
     * @return the noSerie
     */
    public Integer getNoSerie() {
        return noSerie;
    }

    /**
     * @param noSerie the noSerie to set
     */
    public void setNoSerie(Integer noSerie) {
        this.noSerie = noSerie;
    }

    /**
     * @return the dateAchat
     */
    public Date getDateAchat() {
        return dateAchat;
    }
     /**
     * Format param only to override method
     * @param format
     * @return 
     */
    public String getDateAchat(String format) {
        if( format == null )
        {
            format = "dd/MM/yy hh:mi:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat();
        return sdf.format(this.dateAchat);
    }

    /**
     * @param dateAchat the dateAchat to set
     */
    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

}
