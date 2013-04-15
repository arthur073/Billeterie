package modele;

import java.util.Set;

/**
 * Représente un client du système.
 *
 * TODO : je ne sais pas si cette classe sert à quelquechose.
 */
public class Client extends Utilisateur {
    
  public Client(String login, String mdpClair, String nom, String prenom, String email) {
      super(login, mdpClair, nom, prenom, email, TypeUtilisateur.CLIENT);
  }

}
