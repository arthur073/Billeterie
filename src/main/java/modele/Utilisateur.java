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
    private TypeUtilisateur type = null;

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
            String prenom, String email, TypeUtilisateur type) {
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
        StringBuffer hexPass = new StringBuffer();
        try {
            byte[] passwdMd5 = MessageDigest.getInstance("MD5").digest(clair.getBytes("UTF-8"));
            for (int i = 0; i < passwdMd5.length; i++) {
                hexPass.append(Integer.toHexString(0xFF & passwdMd5[i]));
            }
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return new String(hexPass);
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

    public TypeUtilisateur getType() {
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

    public Utilisateur setType(TypeUtilisateur type){
        this.type = type;
        return this;
    }
}
