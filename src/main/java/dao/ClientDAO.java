package dao;

import javax.sql.DataSource;

/**
 *
 * @author Michel, Jany
 */
public class ClientDAO extends UtilisateurDAO {

    public ClientDAO(DataSource ds) {
        super(ds);
    }
}
