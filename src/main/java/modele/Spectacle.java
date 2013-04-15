package modele;

/**
 * Représente un spectacle.
 */
public class Spectacle {
    int no;
    String nom = null;
    String image = null;

    /**
     * Constructeur minimal.
     */
    public Spectacle(int no) {
        this.no = no;
    }

    /**
     * Constructeur complet.
     */
    public Spectacle(int no, String nom, String image) {
        this.no = no;
        this.nom = nom;
        this.image = image;
    }

    public int getNo() {
        return no;
    }

    public Spectacle setNo(int no) {
        this.no = no;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Spectacle setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Spectacle setImage(String image) {
        this.image = image;
        return this;
    }
}
