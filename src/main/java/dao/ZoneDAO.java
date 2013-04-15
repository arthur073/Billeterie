package dao;

import java.util.Set;

import javax.sql.DataSource;

import modele.Zone;
import modele.Place;

class ZoneDAO extends ProviderDAO<Zone> {

    public ZoneDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie l'ensemble des zones de cette zone.
     */
    public Set<Place> getPlaces() {
        // TODO
        return null;
    }

    /**
     * Renvoie l'ensemble des zones existantes.
     */
    public static Set<Zone> getZones() {
        // TODO
        return null;
    }

    @Override
    public void creer(Zone obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles zones !");
    }

    @Override
    public void lire(Zone obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void mettreAJour(Zone obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles zones !");
    }

    @Override
    public void supprimer(Zone obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles zones !");
    }
}
