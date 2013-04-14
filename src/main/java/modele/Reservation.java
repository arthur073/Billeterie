package modele;


/**
 * Class Reservation
 */
public class Reservation {

  /**
   * @param        c
   * @param        r
   * @param        p
   */
  public Reservation(Client c, Representation r, Place p ) {
  }
  public Reservation(String login, int noSpectacle, int noRepresentation, int noZone, int noRang, int noPlace) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }


  /**
   * @return       modele.Reservation
   * @param        loginClient
   * @param        noSpectacle
   * @param        noRepresentation
   * @param        noZone
   * @param        noRang
   * @param        noPlace
   */
  public static Reservation trouverParClef(String loginClient, int noSpectacle,
        int noRepresentation, int noZone, int noRang, int noPlace) {
      return null;
  }

  


  /**
   * @return       modele.Client
   */
  public Client getClient() {
     return null;
  }


  /**
   */
  public void getPlace() {
  }


  /**
   */
  public void getRepresentation() {
  }


  /**
   * @param        c
   */
  public void setClient(Client c) {
  }


  /**
   * @param        p
   */
  public void setPlace(Place p) {
  }


  /**
   * @param        r
   */
  public void setRepresentation(Representation r) {
  }


  /**
   */
  public void liberer() {
  }


  /**
   * @return       int
   */
  public int getPrix() {
     return 24;
  }
}
