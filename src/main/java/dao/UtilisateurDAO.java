/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
     * pre-conditions : le password n'a pas deja été haché
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
            st.setString(2, getHexDigest(password));
            rs = st.executeQuery();
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
    
    	public static String getHexDigest(String password) {
		StringBuffer hexPass = new StringBuffer();
		try {
			byte[] passwdMd5 = MessageDigest.getInstance("MD5").digest(password.getBytes("UTF-8"));
			for (int i = 0 ; i < passwdMd5.length ; i++) {
				hexPass.append(Integer.toHexString(0xFF & passwdMd5[i]));
			}
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return new String(hexPass);
	}
         /**
     * 
     * pre-condition : le login n'est pas utilisé
     */
        public void ClientCreation(String login, String password, String nom, String prenom, String mail) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            
            //PreparedStatement st = conn.prepareStatement(getRequete("SELECT_EXISTENCE_CLIENT"));
            //st.setString(1, login);
            //rs = st.executeQuery();
            //if( rs.next() ){
            //    return false;
            //} else {

            PreparedStatement st = conn.prepareStatement(getRequete("INSERT_CLIENT"));
            String client = "Client";
            st.setString(1, login);
            st.setString(2, nom);
            st.setString(3, prenom);
            st.setString(4, mail);
            st.setString(5, getHexDigest(password));
            st.setString(6, client);
            st.executeUpdate();
            
            //}
            //les identifiants sont ok
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
}
