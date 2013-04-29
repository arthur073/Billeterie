package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import modele.Client;
import modele.Representation;
import modele.Place;
import modele.Zone;

public class PlaceDAO extends ProviderDAO implements DAOMetier<Place> {

    public PlaceDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie une matrice des places existantes.
     *
     * Les lignes correspondent aux rang (décalés de 1), les colonnes aux
     * numéros de places (décalés de 1). Si une intersection est vide, le case
     * associée vaut null.
     */
    public Place[][] getMatricePlaces() throws DAOException {
        // On récupère d'abord la taille du tableau
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int maxRang;
        int maxNumero;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_MAX_RANG_NUM_PLACES"));
            rs = st.executeQuery();
            if (rs.next()) {
                maxRang = rs.getInt("MaxRang");
                maxNumero = rs.getInt("MaxNumero");
            } else {
                throw new DAOException("Le calcul de la taille de la salle n'a rien donné.");
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        conn = null;
        st = null;
        rs = null;
        Place[][] places = new Place[maxRang][maxNumero];
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_TOUTES_PLACES"));
            rs = st.executeQuery();
            while (rs.next()) {
                places[rs.getInt("NoRang") - 1][rs.getInt("NoPlace") - 1] = construire(rs);
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
     * Renvoie la liste des places existantes.
     *
     * Ordonnées par rangs décroissants puis par numéros de place croissants.
     *
     * @return List<Place>
     * @throws DAOException
     */
    public List<Place> getPlaces() throws DAOException {
        List<Place> places = new ArrayList<Place>();
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
     * Renvoie le nombre total de places.
     */
    public int getNombrePlaces() throws DAOException {
        int result = 0;
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_NOMBRE_PLACES"));
            rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NombrePlaces");
            } else {
                throw new DAOException("Le calcul du nombre total de places n'a rien donné.");
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
     * Construit une place à partir d'un ResultSet.
     *
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
     * Renvoie le client qui a réservé cette place pour la réprésentation
     * donnée.
     *
     * @return Le client, ou null si la place est libre.
     * @param r
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
     * Cette méthode sert à compléter l'attribut Zone d'un place dont on connait
     * la clé.
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
