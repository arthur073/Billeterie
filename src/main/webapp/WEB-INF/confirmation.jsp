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

<h3 align="center" class="titre"> Votre spectacle </h3>
<br/>

<div class="confirmation">
    <table>
        <tr>
            <td>
                <img class="reserverImg" src="images/${Image}"/>
            </td>
            <td>
                <h3> ${NomSpectacle}</h3>
                <h3> Le ${Date}</h3>
            </td>
        </tr>
    </table>
</div>


<br/>
<h3 align="center" class="titre"> Vos places </h3>


<table class="indexTable">
    <tr>
        <th>Place</th>
        <th>Prix</th>
    </tr>
<c:forEach items="${map.entrySet()}" var="tuple" >
    
        
        <c:forEach items="${tuple.value}" var="p">
            <tr>
                <td>${p} (${tuple.key.categorie}) </td>
                <td>${tuple.key.tarifBase}</td>
            </tr>
    </c:forEach>
         </td>
</tr> </c:forEach>
</table>

<br/>

<form action="ReservationControleur"  class="reserverFormCentre" method="post">
     <input type="text" name="map" style="display:none;" value="${map}" />
     <input type="submit" name="action" label="reserverPlaces" value="Reserver mes places" class="btnReserverCentre"/>
</form>

<c:import url="Layout/footer.jsp"/>

