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

class PlaceDAO extends ProviderDAO<Place> {

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
                // TODO check si NoZone ça marche ou bien si c'est z.NoZone
                Place p = new Place(rs.getInt("NoPlace"),
                        rs.getInt("NoRang"), rs.getInt("NoZone"));
                Zone z = new Zone(rs.getInt("NoZone"),
                        rs.getString("Categorie"), rs.getFloat("TarifBase"));
                p.setZone(z);
                places.add(p);
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
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PLACE"));
            st.setInt(1, p.getNoZone());
            rs = st.executeQuery();
            if (rs.next()) {
                Zone z = new Zone(p.getNoZone(),
                        rs.getString("Categorie"), rs.getFloat("TarifBase"));
                p.setZone(z);
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
    public void mettreAJour(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }

    @Override
    public void supprimer(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }
}
