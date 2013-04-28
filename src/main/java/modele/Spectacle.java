package modele;

/**
 * Représente un spectacle.
 */
public class Spectacle {
    int noSpectacle;
    String nom = null;
    String image = null;

    /**
     * Constructeur minimal.
     */
    public Spectacle(int noSpectacle) {
        this.noSpectacle = noSpectacle;
    }

    /**
     * Constructeur complet.
     */
    public Spectacle(int noSpectacle, String nom, String image) {
        this.noSpectacle = noSpectacle;
        this.nom = nom;
        this.image = image;
    }

    public int getNoSpectacle() {
        return noSpectacle;
    }

    public Spectacle setNoSpectacle(int noSpectacle) {
        this.noSpectacle = noSpectacle;
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
