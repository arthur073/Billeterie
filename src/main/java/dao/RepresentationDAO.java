/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import modele.Achat;
import modele.Representation;
import modele.Spectacle;
import modele.Zone;

/**
 *
 * @author arthur
 */
public class RepresentationDAO extends ProviderDAO<Representation> {
    public RepresentationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des prix associés à chaque catégorie de place pour une
     * représentation donnée.
     */
    public Map<Zone, Float> getPrixParZones(int noSpectacle, int noRepresentation) throws DAOException {
        Map<Zone, Float> prix = new HashMap<Zone, Float>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_PRIX_ZONE_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            rs = st.executeQuery();
            while (rs.next()) {
                // TODO à CHANGER : "z.NoZone" ou juste "NoZone" ?
                prix.put(new Zone(rs.getInt("NoZone"),
                            "TODO",
                            23), rs.getFloat("Prix"));
                // TODO si une zone n'a pas de tarif spcécial pour une
                // représentation, faut-il mettre le tarif de base dans la map ?
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeResultSet(rs);
            closeStatement(st);
            closeConnection(conn);
        }
        return prix;
    }

    /**
     * Construit un objet Representation avec un objet Spectacle associé à
     * partir des champs d'un ResultSet.
     *
     * Note : la visibilité doit être telle que seules les classes de ce paquetage puissent s'en servir.
     */
    static Representation construire(ResultSet rs) throws SQLException {
        Representation representation = new Representation(rs.getInt("NoSpectacle"), 
                rs.getInt("NoRepresentation"), 
                rs.getDate("DateRepresentation"));
        // TODO ne pas créer deux objets Spectacle s'il s'agit deux fois du même spectacle.
        representation.setSpectacle(new Spectacle(rs.getInt("NoSpectacle"),
                    rs.getString("Nom"), rs.getString("Image")));
        return representation;
    }

    /**
     * Renvoie la liste de toutes les représentations à venir.
     *
     * La liste est triée par dates de représentation croissantes.
     */
    public List<Representation> getRepresentationsAVenir() throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_REPRESENTATIONS_A_VENIR"));
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
     * Insère dans la BDD la représentation donnée.
     */
    public void Insert(Representation r) {
    }

    public List<Achat> getAchats(int noSpectacle, int noRepresentation)
            throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_LISTE_ACHATS_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            st.executeQuery();
            while (rs.next()) {
                Achat achat = new Achat(rs.getString("Login"), 
                        rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"), 
                        rs.getInt("NoZone"), rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"), rs.getDate("DateAchat")
                        );
                result.add(achat);
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

    public void libererReservationImpayees() {
        // TODO
    }

    public void annuler(Representation r) {
        annuler(r.getNoSpectacle(), r.getNoRepresentation());
    }

    public void annuler(int noSpectacle, int noRepresentation) {
        // TODO
    }

    public float getPrix(Representation r, Zone z) throws DAOException {
        // TODO avec une vraie requête
        return getPrixParZones(r.getNoSpectacle(), r.getNoRepresentation()).get(z);
    }

    public void setPrix(Zone z, int prix) {
        // TODO
    }

    @Override
    public void creer(Representation obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void lire(Representation obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void mettreAJour(Representation obj) throws DAOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void supprimer(Representation obj) throws DAOException {
        // TODO Auto-generated method stub

    }
}
