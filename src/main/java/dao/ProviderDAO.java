/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

import java.sql.PreparedStatement;

/**
 * Fournit l'accès au fichier de requêtes SQL et les méthodes de base du patron
 * de conception DAO : création, lecture, mise à jour, suppression.
 *
 * @param <Metier> Type métier associé au DAO.
 *
 * @author Michel, Jany
 */
abstract public class ProviderDAO<Metier> extends AbstractDataBaseDAO {
    /*
     * Accès au fichier de requêtes.
     */

    private Map<String,String> fic;

    public ProviderDAO(DataSource ds) {
        super(ds);
        try {
            Properties props = new Properties();
            InputStream stream;
            stream = this.getClass().getClassLoader().getResourceAsStream("../sql/requetes.properties");
            if( stream != null )
                props.load(stream);
            else
                System.err.println("Erreur de lecture du fichier de requetes sql");
            fic = new HashMap<String, String>(((Map) props));        
        } catch (IOException ex) {
            Logger.getLogger(ProviderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'un ResultSet
     */
    public String getRequete(String key) throws DAOException {
        return fic.get(key);
    }


    /*
     * DAO générique.
     */

    /**
     * Insère dans la BDD l'objet donné. Parmi les champs de obj, ne lit que
     * ceux qui ne sont pas générés automatiquement, et remplit ceux qui le sont.
     * @throws DAOException 
     */
    abstract public void creer(Metier obj) throws DAOException;

    /**
     * Remplit les champs de obj avec les valeurs trouvées dans la BDD. Ne lit que
     * les champs clefs de obj, et écrit dans les autres.
     * @throws DAOException 
     */
    abstract public void lire(Metier obj) throws DAOException;

    /**
     * Met à jour le contenu de la BDD avec les infos de obj. Lit les champs
     * clefs pour déterminer l'objet à mettre à jour, et modifie la ligne
     * correspondante dans la BDD avec les champs non-clefs.
     * @throws DAOException 
     */
    abstract public void mettreAJour(Metier obj) throws DAOException;

    /**
     * Supprime l'objet donné de la BDD. Ne lit que les champs clefs de l'objet.
     * @throws DAOException 
     */
    abstract public void supprimer(Metier obj) throws DAOException;

    /**
     * Ferme un PreparedStatement si besoin.
     *
     * @param st Si null, ne fait rien, sinon le ferme.
     * @throws DAOException
     */
    protected static void closeStatement(PreparedStatement st)
            throws DAOException {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException logOrIgnore) {
                throw new DAOException("Problème à la fermeture du PreparedStatement.");
            }
        }
    }

    /**
     * Ferme un ResultSet si besoin.
     *
     * @param rs Si null, ne fait rien, sinon le ferme.
     * @throws DAOException
     */
    protected static void closeResultSet(ResultSet rs) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException logOrIgnore) {
                throw new DAOException("Problème à la fermeture du ResultSet.");
            }
        }
    }
}
