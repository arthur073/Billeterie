package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import java.io.*;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "PagesControleur", urlPatterns = {"/PagesControleur"})
public class PagesControleur extends HttpServlet {

    // TODO: Changer quand on aura une bd correcte ! 
    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    /**
     * La méthode principale d'aiguillage.
     */
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if (action == null || action.equalsIgnoreCase("annuler")) {
                actionAfficher(request, response);
            } else if (action.equalsIgnoreCase("goToLogin")) {
                goToLogIn(request, response);
            } else if (action.equalsIgnoreCase("goToLogOut")) {
                goToLogOut(request, response);
            } else if (action.equalsIgnoreCase("goToAbout")) {
                goToAbout(request, response);
            } else if (action.equalsIgnoreCase("Creer un compte")) {
                CreerUnCompte(request, response);
            } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    public void actionAfficher(HttpServletRequest request,
            HttpServletResponse response) throws DAOException, ServletException, IOException {
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        request.setAttribute("representations", repDAO.getRepresentationsAVenir());
        request.setAttribute("titre", "Mes billets en ligne");
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }

    private void goToLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.setAttribute("titre", "Connexion a mon compte");

        request.setAttribute("from", request.getParameter("from"));
        request.getSession().setAttribute("LoggedIn", false);
        request.getSession().setAttribute("FailedLogIn", false);
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    private void goToLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.getSession().setAttribute("LoggedIn", false);
        request.getSession().setAttribute("Admin", false);
        request.getSession().setAttribute("Login", "");
        actionAfficher(request, response);
    }

    private void CreerUnCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(request, response);
    }

    private void goToAbout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titre", "A propos");
        getServletContext().getRequestDispatcher("/WEB-INF/about.jsp").forward(request, response);
    }
 
}
