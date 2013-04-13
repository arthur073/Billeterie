package modele;

import java.util.Set;

/**
 * Représente une place de la salle de spectacle.
 */
public class Place {

  /**
   * Renvoie une place dont on connait la clé.
   * @param        noRang
   * @param        noPlace
   */
  public static void trouverParClef(int noRang, int noPlace) {
  }


  /**
   * Renvoie l'ensemble des places existantes.
   * @return Set<Place>
   */
   public static Set<Place> getPlaces() {
      return null;
  }


  /**
   * @return       int
   */
   public int getNoPlace() {
      return 0;
  }

  /**
   * @return       int
   */
   public int getNoRang() {
      return 0;
  }


  /**
   * Renvoie le client qui a réservé cette place pour la réprésentation donnée.
   * @return       Le client, ou null si la place est libre.
   * @param        r
   */
  public Client getReservateurPour(Representation r) {
      return null;
  }


  /**
   * @return      Zone
   */
   public Zone getZone() {
      return null;
  }


  /**
   * Le constructeur est privé parce que les places disponibles sont fixées une
   * fois pour toutes.
   * @see getPlaces
   */
  private Place() {
  }

}
