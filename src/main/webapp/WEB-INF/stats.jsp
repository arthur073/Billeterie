<%-- 
Document   : Statistiques en HTML, à inclure dans une page complète
Created on : 21 avr. 2013, 11:34:17
Author     : Jany
--%>
<%@page import="java.util.Iterator"%>
<%@page import="dao.StatsDAO.InfoRenta"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% if (request.getAttribute("erreurFormatageDate") != null) { %>
<p class="erreur">${erreurFormatageDate}</p>
<% } else { %>

<h2>Statistiques globales sur la période</h2>
<table id="stats-globales">
    <tr><th>Bénéfice total</th><td>${benefTotal}</td></tr>
    <tr><th>Nombre de places vendues</th><td>${totalPlacesVendues}</td></tr>
</table>

<h2>Meilleurs spectacles</h2>

<table id="stats-meilleurs">
    <tr><th colspan="2">Remplissage</th><th colspan="2">Rentabilité</th></tr>
    <%
    List<InfoRenta> mieuxRemplisListe = (List<InfoRenta>)request.getAttribute("mieuxRemplis");
    Iterator<InfoRenta> mieuxRemplis = mieuxRemplisListe.iterator();
    List<InfoRenta> plusRentablesListe = (List<InfoRenta>)request.getAttribute("plusRentables");
    Iterator<InfoRenta> plusRentables = plusRentablesListe.iterator();
    InfoRenta info;
    while (mieuxRemplis.hasNext() || plusRentables.hasNext()) {
        out.println("<tr>");
        if (mieuxRemplis.hasNext()) {
            info = mieuxRemplis.next();
            out.println("<td><strong>" + info.spectacle.getNom() +
                "</strong></th><td>" + info.nbPlacesVendues +
                " (" + info.tauxRemplissage + ")</td></th>");
        } else {
            out.println("<td colspan=\"2\" class=\"invisible\"></td");
        }
        if (plusRentables.hasNext()) {
            info = plusRentables.next();
            out.println("<td><strong>" + info.spectacle.getNom() +
                "</strong></td><td>" + info.benefTotal + "</td></th>");
        } else {
            out.println("<td colspan=\"2\" class=\"invisible\"></td");
        }
        out.println("</tr>\n");
    } %>
</table>

<h2>Tous les spectacles</h2>

<table id="stats-tous">
	<tr><th>Nom</th><th class="colonne-stats">Bénéfice total</th><th class="colonne-stats">Nombre de places vendues</th></tr>
	<c:forEach items="${statsSpectacles}" var="stat">
		<tr><td><strong>${stat.spectacle.nom}</strong></td><td>${stat.benefTotal}</td><td>${stat.nbPlacesVendues} (${stat.tauxRemplissage}&nbsp;%)</td></tr>
	</c:forEach>
</table>


<% } %>