package modele;

/**
 * Représente un client du système.
 */
public class Client extends Utilisateur {

    public Client(String login) {
        super(login);
    }

    public Client(String login, String mdpClair, String nom, String prenom, String email) {
        super(login, mdpClair, nom, prenom, email, TypeUtilisateur.CLIENT);
    }
}
