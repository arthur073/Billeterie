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
            
            <c:if test="${achat.representation.annule}" >
                <tr class="trAnnule">
            </c:if>
            <c:if test="not ${achat.representation.annule}" >
                <tr>
            </c:if>
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

                    <c:choose>
                        <c:when test="${achat.representation.annule}">
                           <input type="submit" name="action" value="Annulé" class="btnBlack Annule" disabled="disabled"/>
                        </c:when>    
                        <c:otherwise>
                            <a href="UtilisateursControleur?action=imprPlaces&amp;noSpectacle=${achat.noSpectacle}&amp;noRepresentation=${achat.noRepresentation}&amp;noPlace=${achat.noPlace}&amp;noRang=${achat.noRang}&amp;noZone=${achat.noZone}&amp;" target="_blank" class="btnBlack">Imprimer</a>
                        </c:otherwise>
                    </c:choose>
                   
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
                    <c:choose>
                        <c:when test="${achat.representation.annule}">
                           <input type="submit" name="action" value="Annulé" class="btnRed" disabled="disabled"/>
                        </c:when>    
                        <c:otherwise>
                            <a href="UtilisateursControleur?action=achatPlaces&amp;noSpectacle=${achat.noSpectacle}&amp;noRepresentation=${achat.noRepresentation}&amp;noZone=${achat.noZone}&amp;noRang=${achat.noRang}&amp;noPlace=${achat.noPlace}&amp;resAsupprimer=1" class="btnBlack widthFixed" >Payer</a>
                            <br/><br/>
                            <a href="UtilisateursControleur?action=annulerPlaces&amp;noSpectacle=${achat.noSpectacle}&amp;noRepresentation=${achat.noRepresentation}&amp;noZone=${achat.noZone}&amp;noRang=${achat.noRang}&amp;noPlace=${achat.noPlace}" class="btnBlack" onclick="return confirm('Êtes-vous sûr de vouloir supprimer la réservation ?')">Annuler</a>
                        </c:otherwise>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>


<c:import url="Layout/footer.jsp"/>

