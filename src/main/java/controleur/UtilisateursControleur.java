/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.BarcodePDF417;
import dao.AchatDAO;
import dao.ClientDAO;
import dao.DAOException;
import dao.RepresentationDAO;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.Achat;

import modele.Client;
import modele.Representation;
import modele.Reservation;
import modele.Utilisateur;
import vue.FlashImpl;

/**
 *
 * @author arthur
 */
@WebServlet(name = "UtilisateursControleur", urlPatterns = {"/UtilisateursControleur"})
public class UtilisateursControleur extends HttpServlet {

    @Resource(name = "jdbc/billeterie")
    private DataSource ds;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("goToMyAccount")) {

                goToMyAccount(request, response);

            } else if (action.equalsIgnoreCase("goToAdmin")) {
                goToAdmin(request, response);
            } else if (action.equalsIgnoreCase("annulerPlaces")) {
                cancelPlaces(request, response);
            } else if (action.equalsIgnoreCase("imprPlaces")) {
                imprPlaces(request, response); 
            } else if (action.equalsIgnoreCase("achatPlaces")) {
                achatPlaces(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } catch (DAOException ex) {
            request.setAttribute("erreurMessage", ex.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String action = request.getParameter("action");
        try {
            if (action.equalsIgnoreCase("Creer")) {
                FormulaireCreerUnCompte(request, response);

            } else if (action.equalsIgnoreCase("Annuler")) {
                // Retour à la page précédente
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void FormulaireCreerUnCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        ClientDAO clientDAO = new ClientDAO(ds);
        Client client = new Client(
                (String) request.getParameter("username"),
                Client.chiffrerMotDePasse((String) request.getParameter("passwd")),
                (String) request.getParameter("nom"),
                (String) request.getParameter("prenom"),
                (String) request.getParameter("email"));
        clientDAO.creer(client);
        getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
    }

    private void goToMyAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        Object loggedIn = request.getSession().getAttribute("LoggedIn");

        if (loggedIn == null || (loggedIn != null && loggedIn.equals(false))) {
            FlashImpl fl = new FlashImpl("Veuillez vous identifier pour accéder à ce service", request, "error");
            request.setAttribute("from", "indexAll");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        // On recherche les attributs de l'utilisateur
        String login = (String) request.getSession().getAttribute("Login");
        Utilisateur u = new Utilisateur(login);
        UtilisateurDAO uDAO = new UtilisateurDAO(ds);
        uDAO.lire(u);

        // on regarde ses places achetées
        ReservationDAO resDAO = new ReservationDAO(ds);
        AchatDAO achDAO = new AchatDAO(ds);

        List<Reservation> listRes = resDAO.getListeReservationsClient(login);

        // On complète les champs de classe
        for (Reservation cur : listRes) {
            RepresentationDAO repDAO = new RepresentationDAO(ds);
            Representation rep = new Representation(cur.getNoSpectacle(),
                    cur.getNoRepresentation());

            repDAO.lire(rep);
            cur.setRepresentation(rep);
        }

        List<Achat> listAchatPrec = achDAO.getListeAchatsClientAvecHistorique(login);
        List<Achat> listAchatSuiv = achDAO.getListeAchatsClientSansHistorique(login);
        request.setAttribute("login", login);
        request.setAttribute("nom", u.getNom());
        request.setAttribute("prenom", u.getPrenom());
        request.setAttribute("email", u.getEmail());
        request.setAttribute("listRes", listRes);
        request.setAttribute("listAchatPrec", listAchatPrec);
        request.setAttribute("listAchatSuiv", listAchatSuiv);

        request.setAttribute("titre", "Mon compte");
        getServletContext().getRequestDispatcher("/WEB-INF/monCompte.jsp").forward(request, response);
    }

    private void goToAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("titre", "Admin");
        StatsControleur.remplirRequeteDeStats(ds, request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    private void cancelPlaces(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException, ServletException {

        String login = request.getParameter("login");
        Integer noS = Integer.parseInt(request.getParameter("noS"));
        Integer noR = Integer.parseInt(request.getParameter("noR"));
        Integer noZ = Integer.parseInt(request.getParameter("noZ"));
        Integer noRang = Integer.parseInt(request.getParameter("noRang"));
        Integer noP = Integer.parseInt(request.getParameter("noP"));
        Float tarif = Float.parseFloat(request.getParameter("tarif"));

        Reservation resa = new Reservation(login, noS, noR, noZ, noRang, noP, tarif);
        ReservationDAO resDAO = new ReservationDAO(ds);
        resDAO.supprimer(resa);

        FlashImpl fl = new FlashImpl("Votre réservation a bien été annulée", request, "success");
        goToMyAccount(request, response);
    }

    private void imprPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        response.setContentType("application/pdf"); // Code 1

        Font itaFont = new Font(BaseFont.ASCENT, 14, Font.ITALIC);
        Font titreFont = new Font(BaseFont.ASCENT, 18, Font.BOLD);
        Font grasFont = new Font(BaseFont.ASCENT, 12, Font.BOLD);
        Document document = new Document(new Rectangle(450, 350));

        // on récupère les informations
        String nomS = request.getParameter("nomS");
        String rootUrl = request.getRequestURL().toString().split("billeterie")[0];
        String image = request.getParameter("image");
        String imageUrl = rootUrl + "billeterie/images/" + image;
        String date = request.getParameter("date");
        String prix = request.getParameter("prix");
        String place = request.getParameter("place");
        String rang = request.getParameter("rang");
        String zone = request.getParameter("zone");
        String numero = request.getParameter("numero");


        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream()); // Code 2
            addMetaData(document);
            document.open();

            Paragraph preface = new Paragraph();
            preface.add(new Paragraph("MesBillets.com", itaFont));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Voici votre e-billet", titreFont));
            addEmptyLine(preface, 2);


            Image img = Image.getInstance(new URL(imageUrl));
            img.scalePercent(70);
            img.setAbsolutePosition(60, 180);

            Paragraph corps = new Paragraph();
            corps.setIndentationLeft(80);
            corps.add(new Paragraph(nomS, grasFont));
            // TODO vrais variables
            corps.add(new Paragraph("Le " + date));
            corps.add(new Paragraph(prix + " €", grasFont));

            BarcodeEAN codeEAN = new BarcodeEAN();
            String innerCode = (String.valueOf(numero.hashCode() * place.hashCode() * rang.hashCode() * prix.hashCode() * nomS.hashCode())
                    + String.valueOf(img.hashCode())).substring(0, 13);
            codeEAN.setCode(innerCode);

            Image code = codeEAN.createImageWithBarcode(writer.getDirectContent(), null, null);
            code.scalePercent(150);
            code.setAbsolutePosition(280, 180);
            addEmptyLine(corps, 2);


            Paragraph billet = new Paragraph();
            billet.add(new Paragraph(zone.toUpperCase(), grasFont));
            billet.add(new Paragraph("Billet numéro " + numero, grasFont));
            billet.add(new Paragraph("Place " + place, grasFont));
            billet.add(new Paragraph("Rang " + rang, grasFont));








            Paragraph fin = new Paragraph();
            addEmptyLine(fin, 1);
            fin.add(new Paragraph("Imprimez ce e-billet et présentez-le à l'entrée du spectacle. "));




            document.add(preface);
            document.add(img);
            document.add(corps);
            document.add(code);
            document.add(billet);
            document.add(fin);
            document.close();
        } catch (com.lowagie.text.DocumentException ex) {
            ex.printStackTrace();
        }
    }

    private void addMetaData(Document document) {
        document.addTitle("Votre facture");
        document.addSubject("Avec MesBillets.com");
        document.addKeywords("Billets, iText");
        document.addAuthor("Arthur Verger");
        document.addCreator("Arthur Verger");
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void achatPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // TODO : mettre ici les bons attributs pour : 
        // prixToTal [DONE]
        // places => simplement mettre la place au bon format (on connait déjà tout !)
        // map => ? 
        // noSpectacle [DONE]
        // noRepresentation [DONE]

        getServletContext().getRequestDispatcher("/WEB-INF/payer.jsp").forward(request, response);
    }
}
