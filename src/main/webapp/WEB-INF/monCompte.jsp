<%-- 
    Document   : monCompte
    Created on : 16 avr. 2013, 13:05:22
    Author     : arthur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <td> Pr�nom : </td>
        <td> <strong> ${prenom} </strong> </td>
    </tr>
    <tr>
        <td> Adresse �lectronique : </td>
        <td><strong> ${email} </strong> </td>
    </tr>
</table>

<strong> Mes places achet�es </strong>

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
                ${achat.dateAchat}
            </td>
            <td>
                ${achat.tarifBase} &euro;
            </td>
            <td>
                <a href="#" class="btnBlack">Annuler</a>
            </td>
        </tr>
    </c:forEach>

    <c:forEach items="${listAchatPrec}" var="achat">

        <tr>
            <td>
                ${achat.noDossier}
            </td>
            <td>
                ${achat.noSerie}
            </td>
            <td>
                ${achat.dateAchat}
            </td>
            <td>
                ${achat.tarifBase} &euro;
            </td>
            <td>

            </td>
        </tr>
    </c:forEach>

</table>


R�serv�es mais non pay�es : ${listRes} <br/>


<strong> Mes places r�serv�es </strong>

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
                ${resa.representation.spectacle.image}
            </td>
            <td>
                ${resa.representation.spectacle.nomSpectacle}
            </td>
            <td>
                ${resa.representation.date}
            </td>
            <td>
                ${resa.tarifBase} &euro;
            </td>
            <td>
                <a href="#" class="btnBlack">Annuler</a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:import url="Layout/footer.jsp"/>

