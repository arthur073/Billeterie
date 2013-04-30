/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ProviderDAO.closeStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Achat;
import modele.Utilisateur;

/**
 *
 * @author Michel, Jany
 */
public class UtilisateurDAO extends ProviderDAO implements DAOMetier<Utilisateur> {

    public UtilisateurDAO(DataSource ds) {
        super(ds);
    }        

    /**
     * Renvoie un objet Utilisateur si les identifiants sont OK, null sinon.
     *
     * Précondition : le password n'a pas deja été haché.
     */
    public Utilisateur connexion(String login, String password) throws DAOException {
        Utilisateur u = new Utilisateur(login);
        try { 
            lire(u);
        } catch (DAOException e) {
            return null;
        }
        if (u.getMotDePasseChiffre().equals(Utilisateur.chiffrerMotDePasse(password))) {
            return u;
        } else {
            return null;
        }
    }

    /**
     * Insère un utilisateur dans la BDD, en utilisant tous les champs fournis.
     *
     * Précondition : le login n'est pas utilisé.
     */
    @Override
    public void creer(Utilisateur u) throws DAOException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("INSERT_CLIENT"));
            st.setString(1, u.getLogin());
            st.setString(2, u.getNom());
            st.setString(3, u.getPrenom());
            st.setString(4, u.getEmail());
            st.setString(5, u.getMotDePasseChiffre());
            st.executeUpdate();
            // Le client a bien été inséré, le login était donc dispo.
        } catch (SQLException e) {
            // TODO discriminer entre déjà existant et erreur BD.
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    /**
     * Lit le champ 'login' et remplit les autres.
     * @throws DAOException
     */
    @Override
    public void lire(Utilisateur u) throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            // FIXME : le getRequete() refusait de marcher
            st = conn.prepareStatement("SELECT Prenom, Nom, Mail, MotDePasse, Type FROM Utilisateur WHERE Login = ?");
            st.setString(1, u.getLogin());
            rs = st.executeQuery();
            if (rs.next()) {
                u.setMotDePasseChiffre(rs.getString("MotDePasse"));
                u.setNom(rs.getString("Nom"));
                u.setPrenom(rs.getString("Prenom"));
                u.setEmail(rs.getString("Mail"));
                // FIXME : erreur java.lang.IllegalArgumentException: No enum constant modele.TypeUtilisateur.Client
                u.setType(rs.getString("Type"));
            } else {
                throw new DAOException(DAOException.Type.NON_TROUVE, "Le login fourni n'existe pas.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
    }

    @Override
    public void mettreAJour(Utilisateur obj) throws DAOException{
       throw new DAOException("Impossible de mettre à jour un utilisateur!");

    }

    @Override
    public void supprimer(Utilisateur obj) throws DAOException {
        throw new DAOException("Impossible de suppriemer un utilisateur!");
    }
    
    public int checkUtilisateurInexistant(String username) throws DAOException{
        int result = 0;
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("CHECK_LOGIN_INEXISTANT"));
            st.setString(1, username);
            rs = st.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return result;
    }
}
