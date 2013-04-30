package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class Utilisateur
 */
public class Utilisateur {

    private String login;
    private String mdpChiffre = null;
    private String nom = null;
    private String prenom = null;
    private String email = null;
    private String type = null;

    /**
     * Contructeur minimal.
     */
    public Utilisateur(String login) {
        this.login = login;
    }

    /**
     * Constructeur complet.
     */
    public Utilisateur(String login, String mdpChiffre, String nom,
            String prenom, String email, String type) {
        this.login = login;
        this.mdpChiffre = mdpChiffre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.type = type;
    }


    /**
     * @return       String
     * @param        clair
     */
    public static String chiffrerMotDePasse(String clair) {
        try {
            byte[] passwdMd5 = MessageDigest.getInstance("MD5").digest(clair.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(byte b: passwdMd5)
                sb.append(String.format("%02x", b&0xff));
            return sb.toString();
        } catch (NoSuchAlgorithmException e1) {
            throw new RuntimeException("algorith not found :",e1);
        } catch (UnsupportedEncodingException e1) {
            throw new RuntimeException("encodage non supporté (UTF-8)", e1);
        }
    }

    public String getLogin() {
        return login;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasseChiffre() {
        return mdpChiffre;
    }

    public String getType() {
        return type;
    }

    public Utilisateur setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Utilisateur setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public Utilisateur setEmail(String email) {
        this.email = email;
        return this;
    }

    public Utilisateur setMotDePasseClair(String mdp) {
        this.mdpChiffre = chiffrerMotDePasse(mdp);
        return this;
    }

    public Utilisateur setMotDePasseChiffre(String mdp) {
        this.mdpChiffre = mdp;
        return this;
    }

    public Utilisateur setType(String type){
        this.type = type;
        return this;
    }
}
