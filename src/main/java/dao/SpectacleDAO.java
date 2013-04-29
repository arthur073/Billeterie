package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;


import modele.Spectacle;
import modele.Representation;

public class SpectacleDAO extends ProviderDAO implements DAOMetier<Spectacle> {

    public SpectacleDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des représentations futures du spectacle donné, excepté la représentation donnée.
     *
     * La liste est triée par dates de représentation croissantes.
     */
    public List<Representation> getAutresRepresentationsPour(Representation rep) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_AUTRES_REPRESENTATIONS_DU_SPECTACLE"));
            st.setInt(1, rep.getNoSpectacle());
            st.setInt(2, rep.getNoRepresentation());
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(RepresentationDAO.construire(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Renvoie la liste de tous les spectacles programmés.
     * @throws DAOException
     */
    public List<Spectacle> getSpectacles() throws DAOException {
        List<Spectacle> result = new ArrayList<Spectacle>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_TOUS_SPECTACLES"));
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(construire(rs));
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
     * Renvoie la liste de tous les spectacles programmés, ayant au moins une
     * représentation dans la période donnée.
     * @throws DAOException
     */
    public List<Spectacle> getSpectacles(Date debut, Date fin)
            throws DAOException {
        List<Spectacle> result = new ArrayList<Spectacle>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_SPECTACLES_PERIODE"));
            st.setDate(1, new java.sql.Date(debut.getTime()));
            st.setDate(2, new java.sql.Date(fin.getTime()));
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(construire(rs));
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
     * Construit un objet Spectacle à partir des champs d'un ResultSet.
     */
    static Spectacle construire(ResultSet rs) throws SQLException {
        return new Spectacle(rs.getInt("NoSpectacle"),
                    rs.getString("Nom"), rs.getString("Image"));
    }


    @Override
    public void creer(Spectacle spec) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("CREATE_SPECTACLE"));
            st.setString(1, spec.getNom());
            st.setString(2, spec.getImage());
            st.executeQuery();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }

    }

    @Override
    public void lire(Spectacle spec) throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_SPECTACLE"));
            st.setInt(1, spec.getNoSpectacle());
            rs = st.executeQuery();
            if (rs.next()) {
                spec.setNom(rs.getString("Nom"));
                spec.setImage(rs.getString("Image"));
            } else {
                throw new DAOException(DAOException.Type.NON_TROUVE, "Spectacle non trouvé");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }
    }

    @Override
    public void mettreAJour(Spectacle spec) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("UPDATE_SPECTACLE"));
            st.setString(1, spec.getNom());
            st.setString(2, spec.getImage());
            st.setInt(3, spec.getNoSpectacle());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    @Override
    public void supprimer(Spectacle spec) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("DELETE_SPECTACLE"));
            st.setInt(1, spec.getNoSpectacle());
            st.executeQuery();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }
}
