/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
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
public class AchatDAO extends ProviderDAO implements DAOMetier<Achat> {

    public AchatDAO(DataSource ds) {
        super(ds);
    }

    @Override
    public void creer(Achat achat) throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("INSERT_ACHAT"));
            st.setString(1, achat.getLogin());
            st.setInt(2, achat.getNoSpectacle());
            st.setInt(3, achat.getNoRepresentation());
            st.setInt(4, achat.getNoPlace());
            st.setInt(5, achat.getNoRang());
            st.setInt(6, achat.getNoZone());
            st.setInt(7, achat.getNoDossier());
            st.setInt(8, achat.getNoSerie());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        
    }

    @Override
    public void mettreAJour(Achat obj) throws DAOException {
        throw new DAOException("La fonction n'a pas de sens");
    }

    @Override
    public void supprimer(Achat ach) throws DAOException {
        throw new DAOException("Impossible de supprimer un achat !");
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
                        new Date(rs.getTimestamp("DateAchat").getTime()),
			rs.getFloat("TarifBase"));
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
                achat.setDateAchat(new Date(rs.getTimestamp("DateAchat").getTime()));
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
                        new Date( rs.getTimestamp("DateAchat").getTime() ),
			rs.getFloat("TarifBase"));
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
                Date dat= new Date( rs.getTimestamp("DateAchat").getTime() );
                Achat achat = new Achat(rs.getString("Login"),
                        rs.getInt("NoSpectacle"),
                        rs.getInt("NoRepresentation"), rs.getInt("NoZone"),
                        rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"),
                        dat, rs.getFloat("TarifBase"));
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
    
    public int getProchainNumDossier(int NoSpectacle, int NoRepresentation) throws DAOException{
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        int result = 1;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PROCHAIN_NUM_DOSSIER"));
            st.setInt(1, NoSpectacle);
            st.setInt(2, NoRepresentation);
            rs = st.executeQuery();
            while (rs.next()) {
                result = rs.getInt(1);
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
}
