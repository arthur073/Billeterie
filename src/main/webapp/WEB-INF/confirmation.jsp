<%@page import="java.awt.Font"%>
<%@page import="java.awt.GraphicsEnvironment"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h2 align="center" class="titre"> Confirmation de la r�servation </h2>

R�capitulatif de la commande : <br/>

Vous avez selectionn� les places : 
<% String pl = request.getParameter("places").replaceAll("/", " "); %>
<%= pl %>

<% request.getParameter("NomSpectacle"); %>


<c:import url="Layout/footer.jsp"/>

