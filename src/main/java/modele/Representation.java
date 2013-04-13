package modele;

import java.util.Date;

/**
 * Correspond à une représentation d'un spectable.
 */
public class Representation {
   /**
    * Crée une nouvelle représentation, et l'insère dans la BDD.
    * @param        spec
    * @param        date
    */
   public Representation(Spectacle spec, Date date )
   {
   }


   /**
    * @param        noSpectacle
    * @param        noRepresentation
    */
   public static void trouverParClef(int noSpectacle, int noRepresentation)
   {
   }


   /**
    * @return       int
    */
   public int getNo()
   {
      return 0;
   }


   /**
    * @return      
    */
   public Date getDate()
   {
      return null;
   }


   /**
    * @return      Spectacle
    */
   public Spectacle getSpectacle()
   {
      return null;
   }


   /**
    * @param        d
    */
   public void setDate(Date d)
   {
   }


   /**
    * @param        spec
    */
   public void setSpectacle(Spectacle spec)
   {
   }


   /**
   */
   public void libererReservationImpayees()
   {
   }


   /**
   */
   public void annuler()
   {
   }


   /**
    * @return       int
    * @param        z
    */
   public int getPrix(Zone z)
   {
      return 0;
   }


   /**
    * @param        z
    * @param        prix
    */
   public void setPrix(Zone z, int prix)
   {
   }
}
