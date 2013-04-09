/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author Michel
 */
public class SqlDAO extends AbstractDataBaseDAO {
    
    private Map<String,String> fic;
    
     public SqlDAO(DataSource ds) {
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
        } catch (Exception ex) {
               Logger.getLogger(SqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
         /**
     * Renvoie la liste des ouvrages de la table bibliographie sous la forme
     * d'un ResultSet
     */
    public String getRequete(String key) throws DAOException {
        return fic.get(key);
    }
}
