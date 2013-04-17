<%@page import="java.lang.Integer"%>
<%@page import="modele.Zone"%>
<%@page import="java.util.List"%>
<%@page import="vue.TraitementPlaces"%>
<%@page import="modele.Place"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.GraphicsEnvironment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h2 align="center" class="titre"> Confirmation de la réservation </h2>

Récapitulatif de la commande : <br/>

Vous avez selectionné les places : 
${places}


<br/><br/>

Vos places Poulailler:
<c:forEach items="${PlacesPoulailler}" var="p">
    ${p}
</c:forEach>
<br/><br/>

Vos places Orchestre:
<c:forEach items="${PlacesOrchestre}" var="p">
    ${p}
</c:forEach>

<br/><br/>

Vos places Balcon:
<c:forEach items="${PlacesBalcon}" var="p">
    ${p}
</c:forEach>
<br/><br/>

Vos places Loge:
<c:forEach items="${PlacesLoge}" var="p">
    ${p}
</c:forEach>
<br/><br/>


Recapitulatif de votre facture:
<% 
    List<Zone> listeCateg = (List<Zone>)request.getAttribute("listeZones");
    for(Zone zone:listeCateg) {

      %>

        <label><%=zone.getCategorie() %></label>
        <span class="price"> <%= zone.getTarifBase() %>&nbsp;&euro;</span><br/> 
        <%
    }

 %> 
<br/>
<c:import url="Layout/footer.jsp"/>

