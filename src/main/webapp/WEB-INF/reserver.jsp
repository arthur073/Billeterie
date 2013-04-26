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
<div class="reserver" >
    <table>
        <tr>
            <td>
                <img class="reserverImg" src="images/<%= request.getParameter("Image")%>"/>
            </td>
            <td>
                <h3> <%= request.getParameter("NomSpectacle")%> </h3>
                <h3> Le  <%= request.getParameter("Date")%></h3>
            </td>
            <td>
                
                <form action="ReservationControleur"  class="reserverForm" method="post">
                    <h4> Choisissez vos places : </h4> 
                    <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
                    <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
                    <input type="text" name="listeZones" style="display:none;" value="${listeZones}" />
                    <input type="text" name="Date" value="${Date}" style="display:none;"/>
                    <input type="text" name="Image" value="${Image}" style="display:none;"/>
                    <input type="text" name="NomSpectacle" value="${NomSpectacle}" style="display:none;"/>
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
                       <input type="text" name="prix" style="display:none;" value="${prix}"/>  
                    <input type="submit" name="action" value="Choisir mes places" class="btnReserver"/> 
                </form>
            </td>
        </tr>
    </table>
</div>
<br/>

<c:if test="${not empty representations}">
    Le spectacle <%= request.getParameter("NomSpectacle")%> est aussi disponible aux dates suivantes : <br/><br/>
</c:if>

<c:forEach items="${representations}" var="rep">
    <div class="reserverSmall" >
        <table>
            <tr>
                <td>
                    <img class="reserverImgSmall" src="images/${rep.spectacle.image}"/>
                </td>
                
                <td>
                    <h3> Le  ${rep.getDate(null)}</h3>
                </td>
                <td>
                    <form action="ReservationControleur" method="post">
                        <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
                        <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
                        <input type="text" name="listeZones" style="display:none;" value="${listeZones}" />
                        <input type="text" name="Date" value="${Date}" style="display:none;"/>
                        <input type="text" name="Image" value="${Image}" style="display:none;"/>
                        <input type="text" name="NomSpectacle" value="${NomSpectacle}" style="display:none;"/>
                        <input type="text" name="prix" style="display:none;" value="${prix}"/>  
                        <input type="submit" name="action" value="Reserver" class="btnBlack btnReserverSmall"/>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</c:forEach>


<c:import url="Layout/footer.jsp"/>

