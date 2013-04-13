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
import modele.Achat;

/**
 *
 * @author Michel
 */
public class AchatDAO extends ProviderDAO {
    
    
     public AchatDAO(DataSource ds) {
        super(ds);
    }
     
      /**
     * Renvoie la liste des achats pour une representation donnee
     */
    public List<Achat> getListeAchatsRepresentation(int noSpectacle, int noRepresentation) throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            st.executeQuery();

            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"), rs.getDate("DateAchat")
                        );
                result.add(achat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
       /**
     * Renvoie la liste des achats pour un client à une representation donnee
     */
    public List<Achat> getListeAchatsClientRepresentation(String login, int noSpectacle, int noRepresentation) throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_CLIENT_REPRESENTATION"));
            st.setString(1, login);
            st.setInt(2, noSpectacle);
            st.setInt(3, noRepresentation);
            rs = st.executeQuery();

            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"), rs.getDate("DateAchat")
                );
                result.add(achat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
      /**
     * Renvoie la liste des achats pour un client avec historique à un an
     */
    public List<Achat> getListeAchatsClientAvecHistorique(String login) throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_CLIENT_AVEC_HISTORIQUE"));
            st.setString(1, login);
            rs = st.executeQuery();

            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"), rs.getDate("DateAchat")                       
                        );
                result.add(achat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
         /**
     * Renvoie la liste des achats pour un client sans historique à un an
     */
    public List<Achat> getListeAchatsClientSansHistorique(String login) throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_CLIENT_SANS_HISTORIQUE"));
            st.setString(1, login);
            rs = st.executeQuery();

            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"), rs.getDate("DateAchat")                       
                        );
                result.add(achat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
         /**
     * Renvoie la liste des achats pour un client sans historique à un an
     */
    public void setAchatClient(Achat a) throws DAOException {
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("INSERT_ACHAT"));
            st.setString(1, a.getLogin());
            st.setInt(2, a.getNoSpectacle());
            st.setInt(3, a.getNoRepresentation());
            st.setInt(4, a.getNoZone());
            st.setInt(5, a.getNoRang());
            st.setInt(6, a.getNoPlace());
            st.setInt(7, a.getNoDossier());
            st.setInt(8, a.getNoSerie());
            st.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
}
