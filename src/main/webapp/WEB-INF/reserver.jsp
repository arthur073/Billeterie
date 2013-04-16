<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="modele.Zone"%>
<%@page import="modele.Zone"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h2 align="center" class="titre"> Réservation de billets </h2>

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
                    <input type="text" label="NoSpectacle" style="display:none;" value="<%= request.getParameter("NoSpectacle")%>" />
                    <input type="text" label="NoRepresentation" style="display:none;" value="<%= request.getParameter("NoSpectacle")%>" />
                        <% 
                             Set<Map.Entry<Zone, Float>> listeCateg = (Set<Map.Entry<Zone, Float>>) request.getAttribute("typePlaces");
                            Iterator<Map.Entry<Zone, Float> > i = listeCateg.iterator();
                            Entry<Zone, Float> e;
                            do {
                                e = i.next(); %>
                                <label><%= e.getKey().getCategorie() %></label>
                                <span class="price"> 
                                <%= e.getValue()%> &euro;</span><br/> 
                                <%
                                
                                
                            }
                            while (i.hasNext() );

                        %>                            
                    <input type="submit" name="action" label="reserverPlaces" value="Choisir mes places" class="btnReserver"/> 
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
                    <h3> Le  ${rep.date}</h3>
                </td>
                <td>
                    <form action="ReservationControleur" method="post">
                        <input type="text" name="Date" value="${rep.date}" style="display:none"/>
                        <input type="text" name="Image" value="${rep.spectacle.image}" style="display:none"/>
                        <input type="text" name="NomSpectacle" value="${rep.spectacle.nom}" style="display:none"/>
                        <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                        <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                        <input type="submit" name="action" label="reserver" value="Reserver" class="btnBlack btnReserverSmall"/>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</c:forEach>


<c:import url="Layout/footer.jsp"/>

