package dao;

/**
 * Définit les méthodes de base du patron de conception DAO : création,
 * lecture, mise à jour, suppression.
 *
 * @param <Metier> Type métier associé au DAO.
 *
 * @author Jany
 */
public interface DAOMetier<Metier> {

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
}
