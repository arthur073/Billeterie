package modele;


/**
 * Représente un client du système.
 */
public class Client extends Utilisateur {

  /**
   * FIXME c'est ça ou les instanceof, vous préférez quoi ?
   * @return       TypeUtilisateur
   */
  public TypeUtilisateur getType() {
    return TypeUtilisateur.CLIENT;
  }


  /**
   * Renvoie l'ensemble des places réservées par ce client pour une
   * représentation donnée.
   * @return       L'ensemble des places réservées.
   * @param        r Représentation pour laquelle les places sont réservées.
   */
  public Set<Place> getPlacesReserveesPour(Representation r) {
  }


  /**
   * @return Ensemble des réservations effectuées par ce client.
   */
  public Set<Reservation> getReservations() {
  }


}
