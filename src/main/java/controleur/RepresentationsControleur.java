/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arthur
 */
@WebServlet(name = "RepresentationsControleur", urlPatterns = {"/RepresentationsControleur"})
public class RepresentationsControleur extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request,
			  HttpServletResponse response)
            throws IOException, ServletException {

            if (request.getSession(false).getAttribute("LoggedIn") == null) {
		getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        
	    String action = request.getParameter("action");

            try {
                //utilisé uniquement pour verifier que pas mauvaise url (null pointer except)
                if( !action.equals(""))
                 actionReserver(request, response);
            } catch (DAOException e) {
                request.setAttribute("erreurMessage", e.getMessage());
		//getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
            } catch (NullPointerException e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            }

	}

    private void actionReserver(HttpServletRequest request, HttpServletResponse response) throws ServletException, DAOException, IOException {
       getServletContext().getRequestDispatcher("/WEB-INF/reserver.jsp").forward(request, response);
    }
    
}
