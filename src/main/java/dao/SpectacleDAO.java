package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import modele.Spectacle;
import modele.Representation;

public class SpectacleDAO extends ProviderDAO<Spectacle> {

    public SpectacleDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des représentations du spectacle donné.
     *
     * La liste est triée par dates de représentation croissantes.
     */
    public List<Representation> getRepresentationsPour(int NoSpectacle, int NoRepresentation) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_REPRESENTATIONS_DU_SPECTACLE"));
            st.setInt(1, NoSpectacle);
            st.setInt(2, NoRepresentation);
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
     * 
     * TODO : ajouter des possibilités de filtrage par période
     */
    public static List<Spectacle> getSpectacles()
    {
        // TODO
        return null;
    }

    /**
     * Construit un objet Spectacle à partir des champs d'un ResultSet.
     */
    static Spectacle construire(ResultSet rs) throws SQLException {
        Spectacle s = new Spectacle(rs.getInt("NoSpectacle"),
                    rs.getString("Nom"), rs.getString("Image"));
        return s;
    }


    @Override
    public void creer(Spectacle obj) throws DAOException {
        // TODO Auto-generated method stub

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
    public void mettreAJour(Spectacle obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void supprimer(Spectacle obj) throws DAOException {
        // TODO Auto-generated method stub

    }
}
