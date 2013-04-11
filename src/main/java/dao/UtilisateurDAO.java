/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Representation;

/**
 *
 * @author Michel
 */
public class UtilisateurDAO extends ProviderDAO {
    
    
     public UtilisateurDAO(DataSource ds) {
        super(ds);
    }
     
         /**
     * Renvoie un boolean a true si les identifiants sont OK
     * pre-conditions : le password a deja été haché et les identifiants ont été échapés
     */
    public boolean ClientIdentification(String login, String password) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_CONNEXION_CLIENT"));
            st.setString(1, login);
            st.setString(2, password);
            rs = st.executeQuery();
            //rs = st.executeQuery(requeteSQL);
            //les identifiants sont ok
            if( rs.next() )
                return true;
            else
                return false;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
         /**
     * 
     * pre-condition : le login n'est pas utilisé
     */
        public boolean ClientCreation(String login, String password, String nom, String prenom, String mail) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_EXISTENCE_CLIENT"));
            st.setString(1, login);
            rs = st.executeQuery(requeteSQL);
            if( ! rs.next() )
                return false;

            st = conn.prepareStatement(getRequete("INSERT_CLIENT"));
            st.setString(1, login);
            st.setString(2, nom);
            st.setString(3, prenom);
            st.setString(4, mail);
            st.setString(5, password);
            st.executeQuery(requeteSQL);
            return true;
            //les identifiants sont ok
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
}
