package modele;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Représente une zone de la salle de spectacle.
 */
public class Zone {
    private int noZone;
    private String categorie = null;
    private Float tarifBase = null;

    /**
     * Constructeur minimal.
     */
    public Zone(int noZone) {
        this.noZone = noZone;
    }

    /**
     * Constructeur complet.
     */
    public Zone(int noZone, String categorie, float tarifBase) {
        this.noZone = noZone;
        this.categorie = categorie;
        this.tarifBase = tarifBase;
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
     * @return the categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * @param categorie the categorie to set
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * @return the tarifBase
     */
    public Float getTarifBase() {
        return tarifBase;
    }

    /**
     * @param tarifBase the tarifBase to set
     */
    public void setTarifBase(Float tarifBase) {
        this.tarifBase = tarifBase;
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Zone)) return false;
        Zone otherZone = (Zone)other;
        return otherZone.noZone == this.noZone;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).
            append(noZone).toHashCode();
    }
}
