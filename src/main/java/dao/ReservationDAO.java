package dao;

import static dao.ProviderDAO.closeResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

import modele.Client;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.Spectacle;

/**
 *
 * @author arthur
 */
public class ReservationDAO extends ProviderDAO<Reservation> {

    public ReservationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des reservations non payees pour une representation
     * donnee
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
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getFloat("TarifBase"));
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
     * Renvoie la liste des reservations pour une representation donnee
     */
    public LinkedList<Reservation> getListeReservationsPourRepresentation(int noSpectacle, int noRepresentation) throws DAOException {
        LinkedList<Reservation> result = new LinkedList<Reservation>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PLACES_RESERVEES"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            st.setInt(3, noSpectacle);
            st.setInt(4, noRepresentation);
            rs = st.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation("anonymous",
                        noSpectacle, noRepresentation,
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getFloat("TarifBase"));
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
     * Renvoie la liste des reservations non payees pour un client a une
     * representation donnee
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
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getFloat("TarifBase"));
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
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getFloat("TarifBase"));
                this.lire(reservation);
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
    public void creer(Reservation reserv) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("INSERT_RESERVATION"));
            st.setString(1, reserv.getLogin());
            st.setInt(2, reserv.getNoSpectacle());
            st.setInt(3, reserv.getNoRepresentation());
            st.setInt(4, reserv.getNoPlace());
            st.setInt(5, reserv.getNoRang());
            st.setInt(6, reserv.getNoZone());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    /**
     * Ajoute les objets Client, Representation et Place à une reservation dont
     * on connaît les attributs clés (càd tous les attributs).
     */
    @Override
    public void lire(Reservation r) throws DAOException {
        ClientDAO uDAO = new ClientDAO(dataSource);
        Client c = new Client(r.getLogin());
        uDAO.lire(c);
        r.setClient(c);
        RepresentationDAO repDAO = new RepresentationDAO(dataSource);
        Representation rep = new Representation(r.getNoSpectacle(), r.getNoRepresentation());
        repDAO.lire(rep);
        r.setRepresentation(rep);
        PlaceDAO pDAO = new PlaceDAO(dataSource);
        Place p = new Place(r.getNoPlace(), r.getNoRang(), r.getNoZone());
        pDAO.lire(p);
        r.setPlace(p);
    }

    /**
     * Cette méthode n'a pas de sens pour une réservation.
     *
     * @see supprimer
     * @see creer
     */
    @Override
    public void mettreAJour(Reservation reserv) throws DAOException {
        /*
         * Appeler cette méthode n'a pas de sens : pas de champs autres
         * qu'attributs clé dans Reservation.
         */
        throw new DAOException("Reservation.mettreAJour n'a pas de sens.");
    }

    @Override
    public void supprimer(Reservation reserv) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("DELETE_RESERVATION"));
            st.setString(1, reserv.getLogin());
            st.setInt(2, reserv.getNoSpectacle());
            st.setInt(3, reserv.getNoRepresentation());
            st.setInt(4, reserv.getNoZone());
            st.setInt(5, reserv.getNoRang());
            st.setInt(6, reserv.getNoPlace());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }
}
