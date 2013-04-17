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

<table>
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
    </tr>
    <tr>
        <c:forEach items="${listAchatSuiv}" var="achat">
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
               ${achat.dateAchat} 
            </td>

        </c:forEach>
    </tr>
</table>
Liste achats � venir : ${listAchatSuiv}<br/>
R�serv�es mais non pay�es : ${listRep} <br/>
Liste achat pass�s : ${listAchatPrec} <br/>


<c:import url="Layout/footer.jsp"/>

