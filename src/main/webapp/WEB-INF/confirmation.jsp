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

<% ArrayList<Place> PlacesPoulailler = new ArrayList<Place>(); %> 
<% ArrayList<Place> PlacesOrchestre = new ArrayList<Place>(); %> 
<% ArrayList<Place> PlacesBalcon = new ArrayList<Place>(); %> 
<% int noP, noR, noZ; %>
<% String[] tmp; %>
<% request.getParameter("NomSpectacle"); %>
<% String[] strArray = pl.split("!"); %>
<% for (String el : strArray) { %>
    <% tmp = el.split(" "); %>
    <% noP = Integer.parseInt(tmp[0]); %>
    <% noR = Integer.parseInt(tmp[1]); %>
    <% noZ = Integer.parseInt(tmp[2]); %>
    <% if (noZ == 1) { %>
        <% PlacesPoulailler.add(new Place(noP,noR,noZ)); %>
    <% } else if (noZ == 2) { %>
        <% PlacesOrchestre.add(new Place(noP,noR,noZ)); %>
    <% } else if (noZ == 3) { %>
        <% PlacesBalcon.add(new Place(noP,noR,noZ)); %>
    <% } %>
<% } %>

Vos places Poulailler:
<br/>
<% for (Place p : PlacesPoulailler) { %>
<%= p.toString() %>
<br/>
<% } %>
<br/>
Vos places Orchestre:
<br/>
<% for (Place p : PlacesOrchestre) { %>
<%= p.toString() %>
<br/>
<% } %>

<br/>
Vos places Balcon:
<br/>
<% for (Place p : PlacesBalcon) { %>
<%= p.toString() %>
<br/>
<% } %>

<br/>


<c:import url="Layout/footer.jsp"/>

