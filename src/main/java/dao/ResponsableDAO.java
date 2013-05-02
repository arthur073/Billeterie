/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import modele.ScriptRunner;

/**
 *
 * @author arthur
 */
public class ResponsableDAO extends ProviderDAO {
    
    
     public ResponsableDAO(DataSource ds) {
        super(ds);
    }
     
     /**
     * Sauvegarde la base et donne un fichier à l'utilisateur
     * 
     */
    public void viderBase() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String listeRequetes[] = {"DELETE_ALL_TABLE_ACHETE", "DELETE_ALL_TABLE_RESERVE",
            "DELETE_ALL_TABLE_REPRESENTATION", "DELETE_ALL_TABLE_SPECTACLE"};

            for(int i = 0; i<listeRequetes.length; i++)
            {
                st.executeUpdate(getRequete(listeRequetes[i]));
            }
        }   catch (SQLException ex) {
            Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erreur BD", ex);
        }
    }
    
         /**
     * Sauvegarde la base et donne un fichier à l'utilisateur
     * 
     */
    public String getBackup() throws DAOException {
        ResultSet rs = null;
        String result = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            String listeRequetes[] = {"SELECT_ALL_TABLE_ACHETE", "SELECT_ALL_TABLE_RESERVE",
            "SELECT_ALL_TABLE_PLACE", "SELECT_ALL_TABLE_REPRESENTATION", "SELECT_ALL_TABLE_SPECTACLE",
            "SELECT_ALL_TABLE_UTILISATEUR", "SELECT_ALL_TABLE_ZONE" };

            for(int i = 0; i<listeRequetes.length; i++)
            {
                //nom de table
                rs = st.executeQuery(getRequete(listeRequetes[i]));
                result += "+-+-+-+"+rs.getMetaData().getTableName(1)+"+-+-+-+";
                result += "\n";
                //noms de colonnes
                for(int j = 1; j<=rs.getMetaData().getColumnCount();j++)
                {
                    result += rs.getMetaData().getColumnName(j);
                    result += "  |   ";
                }
                result += "\n";
                //tuples
                while (rs.next()) {
                    for(int k = 1; k<=rs.getMetaData().getColumnCount();k++)
                    {
                        result += rs.getString(k);
                        result += "  |   ";
                    }
                    result += "\n";
                }
                result += "\n\n\n";
            }

        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
     /**
     * Peuple la base
     * 
     */
    public void peuplerBase() throws DAOException {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            
            String path = this.getClass().getClassLoader().getResource("../sql/insert.sql").toString();
            String modifiedPath;
            //on enleve le file:
            modifiedPath = path.substring(5);
            runner.runScript(new BufferedReader(new FileReader(modifiedPath)));
        }   catch (SQLException ex) {
            Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erreur BD", ex);
        } catch (IOException ex) {
             Logger.getLogger(ResponsableDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("erreur fichier insert", ex);
         }
    }
}
