package modele;

<<<<<<< Updated upstream
import java.sql.Date;
=======
import java.util.Date;
>>>>>>> Stashed changes

/**
 * Représente l'achat d'une place, effectué par un client pour une
 * représentation.
 */
public class Achat {
<<<<<<< Updated upstream
  
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
=======

    private String login;
    private int noSpectacle;
    private int noRepresentation;
    private int noZone;
    private int noRang;
    private int noPlace;
    private int noDossier;
    private int noSerie;
    private Date dateAchat;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public int getNoSpectacle() {
        return noSpectacle;
    }

    public void setNoSpectacle(int noSpectacle){
        this.noSpectacle = noSpectacle;
    }

    public int getNoRepresentation() {
        return noRepresentation;
    }

    public void setNoRepresentation(int noRepresentation){
        thiation;
    }

    public int getNoZone() {
        return noZone;
    }

    public void setNoZone(int noZone){
        this.noZone = noZone;
    }

    public int getNoRang() {
        return noRang;
    }

    public void setNoRang(int noRang){
        this.noRang = noRang;
    }

    public int getNoPlace() {
        return noPlace;
    }

    public void setNoPlace(int noPlace){
        thiPlace;
    }

    public int getNoDossier() {
        return noDossier;
    }

    public void setNoDossier(int noDossier){
        thissier;
    }

    public int getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(int noSerie){
        this.noSerie = noSerie;
    }
    
    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat){
        this. at;
    }

    public Achat(String login, int noSpectacle, int noRepresentation,
            int noZone, int noRang, int noPlace, int noDossier, int noSerie,
            Date dateAchat) {
        this.login = login;
        this.noSpectacle = noSpectacle;
        this.noRepresentation = noRepresentation;
        this.noZone = noZone;
        this.noRang = noRang;
        this.noPlace = noPlace;
        this.noDossier = noDossier;
        this.noSerie = noSerie;
        this.dateAchat = dateAchat;
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
    public Achat(Reservation r, int noDossier, int noSerie, Date dateAchat) {
        Achat(
>>>>>>> Stashed changes
    }
}
