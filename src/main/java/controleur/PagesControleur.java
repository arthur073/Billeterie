package controleur;

import dao.DAOException;
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
@WebServlet(name = "Controleur", urlPatterns = {"/controleur"})
    public class PagesControleur extends HttpServlet {

    
    // TODO: Changer quand on aura une bd correct ! 
	@Resource(name = "jdbc/bibliography")
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
	    getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
	}
    }