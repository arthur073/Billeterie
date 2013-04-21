package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import modele.Client;
import modele.Representation;
import modele.Place;
import modele.Zone;

public class PlaceDAO extends ProviderDAO<Place> {

    public PlaceDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie l'ensemble des places existantes.
     * @return Set<Place>
     * @throws DAOException
     */
    public Set<Place> getPlaces() throws DAOException {
        Set<Place> places = new HashSet<Place>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_TOUTES_PLACES"));
            rs = st.executeQuery();
            while (rs.next()) {
                places.add(construire(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return places;
    }

    /**
     * Construit une place à partir d'un ResultSet.
     * @throws SQLException
     */
    public static Place construire(ResultSet rs) throws SQLException {
        Place p = new Place(rs.getInt("NoPlace"),
                rs.getInt("NoRang"), rs.getInt("NoZone"));
        Zone z = new Zone(rs.getInt("NoZone"),
                rs.getString("Categorie"), rs.getFloat("TarifBase"));
        p.setZone(z);
        return p;
    }

    /**
     * Renvoie le client qui a réservé cette place pour la réprésentation donnée.
     * @return       Le client, ou null si la place est libre.
     * @param        r
     */
    public Client getReservateurPour(Representation r) {
        // TODO est-ce utile ?
        return null;
    }

    @Override
    public void creer(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }

    /**
     * Cette méthode sert à compléter l'attribut Zone d'un place dont on
     * connait la clé.
     */
    @Override
    public void lire(Place p) throws DAOException {
        ZoneDAO zdao = new ZoneDAO(dataSource);
        Zone z = new Zone(p.getNoZone());
        zdao.lire(z);
        p.setZone(z);
    }

    @Override
    public void mettreAJour(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }

    @Override
    public void supprimer(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }
}
