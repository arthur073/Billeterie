package modele;

import java.util.Set;
import javax.sql.DataSource;


/**
 * Représente un client du système.
 */
public class Client extends Utilisateur {
    
    
    
  public Client(String login, String mdpClair, String nom, String prenom, DataSource ds) {
      super(login, mdpClair, nom, prenom, ds);
  }

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
      return null;
  }


  /**
   * @return Ensemble des réservations effectuées par ce client.
   */
  public Set<Reservation> getReservations() {
      return null;
  }

  

}
