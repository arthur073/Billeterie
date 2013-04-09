/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author Michel
 */
public class SqlDAO extends AbstractDataBaseDAO {
    
    
     public SqlDAO(DataSource ds) {
        super(ds);
    }
     
         /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'un ResultSet
     */
    public String getRequete(String key) throws DAOException {
        String requeteSQL = null;
        Properties props = new Properties();
        InputStream stream;
        try {
                stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("/some.properties");
                props.load(stream);
                requeteSQL = props.getProperty(key);
                stream.close();
        } catch (Exception e) {
            throw new DAOException("Erreur Fichier " + e.getMessage(), e);
        } finally {
        }
        return requeteSQL;
    }
}
