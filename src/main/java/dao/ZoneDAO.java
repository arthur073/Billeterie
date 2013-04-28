package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modele.Zone;
import modele.Place;

public class ZoneDAO extends ProviderDAO implements DAOMetier<Zone> {

    public ZoneDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des places de la zone donnée.
     * @throws DAOException
     */
    public List<Place> getPlaces(int noZone) throws DAOException {
        List<Place> result = new ArrayList<Place>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PLACES_PAR_ZONE"));
            st.setInt(1, noZone);
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(PlaceDAO.construire(rs));
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
     * Renvoie la liste des zones existantes, triées par tarifs de base
     * croissants.
     * @throws DAOException
     */
    public List<Zone> getZones() throws DAOException {
        List<Zone> result = new ArrayList<Zone>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_TOUTES_ZONES"));
            rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Zone(rs.getInt("NoZone"),
                            rs.getString("Categorie"), rs.getFloat("TarifBase")));
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
    public void creer(Zone obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles zones !");
    }

    @Override
    public void lire(Zone z) throws DAOException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_ZONE"));
            st.setInt(1, z.getNoZone());
            rs = st.executeQuery();
            if (rs.next()) {
                z.setCategorie(rs.getString("Categorie"));
                z.setTarifBase(rs.getFloat("TarifBase"));
            } else {
                throw new DAOException(DAOException.Type.NON_TROUVE, "Zone non trouvé.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
    }

    @Override
    public void mettreAJour(Zone obj) throws DAOException {
        throw new DAOException("Impossible de modifier les zones !");
    }

    @Override
    public void supprimer(Zone obj) throws DAOException {
        throw new DAOException("Impossible de supprimer les zones !");
    }
}
