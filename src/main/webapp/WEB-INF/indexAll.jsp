<%@page import="java.util.Map"%>
<%@page import="modele.Representation"%>
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
        <% if (((Representation) pageContext.getAttribute("rep")).getAnnule()) { %>
        <tr class="trAnnule">
        <% } else { %>
        <tr>
        <% } %>
            <td><img height="80"  src="images/${rep.spectacle.image}" class="shadowImg"/></td>
            <td>${rep.spectacle.nom}</td>
            <td><%= ((Representation) pageContext.getAttribute("rep")).getDate(null) %></td>
            <td> 
                <form action="ReservationControleur" method="post" >
                    <input type="hidden" name="NoSpectacle" value="${rep.noSpectacle}" />
                    <input type="hidden" name="NoRepresentation" value="${rep.noRepresentation}" />
                    <%  Boolean loggedAdmin = (Boolean) request.getSession().getAttribute("Admin");
                        if (loggedAdmin != null && loggedAdmin) {%>
                           <c:choose>
                            <c:when test="${rep.annule}">
                                <input type="submit" name="action" value="Annul�" class="btnBlack Annule" disabled="disabled"/>
                            </c:when>    
                            <c:otherwise>
                               <input type="submit" name="action" value="Annuler" class="btnBlack"/>
                            </c:otherwise>
                        </c:choose>
                    <% } else { 
                    Representation rep = (Representation) pageContext.getAttribute("rep");
                    %>
                        <c:choose>
                            <c:when test="<%=
                                    ((Representation) pageContext.getAttribute(\"rep\")).isDateLessThanAnHour() || (
                            ((Map<Representation, Integer>) request.getAttribute(\"placesRestantes\")).get(pageContext.getAttribute(\"rep\")) != null
                            && ((Map<Representation, Integer>) request.getAttribute(\"placesRestantes\")).get(pageContext.getAttribute(\"rep\")) <= 70) %>">
                                <input type="submit" name="action" value="Indisponible" class="btnBlack" disabled="disabled"/>
                            </c:when>
                            <c:when test="${rep.annule}">
                                <input type="submit" name="action" value="Annul�" class="btnBlack Annule" disabled="disabled"/>
                            </c:when>    
                            <c:otherwise>
                                <input type="submit" name="action" value="Reserver" class="btnBlack"/>
                            </c:otherwise>
                        </c:choose>
                    <% } %>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

