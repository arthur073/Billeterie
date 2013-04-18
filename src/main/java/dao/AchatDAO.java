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
public class AchatDAO extends ProviderDAO<Achat> {

    public AchatDAO(DataSource ds) {
        super(ds);
    }

    @Override
    public void creer(Achat obj) {
        // TODO Auto-generated method stub
    }

    /**
     * Ajoute les champs non-clés et les objets Client, Representation et Place
     * à un achat dont on connaît les attributs clés.
     *
     * @throws DAOException
     */
    @Override
    public void lire(Achat achat) throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_ACHAT"));
            st.setString(1, achat.getLogin());
            st.setInt(2, achat.getNoSpectacle());
            st.setInt(3, achat.getNoRepresentation());
            st.setInt(4, achat.getNoZone());
            st.setInt(5, achat.getNoRang());
            st.setInt(6, achat.getNoPlace());
            rs = st.executeQuery();
            if (rs.next()) {
                achat.setNoDossier(rs.getInt("NoDossier"));
                achat.setNoSerie(rs.getInt("NoSerie"));
                achat.setDateAchat(rs.getDate("DateAchat"));
            } else {
                throw new DAOException("L'achat demandé n'existe pas");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        /*
         * Dans le code Java, un achat est un sous-type de Reservation, et la
         * méthode lire de ReservationDAO ne fait pas de requêtes SQL, donc ne
         * fait pas référence à la relation correspondant à Reservation. On
         * peut donc se servir de cette méthode pour compléter les objets
         * manquants.
         */
        (new ReservationDAO(dataSource)).lire(achat);
    }

    @Override
    public void mettreAJour(Achat obj) {
        // TODO Auto-generated method stub
    }

    @Override
    public void supprimer(Achat obj) {
        // TODO Auto-generated method stub
    }

    /**
     * Renvoie la liste des achats pour un client à une representation donnee
     */
    public List<Achat> getListeAchatsClientRepresentation(String login,
            int noSpectacle, int noRepresentation) throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
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
            ReservationDAO resaDAO = new ReservationDAO(dataSource);

            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"),
                        rs.getInt("NoSpectacle"),
                        rs.getInt("NoRepresentation"), rs.getInt("NoZone"),
                        rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"),
                        rs.getDate("DateAchat"), rs.getFloat("TarifBase"));
                resaDAO.lire(achat);
                result.add(achat);
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
     * Renvoie la liste des achats pour un client avec historique à un an
     */
    public List<Achat> getListeAchatsClientAvecHistorique(String login)
            throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_CLIENT_AVEC_HISTORIQUE"));
            st.setString(1, login);
            rs = st.executeQuery();
            ReservationDAO resaDAO = new ReservationDAO(dataSource);
            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"),
                        rs.getInt("NoSpectacle"),
                        rs.getInt("NoRepresentation"), rs.getInt("NoZone"),
                        rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"),
                        rs.getDate("DateAchat"), rs.getFloat("TarifBase"));
                resaDAO.lire(achat);
                result.add(achat);
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
     * Renvoie la liste des achats pour un client sans historique à un an
     */
    public List<Achat> getListeAchatsClientSansHistorique(String login)
            throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        ReservationDAO resaDAO = new ReservationDAO(dataSource);

        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_CLIENT_SANS_HISTORIQUE"));
            st.setString(1, login);
            rs = st.executeQuery();
            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"),
                        rs.getInt("NoSpectacle"),
                        rs.getInt("NoRepresentation"), rs.getInt("NoZone"),
                        rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"),
                        rs.getDate("DateAchat"), rs.getFloat("TarifBase"));
                resaDAO.lire(achat);
                result.add(achat);
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
     */
    public void setAchatClient(Achat a) throws DAOException {
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("INSERT_ACHAT"));
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
            closeStatement(st);
            closeConnection(conn);
        }
    }
}
