<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="modele.Zone"%>
<%@page import="modele.Zone"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

Vous avez choisi la représentation suivante : <br/>
    <table class="reserver">
        <tr>
            <td>
                <img class="reserverImg" src="images/${rep.spectacle.image}"/>
            </td>
            <td>
                <h3>${rep.spectacle.nom}</h3>
                <h3>Le ${rep.getDate(null)}</h3>
            </td>
            <td>
                <form action="ReservationControleur"  class="reserverForm" method="post">
                    <h4> Choisissez vos places : </h4> 
                    <input type="hidden" name="NoSpectacle" value="${rep.noSpectacle}" />
                    <input type="hidden" name="NoRepresentation" value="${rep.noRepresentation}" />
                        <% 
                            List<Zone> listeCateg = (List<Zone>)request.getAttribute("listeZones");
                            request.setAttribute("listeZones",listeCateg);
                            for(Zone zone:listeCateg) {
                              %>
                                <label><%=zone.getCategorie() %></label>
                                <span class="price"> <%= zone.getTarifBase() %>&nbsp;&euro;</span><br/> 
                                <% 
                            }
                        %> 
                    <input type="submit" name="action" value="Choisir mes places" class="btnReserver"/> 
                </form>
            </td>
        </tr>
    </table>
        
<c:if test="${not empty representations}">
    Le spectacle ${rep.spectacle.nom} est aussi disponible aux dates suivantes : <br/><br/>
        
    <c:forEach items="${representations}" var="rep">
        <div class="reserverSmall">
            <table>
                <tr>
                    <td><img alt="Affiche pour ${rep.spectacle.nom}" class="reserverImgSmall" src="images/${rep.spectacle.image}"/></td>
                    <td><h3> Le  ${rep.getDate(null)}</h3></td>
                    <td>
                        <form action="ReservationControleur" method="post">
                            <input type="hidden" name="NoSpectacle" value="${rep.noSpectacle}" />
                            <input type="hidden" name="NoRepresentation" value="${rep.noRepresentation}" />
                            <c:choose>
                                <c:when test="${rep.annule == 1}">
                                    <input type="submit" name="action" value="Annulé" class="btnRed" disabled="disabled"/>
                                </c:when>    
                                <c:otherwise>
                                    <input type="submit" name="action" value="Reserver" class="btnBlack btnReserverSmall"/>
                                </c:otherwise>
                            </c:choose>
                            
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </c:forEach> 
</c:if>
    
    
                        <c:import url="Layout/footer.jsp"/>
                        
