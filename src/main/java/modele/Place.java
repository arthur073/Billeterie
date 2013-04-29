package modele;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Représente une place de la salle de spectacle.
 */
public class Place {

    int noPlace;
    int noRang;
    int noZone;

    /**
     * Zone associée à cette place.
     */
    Zone zone = null;

    /**
     * Constructeur minimal.
     */
    public Place(int noPlace, int noRang, int noZone) {
        this.noPlace = noPlace;
        this.noZone = noZone;
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
    public Place setNoPlace(int noPlace) {
        this.noPlace = noPlace;
        return this;
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
    public Place setNoRang(int noRang) {
        this.noRang = noRang;
        return this;
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
    public Place setNoZone(int noZone) {
        this.noZone = noZone;
        return this;
    }

    /**
     * @return the zone
     */
    public Zone getZone() {
        return zone;
    }

    /**
     * @param zone the zone to set
     */
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    @Override
    public String toString(){
        return "Place " + this.noPlace + " Rang " + this.noRang;    
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Place)) return false;
        Place otherPlace = (Place) other;
        return otherPlace.noPlace == this.noPlace
                && otherPlace.noRang == this.noRang;
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 41).
            append(noPlace).append(noRang).toHashCode();
    }
}
