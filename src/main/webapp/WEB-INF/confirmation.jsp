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

<c:forEach items="${map.entrySet()}" var="tuple" >
    Vos place en zone ${tuple.key.categorie} :
    <c:forEach items="${tuple.value}" var="p">
        ${p}
    </c:forEach>
 </c:forEach>

<br/><br/>



Recapitulatif de votre facture:

<br/>
<c:import url="Layout/footer.jsp"/>

