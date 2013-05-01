package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
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
                /* On veut un ordre décroissant sur les rangs. */
                places[maxRang - rs.getInt("NoRang")][rs.getInt("NoPlace") - 1] = construire(rs);
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

    @Override
    public void creer(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de nouvelles places !");
    }

    /**
     * Cette méthode sert à compléter l'attribut Zone d'un place dont on connait
     * la clé, et à vérifier que cette place est bien dans la BDD.
     */
    @Override
    public void lire(Place p) throws DAOException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PLACE"));
            st.setInt(1, p.getNoPlace());
            st.setInt(2, p.getNoRang());
            st.setInt(3, p.getNoZone());
            rs = st.executeQuery();
            if (rs.next()) {
                p.setZone(ZoneDAO.construire(rs));
            } else {
                throw new DAOException(DAOException.Type.NON_TROUVE, "La place donnée n'existe pas.");
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.Type.MAUVAIS_SQL, "Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
    }

    @Override
    public void mettreAJour(Place obj) throws DAOException {
        throw new DAOException("Impossible de mettre à jour de novelles places !");
    }

    @Override    public void supprimer(Place obj) throws DAOException {
        throw new DAOException("Impossible de suppriemer  de novelles places !");
    }
}
