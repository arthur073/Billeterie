package dao;

import java.util.Set;

import javax.sql.DataSource;

import modele.Client;
import modele.Representation;
import modele.Place;

class PlaceDAO extends ProviderDAO<Place> {

    public PlaceDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie l'ensemble des places existantes.
     * @return Set<Place>
     */
    public static Set<Place> getPlaces() {
        // TODO
        return null;
    }

    /**
     * Renvoie le client qui a réservé cette place pour la réprésentation donnée.
     * @return       Le client, ou null si la place est libre.
     * @param        r
     */
    public Client getReservateurPour(Representation r) {
        // TODO
        return null;
    }

    @Override
    public void creer(Place obj) throws DAOException {
        throw new DAOException("Impossible de créer de novelles places !");
    }

    @Override
    public void lire(Place obj) throws DAOException {
        // TODO Auto-generated method stub
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
