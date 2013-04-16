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
<% String pl = request.getParameter("places").replaceAll("/", " "); %>
<%= pl %>
<br/><br/>

<% ArrayList<Place> Places = new ArrayList<Place>(); %> 
<% int noP, noR, noZ; %>
<% String[] tmp; %>
<% request.getParameter("NomSpectacle"); %>
<% String[] strArray = pl.split("!"); %>
<% for (String el : strArray) { %>
    <% tmp = el.split(" "); %>
    <% noP = Integer.parseInt(tmp[0]); %>
    <% noR = Integer.parseInt(tmp[1]); %>
    <% noZ = Integer.parseInt(tmp[2]); %>
    <% Places.add(new Place(noP,noR,noZ)); %>
    <%= el %>
    <br/>
<% } %>



<c:import url="Layout/footer.jsp"/>

