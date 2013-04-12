package modele;
import dao.AbstractDataBaseDAO;
import java.lang.String;


/**
 * Class Utilisateur
 */
abstract public class Utilisateur extends AbstractDataBaseDAO {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public Utilisateur () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //

  /**
   * @param        login
   * @param        mdpClair
   * @param        nom
   * @param        prenom
   */
  public void Utilisateur( java.lang.String login, java.lang.String mdpClair, java.lang.String nom, java.lang.String prenom )
  {
  }


  /**
   * @return       java.lang.String
   * @param        clair
   */
  public static java.lang.String chiffrerMotDePasse( java.lang.String clair )
  {
  }


  /**
   * @return       modele.Utilisateur
   * @param        login
   */
  public static modele.Utilisateur trouverParClef( java.lang.String login )
  {
  }


  /**
   */
  public void getLogin(  )
  {
  }


  /**
   * @return       java.lang.String
   */
  public java.lang.String getNom(  )
  {
  }


  /**
   */
  public void getPrenom(  )
  {
  }


  /**
   */
  public void getEmail(  )
  {
  }


  /**
   * @return       java.lang.String
   */
  public java.lang.String getMotDePasseChiffre(  )
  {
  }


  /**
   * @return       modele.TypeUtilisateur
   */
  abstract public modele.TypeUtilisateur getType(  );


  /**
   * @param        nom
   */
  public void setNom( void nom )
  {
  }


  /**
   * @param        prenom
   */
  public void setPrenom( void prenom )
  {
  }


  /**
   * @param        email
   */
  public void setEmail( java.lang.String email )
  {
  }


  /**
   * @param        mdp
   */
  public void setMotDePasseClair( java.lang.String mdp )
  {
  }


}
