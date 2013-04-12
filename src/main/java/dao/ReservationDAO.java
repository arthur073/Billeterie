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
import modele.Reservation;

/**
 *
 * @author arthur
 */
public class ReservationDAO extends ProviderDAO {
    
    
     public ReservationDAO(DataSource ds) {
        super(ds);
    }
     
         /**
     * Renvoie la liste des reservations non payees pour une representation donnee
     */
    public List<Reservation> getListeReservationsNonPayeesRepresentation(int noSpectacle, int noRepresentation) throws DAOException {
        List<Reservation> result = new ArrayList<Reservation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_NON_PAYEES_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            st.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace")
                        );
                result.add(reservation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
       /**
     * Renvoie la liste des reservations non payees pour un client a une representation donnee
     */
    public List<Reservation> getListeReservationsClientRepresentation(String login, int noSpectacle, int noRepresentation) throws DAOException {
        List<Reservation> result = new ArrayList<Reservation>();
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
                Reservation reservation = new Reservation(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace")
                        );
                result.add(reservation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
      /**
     * Renvoie la liste des reservations non payees pour un client
     */
    public List<Reservation> getListeReservationsClient(String login) throws DAOException {
        List<Reservation> result = new ArrayList<Reservation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_CLIENT"));
            st.setString(1, login);
            rs = st.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace")
                        );
                result.add(reservation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
}
