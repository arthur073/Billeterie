package modele;

import javax.sql.DataSource;


/**
 * Représente l'administrateur du système.
 */
public class Administrateur extends Utilisateur {

  /*
   * FIXME il faut déterminer la bonne visibilité pour que seule la classe Utilisateur puisse le construire.
   */
  
  public Administrateur(String login, String mdpClair, String nom, String prenom, DataSource ds) {
      super(login, mdpClair, nom, prenom, ds);
  }

   /**
    * @return TypeUtilisateur.RESPONSABLE
    *
    * FIXME en fait on aurait aussi pu
    *         utiliser les instanceof pour faire la même chose.
    */
  public TypeUtilisateur getType() {
    return TypeUtilisateur.RESPONSABLE;
  }
}
