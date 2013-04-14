package modele;

import java.sql.Date;

/**
 * Représente l'achat d'une place, effectué par un client pour une
 * représentation.
 */
public class Achat {
  
  /**
   * Crée un nouvel achat à partir de ses attributs clés.
   * @param        c Le client à l'origine de l'achat.
   * @param        r La représentation pour laquelle 
   * @param        p La place achetée.
   */
 
  public Achat(String login, int NoSpectacle, int noRepresentation, int noZone, int noRang, int noPlace, 
          int noDossier, int noSerie, Date date) {
  }


  /**
   * Transforme une réservation déjà effectuée en Achat.
   *
   * N'efface pas la réservation de la base de données. Le numéro de dossier et
   * le numéro de série sont générés automatiquement.
   *
   * FIXME : garder le même numéro de dossier pour plusieurs achats différents.
   *
   * @param        r Le réservation à finaliser.
   */
  public Achat(Reservation r) {
  }


  /**
   * Permet de chercher un achat dont on connait la clé.
   *
   * @return       L'achat cherché, ou null s'il n'existe pas.
   * @param        loginClient
   * @param        noSpectacle
   * @param        noRepresentation
   * @param        noZone
   * @param        noRang
   * @param        noPlace
   */
  public static Achat trouverParClef(String loginClient, int noSpectacle,
      int noRepresentation, int noZone, int noRang, int noPlace) {
     return null;
  }


  /**
   * Renvoie le numéro de dossier associé à cet achat.
   * @return Numéro de dossier.
   */
   public int getNoDossier() {
      return 0;
  }


  /**
   * Renvoie le numéro de série associé à cet achat.
   * @return Numéro de série.
   */
   public int getNoSerie() {
      return 0;
  }

    public String getLogin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNoSpectacle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNoRepresentation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNoZone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNoRang() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNoPlace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
