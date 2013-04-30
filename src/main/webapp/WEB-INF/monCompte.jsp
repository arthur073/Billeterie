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

            </th>
            <th>
                Spectacle
            </th>
            <th>
                Date Spectacle
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
                    <img height="80px"  src="images/${achat.representation.spectacle.image}" class="shadowImg"/>   
                </td>
                <td>
                    ${achat.representation.spectacle.nom}
                </td>
                <td>
                    ${achat.representation.getDate(null)}
                </td>
                <td>
                    ${achat.getDateAchat(null)}
                </td>
                <td>
                    ${achat.place.zone.tarifBase} &euro;
                </td>
                <td>
                    <a href="UtilisateursControleur?action=imprPlaces&nomS=${achat.representation.spectacle.nom}&image=${achat.representation.spectacle.image}&date=${achat.representation.getDate(null)}&prix=${achat.place.zone.tarifBase}&numero=${achat.noSerie}&place=${achat.noPlace}&rang=${achat.noRang}&zone=${achat.nomZone}" target="_blank" class="btnBlack">Imprimer</a>
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
        <c:forEach items="${listRes}" var="achat">
            <tr>
                <td>
                    <img height="80px"  src="images/${achat.representation.spectacle.image}" class="shadowImg"/>   
                <td>
                    ${achat.representation.spectacle.nom}
                </td>
                <td>
                    ${achat.representation.getDate(null)}
                </td>
                <td>
                    ${achat.place.zone.tarifBase} &euro;
                </td>
                <td>
                    <a href="UtilisateursControleur?action=achatPlaces&login=${login}&noSpectacle=${achat.noSpectacle}&noRepresentation=${achat.noRepresentation}&noZ=${achat.noZone}&noRang=${achat.noRang}&noP=${achat.noPlace}&prixTotal=${achat.tarifBase}&places=${places}resAsupprimer=1" class="btnBlack widthFixed" >Payer</a>
                    <br/><br/>
                    <a href="UtilisateursControleur?action=annulerPlaces&login=${login}&noS=${achat.noSpectacle}&noR=${achat.noRepresentation}&noZ=${achat.noZone}&noRang=${achat.noRang}&noP=${achat.noPlace}&tarif=${achat.tarifBase}" class="btnBlack" onclick="return confirm('Êtes-vous sûr de vouloir supprimer la réservation ?')">Annuler</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>


<c:import url="Layout/footer.jsp"/>

