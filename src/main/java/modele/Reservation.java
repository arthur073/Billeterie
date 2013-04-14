package modele;


/**
 * Class Reservation
 */
public class Reservation {

    String login;
    int noSpectacle;
    int noRepresentation;
    int noZone;
    int noRang;
    int noPlace;


    /**
     * Constructeur minimal par objets.
     */
    public Reservation(Client c, Representation r, Place p) {
        this.login = c.getLogin();
        this.noSpectacle = r.getNoSpectacle();
        this.noRepresentation = r.NoRepresentation();
        this.noZone = p.getNoZone();
        this.noRang = p.getNoRang();
        this.noPlace = p.getNoPlace();
    }
    
    /**
     * Constructeur minimal.
     */
    public Reservation(String login, int noSpectacle, int noRepresentation,
            int noZone, int noRang, int noPlace) {
        this.login = login;
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.noZone = noZone;
        this.noRang = noRang;
        this.noPlace = noPlace;
    }

}
