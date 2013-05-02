package dao;

import java.sql.Connection;
import java.util.Date;
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

    public StatsDAO(DataSource ds) throws DAOException {
        super(ds);
        this.nbTotalPlaces = (new PlaceDAO(ds)).getNombrePlaces();
    }

    /**
     * Renvoie le nombre total de places vendues sur la période.
     *
     * @throws DAOException
     */
    public int getNbAchatsPeriode(Date debut, Date fin)
            throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_NB_ACHATS_PERIODE"));
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NbAchats");
            } else {
                throw new DAOException("Le calcul du nombre d'achats n'a rien donné.");
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
     * Renvoie la somme des prix de toutes les places vendues pour les
     * représentations de la période donnée.
     *
     * @throws DAOException
     */
    public float getBenefTotalPeriode(Date debut, Date fin)
            throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        float result = 0.0f;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_BENEF_TOTAL_PERIODE"));
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
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
     * Compte le nombre de représentations du spectacle donné pendant la période
     * donnée.
     */
    public int getNbRepresentationsPeriode(int noSpectacle, Date debut, Date fin) throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        int result = 0;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_NB_REPRESENTATIONS_PERIODE"));
            st.setInt(1, noSpectacle);
            st.setDate(2, new java.sql.Date(debut.getTime()));
            st.setDate(3, new java.sql.Date(fin.getTime()));
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NbRepresentations");
            } else {
                throw new DAOException("Le calcul du nombre de représentations n'a rien donné.");
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

        public Spectacle getSpectacle() {
            return spectacle;
        }

        public int getNbPlacesVendues() {
            return nbPlacesVendues;
        }

        public float getTauxRemplissage() {
            return tauxRemplissage;
        }

        public float getBenefTotal() {
            return benefTotal;
        }

        public InfoRenta(Spectacle spectacle, int nbRepresentations, int nbPlacesVendues, float benefTotal) {
            this.spectacle = spectacle;
            this.nbPlacesVendues = nbPlacesVendues;
            this.tauxRemplissage = (float) nbPlacesVendues / (float) (nbTotalPlaces * nbRepresentations);
            this.benefTotal = benefTotal;
        }
    }

    /**
     * Renvoie la liste des n spectacles les plus rentables sur la période
     * donnée.
     *
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
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
            st.setInt(3, n);
            rs = st.executeQuery();
            while (rs.next()) {
                plusRentables.add(new InfoRenta(SpectacleDAO.construire(rs),
                        getNbRepresentationsPeriode(rs.getInt("NoSpectacle"), debut, fin),
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
     *
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
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
            st.setInt(3, n);
            rs = st.executeQuery();
            while (rs.next()) {
                plusRemplis.add(new InfoRenta(SpectacleDAO.construire(rs),
                        getNbRepresentationsPeriode(rs.getInt("NoSpectacle"), debut, fin),
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
     *
     * @throws DAOException
     */
    public List<InfoRenta> getStatsTousSpectacles(Date debut, Date fin)
            throws DAOException {
        List<InfoRenta> stats = new ArrayList<InfoRenta>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_STATS_SPECTACLES"));
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
            rs = st.executeQuery();
            while (rs.next()) {
                stats.add(new InfoRenta(SpectacleDAO.construire(rs),
                        getNbRepresentationsPeriode(rs.getInt("NoSpectacle"), debut, fin),
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
