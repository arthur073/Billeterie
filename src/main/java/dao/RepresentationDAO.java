/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
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
public class RepresentationDAO extends AbstractDataBaseDAO {
    
    
     public RepresentationDAO(DataSource ds) {
        super(ds);
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
   //     try {
   //         conn = getConnection();
   //         Statement st = conn.createStatement();
   //         requeteSQL = "select * from billeterie";
   //         rs = st.executeQuery(requeteSQL);
//
//            while (rs.next()) {
//                Ouvrage ouvrage = new Ouvrage(rs.getInt("id"), rs.getString("auteur"), rs.getString("titre"));
//                System.err.println(ouvrage);
//                result.add(ouvrage);
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Erreur BD " + e.getMessage(), e);
//        } finally {
//            closeConnection(conn);
//        }
        return result;
    }
}