/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author arthur
 */
public class FlashImpl {

    private String message;
    private String klass;
    private static String start = "Erreur : ";

    public FlashImpl(String message, HttpServletRequest request) {
        this.message = message;
        this.klass = "flash";
        request.setAttribute("flash", this);
    }

    // Constructeur pour changer la classe
    public FlashImpl(String message, HttpServletRequest request, String classe) {
        this.message = message;
        this.klass = classe;
        request.setAttribute("flash", this);
    }

    public void message(String klass, String message) {
        this.klass = klass;
        this.message = message;
    }

    public boolean isEmptyMessage() {
        return message == null;
    }

    public void clear() {
        this.message = null;
        this.klass = null;
    }

    public String getMessage() {
        String msg = message;
        this.clear();
        return msg;
    }

    public String getStart() {
        return start;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }
}
