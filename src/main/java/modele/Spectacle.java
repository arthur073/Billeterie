package modele;

import java.lang.String;
import java.util.Set;

/**
 * Class Spectacle
 */
public class Spectacle {
  /**
   * @param        nom
   */
   public Spectacle(String nom)
  {
  }


  /**
   * @return       modele.Spectacle
   * @param        no
   */
  public static modele.Spectacle trouverParClef( int no )
 {
      return null;
  }


  /**
   * Renvoie la liste de tous les spectacles programmés.
   * 
   *
   * TODO : ajouter des possibilités de filtrage par période
   * 
   * @return       Set<Spectacle>
   */
  public static Set<Spectacle> getSpectacles()
 {
      return null;
  }


  /**
   * @return       int
   */
  public int getNo(  )
 {
      return 0;
  }


  /**
   * Renvoie les représentations de ce spectacle.
   * @return       Set<Representation>
   */
  public Set<Representation> getRepresentations(  )
 {
      return null;
  }


  /**
   * @return       java.lang.String
   */
  public String getNom(  )
 {
      return null;
  }


  /**
   * @param        nom
   */
  public void setNom(String nom )
  {
  }


}
