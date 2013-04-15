package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import modele.Client;
import modele.Place;
import modele.Representation;
import modele.Reservation;

/**
 *
 * @author arthur
 */
public class ReservationDAO extends ProviderDAO<Reservation> {
    
     public ReservationDAO(DataSource ds) {
        super(ds);
    }
     
         /**
     * Renvoie la liste des reservations non payees pour une representation donnee
     */
    public List<Reservation> getListeReservationsNonPayeesRepresentation(int noSpectacle, int noRepresentation) throws DAOException {
        List<Reservation> result = new ArrayList<Reservation>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_NON_PAYEES_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
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
            closeResultSet(rs);
            closeStatement(st);
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
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_CLIENT_REPRESENTATION"));
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
            closeResultSet(rs);
            closeStatement(st);
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
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_RESERVATIONS_CLIENT"));
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
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return result;
    }

    @Override
    public void creer(Reservation obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    /**
     * Ajoute les objets Client, Representation et Place à une reservation
     * dont on connaît les attributs clés (càd tous les attributs).
     */
    @Override
    public void lire(Reservation r) throws DAOException {
        ClientDAO uDAO = new ClientDAO(dataSource);
        Client c = new Client(r.getLogin());
        uDAO.lire(c);
        r.setClient(c);
        RepresentationDAO repDAO = new RepresentationDAO(dataSource);
        Representation rep = new Representation(r.getNoRepresentation(), r.getNoSpectacle());
        repDAO.lire(rep);
        r.setRepresentation(rep);
        PlaceDAO pDAO = new PlaceDAO(dataSource);
        Place p = new Place(r.getNoPlace(), r.getNoRang(), r.getNoZone());
        pDAO.lire(p);
        r.setPlace(p);
    }

    @Override
    public void mettreAJour(Reservation obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void supprimer(Reservation obj) throws DAOException {
        // TODO Auto-generated method stub

    }
}
