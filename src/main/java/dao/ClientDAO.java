package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.sql.DataSource;

import modele.Client;
import modele.Representation;
import modele.Reservation;
import modele.Utilisateur;
import modele.TypeUtilisateur;
import modele.Place;

/**
 *
 * @author Michel, Jany
 */
public class ClientDAO extends UtilisateurDAO {

    public ClientDAO(DataSource ds) {
        super(ds);
    }


    /**
     * Renvoie l'ensemble des places réservées par ce client pour une
     * représentation donnée.
     * @return       L'ensemble des places réservées.
     * @param        r Représentation pour laquelle les places sont réservées.
     */
    public Set<Place> getPlacesReserveesPour(Representation r) {
        // TODO
        return null;
    }


    /**
     * @return Ensemble des réservations effectuées par ce client.
     */
    public Set<Reservation> getReservations() {
        // TODO
        return null;
    }

}
