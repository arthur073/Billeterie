package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

/**
 * Le contrôleur de l'application.
 */
@WebServlet(name = "PagesControleur", urlPatterns = {"/pagesControleur"})
    public class PagesControleur extends HttpServlet {

    
    // TODO: Changer quand on aura une bd correcte ! 
	@Resource(name = "jdbc/billeterie")
	    private DataSource ds;


	/**
	 * La méthode principale d'aiguillage.
	 */
	public void doGet(HttpServletRequest request,
			  HttpServletResponse response)
            throws IOException, ServletException {

	    //PrintWriter out = response.getWriter();
	    //String action = request.getParameter("action");
                  
            try {
                actionAfficher(request, response);
            } catch (DAOException e) {
                request.setAttribute("erreurMessage", e.getMessage());
		getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
            }
	}

	private void actionAfficher(HttpServletRequest request, 
				    HttpServletResponse response) throws DAOException, ServletException, IOException {
	    RepresentationDAO repDAO = new RepresentationDAO(ds);
            request.setAttribute("representations", repDAO.getListeRepresentations());                
            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
	}
    }
