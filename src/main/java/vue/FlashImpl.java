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

    private static final String ERROR = "error";
    private static final String WARNING = "warning";
    private static final String NOTICE = "notice";
    private String message;
    private String klass;

    public FlashImpl(String message, HttpServletRequest request) {
        this.message = message;
        request.setAttribute("flash", this);
    }
    
    
    public void message(String klass, String message) {
        this.klass = klass;
        this.message = message;
    }

    public void notice(String message) {
        this.message(NOTICE, message);
    }

    public void warning(String message) {
        this.message(WARNING, message);
    }

    public void error(String message) {
        this.message(ERROR, message);
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
