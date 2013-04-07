/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arthur
 */
public class RepresentationsControleur extends HttpServlet {
    
    public void doGet(HttpServletRequest request,
			  HttpServletResponse response)
            throws IOException, ServletException {

	    //PrintWriter out = response.getWriter();
	    //String action = request.getParameter("action");
                  
           // try {
                //actionAfficher(request, response);
           // } catch (DAOException e) {
           //     request.setAttribute("erreurMessage", e.getMessage());
		//getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
           // }
	}
    
}
