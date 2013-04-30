/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.ProviderDAO.closeStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Achat;
import modele.Representation;
import modele.Spectacle;

/**
 *
 * @author arthur
 */
public class RepresentationDAO extends ProviderDAO implements DAOMetier<Representation> {
    public RepresentationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Construit un objet Representation avec un objet Spectacle associé à
     * partir des champs d'un ResultSet.
     *
     * Note : la visibilité doit être telle que seules les classes de ce paquetage puissent s'en servir.
     */
    static Representation construire(ResultSet rs) throws SQLException {
        try {
            Date dateFormatted = new Date( rs.getTimestamp("DateRepresentation").getTime() );
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
            Date dat = df.parse(df.format(dateFormatted));
            Representation representation = new Representation(
                    rs.getInt("NoSpectacle"), rs.getInt("NoRepresentation"),
                    dat );
            representation.setSpectacle(new Spectacle(rs.getInt("NoSpectacle"), rs
                    .getString("Nom"), rs.getString("Image")));
            return representation;
        } catch (ParseException ex) {
            Logger.getLogger(RepresentationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            st = conn
                    .prepareStatement(getRequete("SELECT_LISTE_REPRESENTATIONS_A_VENIR"));
            rs = st.executeQuery();
            while (rs.next()) {
                Representation r = construire(rs);
                //TODO
                if( r.isDateLessThanAnHour() ) {
                    System.out.println("dtc");
                } else {
                    System.out.println("ok");
                }
                result.add(r);
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

    public List<Achat> getAchats(int noSpectacle, int noRepresentation)
            throws DAOException {
        List<Achat> result = new ArrayList<Achat>();
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn
                    .prepareStatement(getRequete("SELECT_LISTE_ACHATS_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            rs = st.executeQuery();
            while (rs.next()) {
                Date dateFormatted = new Date( rs.getTimestamp("DateAchat").getTime() );
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
                Date dat = df.parse(df.format(dateFormatted));
                Achat achat = new Achat(rs.getString("Login"),
                        rs.getInt("NoSpectacle"),
                        rs.getInt("NoRepresentation"), rs.getInt("NoZone"),
                        rs.getInt("NoRang"), rs.getInt("NoPlace"),
                        rs.getInt("NoDossier"), rs.getInt("NoSerie"),
                        dat, rs.getFloat("TarifBase"));
                result.add(achat);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } catch (ParseException ex) {
            Logger.getLogger(RepresentationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }
        return result;
    }

    public void annuler(Representation r) throws DAOException {
        annuler(r.getNoSpectacle(), r.getNoRepresentation());
    }

    public void annuler(int noSpectacle, int noRepresentation)
            throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("ANNULER_REPRESENTATION"));
            st.setInt(1, noSpectacle);
            st.setInt(2, noRepresentation);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    @Override
    public void creer(Representation rep) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("INSERT_REPRESENTATION"));
            st.setInt(1, rep.getNoSpectacle());
            st.setInt(2, rep.getNoRepresentation());
            st.setDate(3, new java.sql.Date(rep.getDate().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    /**
     * Lit les attributs clés et remplit la date et le spectacle correspondant.
     */
    @Override
    public void lire(Representation rep) throws DAOException {
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_REPRESENTATION"));
            st.setInt(1, rep.getNoSpectacle());
            st.setInt(2, rep.getNoRepresentation());
            rs = st.executeQuery();
            if (rs.next()) {
                Date dateFormatted = new Date( rs.getTimestamp("DateRepresentation").getTime() );
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
                Date dat = df.parse(df.format(dateFormatted));
                rep.setDate(dat);
                System.out.println(rs.getInt("NoSpectacle"));
                rep.setSpectacle(SpectacleDAO.construire(rs));
            } else {
                throw new DAOException(DAOException.Type.NON_TROUVE,
                        "Représentation non trouvée"+ st.toString());
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } catch (ParseException ex) {
            Logger.getLogger(RepresentationDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }
    }

    /**
     * Lit tous les attributs et met à jour la BDD.
     */
    @Override
    public void mettreAJour(Representation rep) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("UPDATE_REPRESENTATION"));
            st.setInt(1, rep.getNoSpectacle());
            st.setInt(2, rep.getNoRepresentation());
            st.setDate(3, new java.sql.Date(rep.getDate().getTime()));
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }

    /**
     * Lit les attributs clés et supprime la représentation correspondante de
     * la BDD.
     */
    @Override
    public void supprimer(Representation rep) throws DAOException {
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("DELETE_REPRESENTATION"));
            st.setInt(1, rep.getNoSpectacle());
            st.setInt(2, rep.getNoRepresentation());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeStatement(st);
            closeConnection(conn);
        }
    }
    
    public int getNbPlacesRestantes(int NoSpectacle, int NoRepresentation) throws DAOException {
        int result = 450;
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn = getConnection();
            st = conn.prepareStatement(getRequete("SELECT_NB_PLACES_RESTANTES"));
            st.setInt(1, NoSpectacle);
            st.setInt(2, NoRepresentation);
            st.setInt(3, NoSpectacle);
            st.setInt(4, NoRepresentation);
            rs = st.executeQuery();
            while (rs.next()) {
                result -= rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement(st);
            closeResultSet(rs);
            closeConnection(conn);
        }        
        return result;
    }
}
