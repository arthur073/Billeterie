///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package dao;
//
//import javax.sql.DataSource;
//
///**
// *
// * @author arthur
// */
//public class ResponsableDAO extends AbstractDataBaseDAO {
//    
//    
//     public ResponsableDAO(DataSource ds) {
//        super(ds);
//    }
//     
//         /**
//     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
//     * d'un ResultSet
//     */
//    /*public List<Representation> getListeRepresentations() throws DAOException {
//        List<Representation> result = new ArrayList<Representation>();
//        ResultSet rs = null;
//        String requeteSQL = "";
//        Connection conn = null;
//        try {
//            conn = getConnection();
//            Statement st = conn.createStatement();
//            requeteSQL = "select r.*, s.Nom from Spectacle s, Representation r where s.NoSpectacle=r.NoSpectacle";
//            rs = st.executeQuery(requeteSQL);
//
//            while (rs.next()) {
//                Representation representation = new Representation(rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), rs.getDate("Date"), rs.getString("Nom"));
//                result.add(representation);
//            }
//        } catch (SQLException e) {
//            throw new DAOException("Erreur BD " + e.getMessage(), e);
//        } finally {
//            closeConnection(conn);
//        }
//        return result;
//    }*/
//}
