
package dao;

/**
 *
 * @author Philippe.Genoud@imag.fr
 */
public class DAOException extends Exception {

    static public enum Type {
        DEJA_EXISTANT,
        NON_TROUVE,
        MAUVAIS_SQL;
    }

    private Type type = Type.MAUVAIS_SQL;

    public DAOException() {
    }

    public DAOException(Type type) {
        this.type = type;
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public DAOException(String message,Throwable cause) {
        super(message,cause);
    }

    public DAOException(Type type, String message,Throwable cause) {
        super(message,cause);
        this.type = type;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

}
