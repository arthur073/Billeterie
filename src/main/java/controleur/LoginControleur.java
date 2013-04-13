/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import dao.UtilisateurDAO;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author thib
 */
@WebServlet(name = "LoginControleur", urlPatterns = {"/LoginControleur"})
public class LoginControleur extends HttpServlet {
    
    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("Valider")) {
                String login = request.getParameter("username");
                String password = request.getParameter("passwd");
                logMeIn(request, response,login,password);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }
    
    private void logMeIn(HttpServletRequest request, HttpServletResponse response, String login, String password) throws ServletException, IOException, DAOException {
    
        UtilisateurDAO utilDAO = new UtilisateurDAO(ds);
        Boolean logged =  utilDAO.ClientIdentification(login, password);
        // TODO à clarifier
        
        if (logged) {
            request.getSession(true).setAttribute("LoggedIn", true);
            request.getSession(true).setAttribute("FailedLogIn", false);
        } else {
            request.getSession(true).setAttribute("FailedLogIn", true);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        actionAfficher(request, response);
    }

    private void actionAfficher(HttpServletRequest request,
        HttpServletResponse response) throws DAOException, ServletException, IOException {
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getListeRepresentations());
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }
    
}
