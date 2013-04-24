<%-- 
    Document   : monCompte
    Created on : 16 avr. 2013, 13:05:22
    Author     : arthur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:import url="Layout/header.jsp"/>  

<strong> Mes informations </strong>
<table class="infoCompte">
    <tr>
        <td> Login : </td>
        <td><strong> ${login} </strong></td>
    </tr>
    <tr>
        <td> Nom : </td>
        <td><strong> ${nom} </strong></td>
    </tr>
    <tr>
        <td> Prénom : </td>
        <td> <strong> ${prenom} </strong> </td>
    </tr>
    <tr>
        <td> Adresse électronique : </td>
        <td><strong> ${email} </strong> </td>
    </tr>
</table>

<c:if test="${not empty listAchatSuiv}">
<strong> Mes places achetées </strong>
<table class="indexTable">
    <tr>
        <th>
            No dossier
        </th>
        <th>
            No Billet
        </th>
        <th>
            Date Achat
        </th>
        <th>
            Prix
        </th>
        <th>

        </th>
    </tr>
    <c:forEach items="${listAchatSuiv}" var="achat">
        <tr>
            <td>
                ${achat.noDossier}
            </td>
            <td>
                ${achat.noSerie}
            </td>
            <td>
                Le <fmt:formatDate value="${achat.dateAchat}" pattern="dd-MM-yyyy à HH:mm" />
            </td>
            <td>
                ${achat.place.zone.tarifBase} &euro;
            </td>
            <td>
                <a href="UtilisateursControleur?action=imprPlaces" target="_blank" class="btnBlack">Imprimer</a>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>




<br/>

<c:if test="${not empty listRes}">
<strong> Mes places réservées </strong>

<table class="indexTable">
    <tr>
        <th>

        </th>
        <th>
            Spectacle
        </th>
        <th>
            Date
        </th>
        <th>
            Prix
        </th>
        <th>

        </th>
    </tr>
    <c:forEach items="${listRes}" var="resa">
        <tr>
            <td>
                <img height="80px"  src="images/${resa.representation.spectacle.image}"/>   
            <td>
                ${resa.representation.spectacle.nom}
            </td>
            <td>
                Le <fmt:formatDate value="${resa.representation.date}" pattern="dd-MM-yyyy à HH:mm" />
            </td>
            <td>
                ${resa.place.zone.tarifBase} &euro;
            </td>
            <td>
                <a href="UtilisateursControleur?action=annulerPlaces&login=${login}&noS=${resa.noSpectacle}&noR=${resa.noRepresentation}&noZ=${resa.noZone}&noRang=${resa.noRang}&noP=${resa.noPlace}&tarif=${resa.tarifBase}" class="btnBlack" onclick="return confirm('Êtes-vous sûr de vouloir supprimer la réservation ?')">Annuler</a>
            </td>
        </tr>
    </c:forEach>
</table>
</c:if>


<c:import url="Layout/footer.jsp"/>

