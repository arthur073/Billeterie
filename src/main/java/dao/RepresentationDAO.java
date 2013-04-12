/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Representation;

/**
 *
 * @author arthur
 */
//on extends le sqlDAO car on s'en sert partout et il extends deja lui-même le abstract
public class RepresentationDAO extends ProviderDAO {

    public RepresentationDAO(DataSource ds) {
        super(ds);
    }
    
<<<<<<< HEAD
    public ArrayList<Integer> getRepresentationPrice(int NoSpectacle, int NoRepresentation) {
=======
    public List<Integer> getRepresentationPrice(int NoSpectacle, int NoRepresentation) throws DAOException {
>>>>>>> 637cb8d0b8d13890a124719cd547590962041160
        ArrayList<Integer> prices = new ArrayList<Integer>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;

        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_PRIX_ZONE_REPRESENTATION"));
            st.setInt(1, NoSpectacle);
            st.setInt(2, NoRepresentation);
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                prices.add(rs.getInt("Prix"));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        
        return prices;
    }

    /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'un ResultSet
     */
    public List<Representation> getListeRepresentations() throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;

        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL = getRequete("SELECT_LISTE_REPRESENTATIONS_A_VENIR");

            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                Representation representation = new Representation(rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), rs.getDate("DateRepresentation"), rs.getString("Nom"), rs.getString("Image"));
                result.add(representation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }

        return result;
    }
}
