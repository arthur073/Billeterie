package modele;


/**
 * Représente l'administrateur du système.
 */
public class Administrateur extends Utilisateur {

  /*
   * FIXME il faut déterminer la bonne visibilité pour que seule la classe Utilisateur puisse le construire.
   */
  Administrateur() {
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
