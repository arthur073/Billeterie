/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author arthur
 */
@WebServlet(name = "ReservationControleur", urlPatterns = {"/ReservationControleur"})
public class ReservationControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getSession(false).getAttribute("LoggedIn") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");

        try {
            // TODO : if à mettre
            actionReserver(request, response);
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            //getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void actionReserver(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {
        RepresentationDAO rep = new RepresentationDAO(ds);
        ArrayList<Float> listePrix = new ArrayList<Float>();
        listePrix = rep.getRepresentationPrice(Integer.parseInt(request.getParameter("NoSpectacle").toString()), Integer.parseInt(request.getParameter("NoRepresentation").toString()));
        
        // TODO : prendre les prix de la requête de thib
        request.setAttribute("p1", listePrix.remove(0));
        request.setAttribute("p2", listePrix.remove(0));
        request.setAttribute("p3", listePrix.remove(0));
        
        int NoSpectacle = Integer.parseInt(request.getParameter("NoSpectacle").toString());
        int NoRepresentation = Integer.parseInt(request.getParameter("NoRepresentation").toString());
        request.setAttribute("representations", rep.getListeRepresentations(NoSpectacle, NoRepresentation));
        getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }
}
