package modele;


/**
 * Class TypeUtilisateur
 */
public class TypeUtilisateur {

  //
  // Fields
  //

  public modele.TypeUtilisateur RESPONSABLE;
  public modele.TypeUtilisateur CLIENT;
  
  //
  // Constructors
  //
  public TypeUtilisateur () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of RESPONSABLE
   * @param newVar the new value of RESPONSABLE
   */
  public void setRESPONSABLE ( modele.TypeUtilisateur newVar ) {
    RESPONSABLE = newVar;
  }

  /**
   * Get the value of RESPONSABLE
   * @return the value of RESPONSABLE
   */
  public modele.TypeUtilisateur getRESPONSABLE ( ) {
    return RESPONSABLE;
  }

  /**
   * Set the value of CLIENT
   * @param newVar the new value of CLIENT
   */
  public void setCLIENT ( modele.TypeUtilisateur newVar ) {
    CLIENT = newVar;
  }

  /**
   * Get the value of CLIENT
   * @return the value of CLIENT
   */
  public modele.TypeUtilisateur getCLIENT ( ) {
    return CLIENT;
  }

  //
  // Other methods
  //

}
