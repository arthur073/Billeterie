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
<% String places = request.getParameter("places").replaceAll("/", " "); %>
<%= places %>
<br/><br/>

<% ArrayList<Place> PlacesPoulailler = new ArrayList<Place>(); %> 
<% ArrayList<Place> PlacesOrchestre = new ArrayList<Place>(); %> 
<% ArrayList<Place> PlacesBalcon = new ArrayList<Place>(); %> 
<% ArrayList<Place> PlacesLoge = new ArrayList<Place>(); %> 
<% TraitementPlaces tp = new TraitementPlaces(); %>
<% tp.TraiterPlaces(places, PlacesPoulailler, PlacesOrchestre, PlacesBalcon, PlacesLoge); %>

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

<br/>
Vos places Loge:
<br/>
<% for (Place p : PlacesLoge) { %>
<%= p.toString() %>
<br/>
<% } %>
<br/>


Recapitulatif de votre facture:

<br/>
<c:import url="Layout/footer.jsp"/>

