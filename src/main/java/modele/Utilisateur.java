/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Michel
 */
public class Utilisateur {
    
    private String login;
    private String nom;
    private String prenom;
    private String mail;
    private String password;
    private String type;

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public Utilisateur(String login, String nom, String prenom, String mail, String password, String type) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.type = type;
    }
   
    
    
    public void annulerReservation( Reservation r) {
        
    }
    
    // ......
}
