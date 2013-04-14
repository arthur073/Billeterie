package modele;

/**
 * Représente une zone de la salle de spectacle.
 */
public class Zone {
    private int no;
    private String categorie = null;
    private Float tarifBase = null;

    /**
     * Constructeur minimal.
     */
    public Zone(int no) {
        this.no = no;
    }

    /**
     * Constructeur complet.
     */
    public Zone(int no, String categorie, float tarifBase) {
        this.no = no;
        this.categorie = categorie;
        this.tarifBase = tarifBase;
    }

    public float getTarifBase() {
        return tarifBase;
    }

    public int getNo() {
        return no;
    }

    public String getCategorie() {
        return categorie;
    }
}
