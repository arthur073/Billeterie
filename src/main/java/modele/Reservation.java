package modele;


/**
 * Class Reservation
 */
public class Reservation {

    private String login;
    private int noSpectacle;
    private int noRepresentation;
    private int noZone;
    private int noRang;
    private int noPlace;

    /*
     * Objets associés à la réservation.
     */
    private Client client;
    private Representation representation;
    private Place place;

    /**
     * Constructeur minimal par objets.
     */
    public Reservation(Client c, Representation r, Place p) {
        this.login = c.getLogin();
        this.noSpectacle = r.getNoSpectacle();
        this.noRepresentation = r.getNoRepresentation();
        this.noZone = p.getNoZone();
        this.noRang = p.getNoRang();
        this.noPlace = p.getNoPlace();
        this.client = c;
        this.representation = r;
        this.place = p;
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

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the noSpectacle
     */
    public int getNoSpectacle() {
        return noSpectacle;
    }

    /**
     * @param noSpectacle the noSpectacle to set
     */
    public void setNoSpectacle(int noSpectacle) {
        this.noSpectacle = noSpectacle;
    }

    /**
     * @return the noRepresentation
     */
    public int getNoRepresentation() {
        return noRepresentation;
    }

    /**
     * @param noRepresentation the noRepresentation to set
     */
    public void setNoRepresentation(int noRepresentation) {
        this.noRepresentation = noRepresentation;
    }

    /**
     * @return the noZone
     */
    public int getNoZone() {
        return noZone;
    }

    /**
     * @param noZone the noZone to set
     */
    public void setNoZone(int noZone) {
        this.noZone = noZone;
    }

    /**
     * @return the noRang
     */
    public int getNoRang() {
        return noRang;
    }

    /**
     * @param noRang the noRang to set
     */
    public void setNoRang(int noRang) {
        this.noRang = noRang;
    }

    /**
     * @return the noPlace
     */
    public int getNoPlace() {
        return noPlace;
    }

    /**
     * @param noPlace the noPlace to set
     */
    public void setNoPlace(int noPlace) {
        this.noPlace = noPlace;
    }

    /**
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * @return the representation
     */
    public Representation getRepresentation() {
        return representation;
    }

    /**
     * @param representation the representation to set
     */
    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    /**
     * @return the place
     */
    public Place getPlace() {
        return place;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(Place place) {
        this.place = place;
    }
}
