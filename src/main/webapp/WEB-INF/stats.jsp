<%-- 
Document   : Statistiques en HTML, à inclure dans une page complète
Created on : 21 avr. 2013, 11:34:17
Author     : Jany
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Statistiques globales sur la période</h2>
<table>
    <tr><th>Bénéfice total</th><td>${benefTotal}</td></tr>
    <tr><th>Nombre de places vendues</th><td>${totalPlacesVendues}</td></tr>
</table>

<h2>Meilleurs spectacles</h2>

<table>
    <tr><th colspan="2">Remplissage</th><th colspan="2">Rentabilité</th></tr>
    <% List<InfoRenta> mieuxRemplis = request.getSession().getAttribute("mieuxRemplis");
    List<InfoRenta> plusRentables = request.getSession().getAttribute("plusRentables");
    InfoRenta info;
    while (mieuxRemplis.hasNext() || plusRentables.hasNext()) {
        out.println("<tr>");
        if (mieuxRemplis.hasNext()) {
            info = mieuxRemplis.next();
            out.println("<th>" + info.spectacle.getNom() +
                "</th><td>" + info.nbPlacesVendues +
                " (" + info.tauxRemplissage + ")</td></th>");
        } else {
            out.println("<td colspan=\"2\" class=\"invisible\"></td");
        }
        if (plusRentables.hasNext()) {
            info = plusRentables.next();
            out.println("<th>" + info.spectacle.getNom() +
                "</th><td>" + info.benefTotal + "</td></th>");
        } else {
            out.println("<td colspan=\"2\" class=\"invisible\"></td");
        }
        out.println("</tr>\n");
    } %>
</table>

<h2>Tous les spectacles</h2>

<table>
    <c:foreach >

