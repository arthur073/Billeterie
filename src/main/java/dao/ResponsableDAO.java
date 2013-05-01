/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author arthur
 */
public class ResponsableDAO extends ProviderDAO {
    
    
     public ResponsableDAO(DataSource ds) {
        super(ds);
    }
     
     /**
     * Sauvegarde la base et donne un fichier à l'utilisateur
     * 
     */
    public void viderBase() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String listeRequetes[] = {"DELETE_ALL_TABLE_ACHETE", "DELETE_ALL_TABLE_RESERVE",
            "DELETE_ALL_TABLE_REPRESENTATION", "DELETE_ALL_TABLE_SPECTACLE"};

            for(int i = 0; i<listeRequetes.length; i++)
            {
                st.executeUpdate(getRequete(listeRequetes[i]));
            }
        }   catch (SQLException ex) {
            Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erreur BD", ex);
        }
    }
    
         /**
     * Sauvegarde la base et donne un fichier à l'utilisateur
     * 
     */
    public String getBackup() throws DAOException {
        ResultSet rs = null;
        String result = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String listeRequetes[] = {"SELECT_ALL_TABLE_ACHETE", "SELECT_ALL_TABLE_RESERVE",
            "SELECT_ALL_TABLE_PLACE", "SELECT_ALL_TABLE_REPRESENTATION", "SELECT_ALL_TABLE_SPECTACLE",
            "SELECT_ALL_TABLE_UTILISATEUR", "SELECT_ALL_TABLE_ZONE" };

            for(int i = 0; i<listeRequetes.length; i++)
            {
                //nom de table
                rs = st.executeQuery(getRequete(listeRequetes[i]));
                result += "+-+-+-+"+rs.getMetaData().getTableName(1)+"+-+-+-+";
                result += "\n";
                //noms de colonnes
                for(int j = 1; j<=rs.getMetaData().getColumnCount();j++)
                {
                    result += rs.getMetaData().getColumnName(j);
                    result += "  |   ";
                }
                result += "\n";
                //tuples
                while (rs.next()) {
                    for(int k = 1; k<=rs.getMetaData().getColumnCount();k++)
                    {
                        result += rs.getString(k);
                        result += "  |   ";
                    }
                    result += "\n";
                }
                result += "\n\n\n";
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
     
         /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'un ResultSet
     */
    /*public List<Representation> getListeRepresentations() throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select r.*, s.Nom from Spectacle s, Representation r where s.NoSpectacle=r.NoSpectacle";
            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                Representation representation = new Representation(rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), rs.getDate("Date"), rs.getString("Nom"));
                result.add(representation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }*/
}
