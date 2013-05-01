<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>


<table class="indexTable">
    <tr>
        <th> </th>
        <th>Spectacle</th>
        <th>Date</th>
        <th> </th>
    </tr>
    <c:forEach items="${representations}" var="rep">
        <tr>
            <td><img height="80"  src="images/${rep.spectacle.image}" class="shadowImg"/></td>
            <td>${rep.spectacle.nom}</td>
            <td>${rep.getDate(null)}</td>
            <td> 
                <form action="ReservationControleur" method="post" >
                    <input type="text" name="Date" value="${rep.getDate(null)}" style="display:none"/>
                    <input type="text" name="Image" value="${rep.spectacle.image}" style="display:none"/>
                    <input type="text" name="NomSpectacle" value="${rep.spectacle.nom}" style="display:none"/>
                    <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                    <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                    <input type="text" name="Annule" value="${rep.annule}" style="display:none"/>
                    <%  Boolean loggedAdmin = (Boolean) request.getSession().getAttribute("Admin");
                        if (loggedAdmin != null && loggedAdmin) {%>
                           <c:choose>
                            <c:when test="${rep.getAnnule() == 1}">
                                <input type="submit" name="action" value="Annulé" class="btnRed" disabled="disabled"/>
                            </c:when>    
                            <c:otherwise>
                               <input type="submit" name="action" value="Annuler" class="btnBlack"/>
                            </c:otherwise>
                        </c:choose>
                    <% } else { %>
                        <c:choose>
                            <c:when test="${rep.isDateLessThanAnHour()}">
                                <input type="submit" name="action" value="Indisponible" class="btnBlack" disabled="disabled"/>
                            </c:when>
                            <c:when test="${rep.getAnnule() == 1}">
                                <input type="submit" name="action" value="Annulé" class="btnRed" disabled="disabled"/>
                            </c:when>    
                            <c:otherwise>
                                <input type="submit" name="action" value="Réserver" class="btnBlack"/>
                            </c:otherwise>
                        </c:choose>
                    <% } %>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

