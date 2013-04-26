package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modele.Spectacle;

/**
 *
 * @author Jany
 */
public class StatsDAO extends ProviderDAO {

    // Calculé une fois pour être dispo dans toutes les requêtes.
    private final int nbTotalPlaces;

    public StatsDAO(DataSource ds, int nbTotalPlaces) {
        super(ds);
        this.nbTotalPlaces = nbTotalPlaces;
    }

    /**
     * Renvoie la somme des prix de toutes les places vendues pour les
     * représentations de la période donnée.
     * @throws DAOException
     */
    public float statBenefTotalPeriode(Date debut, Date fin)
            throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        float result = 0.0f;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_BENEF_TOTAL_PERIODE"));
            st.setDate(1, debut);
            st.setDate(2, fin);
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getFloat("BenefTotal");
            } else {
                throw new DAOException("Le calcul du bénéfice total n'a rien donné.");
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
     * Contient des informations sur la rentabilité d'un spectacle.
     */
    public class InfoRenta {
        public final Spectacle spectacle;
        public final int nbPlacesVendues;
        public final float tauxRemplissage;
        public final float benefTotal;

        public InfoRenta(Spectacle spectacle, int nbPlacesVendues, float benefTotal) {
            this.spectacle = spectacle;
            this.nbPlacesVendues = nbPlacesVendues;
            this.tauxRemplissage = (float) nbPlacesVendues / (float) nbTotalPlaces;
            this.benefTotal = benefTotal;
        }
    }

    /**
     * Renvoie la liste des n spectacles les plus rentables sur la période donnée.
     * @throws DAOException
     */
    public List<InfoRenta> getStatsSpectaclesLesPlusRentables(int n, Date debut, Date fin)
            throws DAOException {
        List<InfoRenta> plusRentables = new ArrayList<InfoRenta>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_SPECTACLES_PLUS_RENTABLES"));
            st.setInt(1, n);
            st.setDate(2, debut);
            st.setDate(3, fin);
            rs = st.executeQuery();
            while (rs.next()) {
                plusRentables.add(new InfoRenta(SpectacleDAO.construire(rs),
                            rs.getInt("NbPlacesVendues"), rs.getFloat("BenefTotal")));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return plusRentables;
    }

    /**
     * Renvoie la liste des n spectacles les plus remplis sur la période donnée.
     * @throws DAOException
     */
    public List<InfoRenta> getStatsSpectaclesLesPlusRemplis(int n, Date debut, Date fin)
            throws DAOException {
        List<InfoRenta> plusRemplis = new ArrayList<InfoRenta>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_SPECTACLES_PLUS_REMPLIS"));
            st.setInt(1, n);
            st.setDate(2, debut);
            st.setDate(3, fin);
            rs = st.executeQuery();
            while (rs.next()) {
                plusRemplis.add(new InfoRenta(SpectacleDAO.construire(rs),
                            rs.getInt("NbPlacesVendues"), rs.getFloat("BenefTotal")));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return plusRemplis;
    }


    /**
     * Renvoie les stats pour tous les spectacles, triés par dates de
     * représentations décroissantes.
     * @throws DAOException
     */
    public List<InfoRenta> getStatsTousSpectacles()
            throws DAOException {
        List<InfoRenta> stats = new ArrayList<InfoRenta>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_STATS_SPECTACLES"));
            rs = st.executeQuery();
            while (rs.next()) {
                stats.add(new InfoRenta(SpectacleDAO.construire(rs),
                            rs.getInt("NbPlacesVendues"), rs.getFloat("BenefTotal")));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return stats;
    }
}
