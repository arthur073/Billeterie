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
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import dao.AchatDAO;
import dao.ClientDAO;
import dao.DAOException;
import dao.PlaceDAO;
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
import modele.Place;
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

            } else if (action.equalsIgnoreCase("goToStats")) {
                goToStats(request, response);
            } else if (action.equalsIgnoreCase("goToAdmin")) {
                goToAdmin(request, response);
            } else if (action.equalsIgnoreCase("annulerPlaces")) {
                cancelPlaces(request, response);
            } else if (action.equalsIgnoreCase("imprPlaces")) {
                imprPlaces(request, response);
            } else if (action.equalsIgnoreCase("achatPlaces")) {
                achatPlaces(request, response);
            } else {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (DAOException e) {
            throw new RuntimeException(e);
            // request.setAttribute("erreurMessage", e.getMessage());
            // getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (NullPointerException e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_FOUND);
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
            throw new RuntimeException(e);
            // request.setAttribute("erreurMessage", e.getMessage());
            // getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        }

    }

    private void FormulaireCreerUnCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {

        ClientDAO clientDAO = new ClientDAO(ds);
        int bool = clientDAO.checkUtilisateurInexistant(request.getParameter("username"));
        if (bool < 1) {
            Client client = new Client(
                    (String) request.getParameter("username"),
                    Client.chiffrerMotDePasse((String) request.getParameter("passwd")),
                    (String) request.getParameter("nom"),
                    (String) request.getParameter("prenom"),
                    (String) request.getParameter("email"));
            clientDAO.creer(client);
            FlashImpl fl = new FlashImpl("Bienvenue, " + client.getPrenom() + ". Votre compte a bien été créé.", request, "success");
            request.getSession().setAttribute("LoggedIn", true);
            request.getSession().setAttribute("Login", client.getLogin());
            request.getSession().setAttribute("FailedLogIn", false);
            RepresentationDAO repDAO = new RepresentationDAO(ds);
            request.setAttribute("representations", repDAO.getRepresentationsAVenir());
            request.setAttribute("titre", "Mes billets en ligne");
            getServletContext().getRequestDispatcher("/WEB-INF/indexAll.jsp").forward(request, response);
        } else {
            FlashImpl fl = new FlashImpl("Login déjà existant. Veuillez en choisir un autre.", request, "error");
            getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(request, response);
        }

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

        // on regarde ses places achetées et réservées
        ReservationDAO resDAO = new ReservationDAO(ds);
        AchatDAO achDAO = new AchatDAO(ds);

        //on supprime les réservations qui sont périmées
        resDAO.supprimerReservationsNonPayees();
        List<Reservation> listRes = resDAO.getListeReservationsClient(login);

        // On complète les champs de classe
        for (Reservation cur : listRes) {
            RepresentationDAO repDAO = new RepresentationDAO(ds);
            Representation rep = new Representation(cur.getNoSpectacle(),
                    cur.getNoRepresentation(),false);

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

    private void goToStats(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        request.setAttribute("titre", "Stats");
        StatsControleur.remplirRequeteDeStats(ds, request, response);
        getServletContext().getRequestDispatcher("/WEB-INF/stats.jsp").forward(request, response);
    }
    private void goToAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
    request.setAttribute("titre", "Admin");
    StatsControleur.remplirRequeteDeStats(ds, request, response);
    getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    private void cancelPlaces(HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException, ServletException {

        String login = request.getParameter("login");
        Integer noS = Integer.parseInt(request.getParameter("noSpectacle"));
        Integer noR = Integer.parseInt(request.getParameter("noRepresentation"));
        Integer noZ = Integer.parseInt(request.getParameter("noZone"));
        Integer noRang = Integer.parseInt(request.getParameter("noRang"));
        Integer noP = Integer.parseInt(request.getParameter("noPlace"));

        Reservation resa = new Reservation(login, noS, noR, noZ, noRang, noP);
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
        AchatDAO aDAO = new AchatDAO(ds);
        Achat a = new Achat((String) request.getSession().getAttribute("Login"),
                Integer.parseInt(request.getParameter("noSpectacle")),
                Integer.parseInt(request.getParameter("noRepresentation")),
                Integer.parseInt(request.getParameter("noZone")),
                Integer.parseInt(request.getParameter("noRang")),
                Integer.parseInt(request.getParameter("noPlace")));
        aDAO.lire(a);
        
        String nomS = a.getRepresentation().getSpectacle().getNom();
        String image = a.getRepresentation().getSpectacle().getImage();
        String imageUrl = new URL(request.getScheme(), request.getServerName(), 
        request.getServerPort(), request.getContextPath() + "/images/" + image).toString();
        String date = a.getDateAchat(null);
        String prix = a.getPlace().getZone().getTarifBase().toString();
        String place = "" + a.getPlace().getNoPlace();
        String rang = "" + a.getPlace().getNoRang();
        String zone = a.getPlace().getZone().getCategorie();
        String numero = "" + a.getNoSerie();


        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    response.getOutputStream()); // Code 2
            addMetaData(document);

            document.open();
            //for barcode generation
            PdfContentByte cb = writer.getDirectContent();

            Paragraph preface = new Paragraph();
            preface.add(new Paragraph("MesBillets.com", itaFont));
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Voici votre e-billet", titreFont));
            addEmptyLine(preface, 2);


            Image img = Image.getInstance(new URL(imageUrl));
            img.scaleAbsolute(45, 55);
            img.setAbsolutePosition(60, 180);

            Paragraph corps = new Paragraph();
            corps.setIndentationLeft(80);
            corps.add(new Paragraph(nomS, grasFont));
            corps.add(new Paragraph("Le " + date));
            corps.add(new Paragraph(prix + " €", grasFont));

            BarcodeEAN codeEAN = new BarcodeEAN();
            String innerCode;
            long randomNum;
            randomNum = (long) (Math.random() * (3999999999999L - 3000000000000L)) + 3000000000000L;
            innerCode = String.valueOf(randomNum);
            codeEAN.setCode(innerCode);

            Image code = codeEAN.createImageWithBarcode(cb, null, null);
            code.scalePercent(150);
            code.setAbsolutePosition(280, 90);
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
        document.addAuthor("DreamTeam ACVL");
        document.addCreator("DreamTeam ACVL");
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private void achatPlaces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        Panier panier = new Panier(request.getSession(), ds);
        panier.vider();
        PlaceDAO pDAO = new PlaceDAO(ds);
        Place place = new Place(Integer.parseInt(request.getParameter("noPlace")),
                Integer.parseInt(request.getParameter("noRang")),
                Integer.parseInt(request.getParameter("noZone")));
        pDAO.lire(place);
        panier.ajouterPlace(place);
        RepresentationDAO repDAO = new RepresentationDAO(ds);
        Representation rep = new Representation(
                Integer.parseInt(request.getParameter("noSpectacle")),
                Integer.parseInt(request.getParameter("noRepresentation")),false);
        panier.setRepresentation(rep);
        request.setAttribute("panier", new Panier(request.getSession(), ds));
        request.setAttribute("resAsupprimer", "1");
        getServletContext().getRequestDispatcher("/WEB-INF/payer.jsp").forward(request, response);
    }
}
