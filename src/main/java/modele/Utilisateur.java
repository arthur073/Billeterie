package modele;
import dao.UtilisateurDAO;
import javax.sql.DataSource;


/**
 * Class Utilisateur
 */
abstract public class Utilisateur extends UtilisateurDAO {

  /**
   * @param        login
   * @param        mdpClair
   * @param        nom
   * @param        prenom
   */
  public Utilisateur(String login, String mdpClair, String nom, String prenom, DataSource ds) {
      super(ds);
  }


  /**
   * @return       String
   * @param        clair
   */
   public static String chiffrerMotDePasse(String clair) {
      return clair;
  }


  /**
   * @return       modele.Utilisateur
   * @param        login
   */
   public static Utilisateur trouverParClef(String login) {
      return null;
  }


  /**
   */
  public void getLogin() {
  }


  /**
   * @return       java.lang.String
   */
   public String getNom() {
      return null;
  }


  /**
   */
  public void getPrenom() {
  }


  /**
   */
  public void getEmail() {
  }


  /**
   * @return       String
   */
   public String getMotDePasseChiffre() {
      return null;
  }


  /**
   * @return       modele.TypeUtilisateur
   */
  abstract public TypeUtilisateur getType();


  /**
   * @param        nom
   */
  public void setNom(String nom) {
  }


  /**
   * @param        prenom
   */
  public void setPrenom(String prenom) {
  }


  /**
   * @param        email
   */
  public void setEmail(String email) {
  }


  /**
   * @param        mdp
   */
  public void setMotDePasseClair(String mdp) {
  }

}
