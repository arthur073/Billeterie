<%@ page contentType="text/html; charset=utf-8"%>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <title>Mes Billets</title>
    </head>

    <body>
        <% if (request.getRequestURI().equalsIgnoreCase("/billeterie/WEB-INF/indexAll.jsp")) {%>
        <% Boolean logged = (Boolean) request.getSession().getAttribute("LoggedIn");%>
        <% if (logged) {%>
        <a href="PagesControleur?action=goToLogOut" class="login"/>Me déconnecter</a>
        <% } else {%>
    <a href="PagesControleur?action=goToLogin" class="login"/>Me connecter</a>
    <% }%>
    <% } else {%>
<a href="PagesControleur" class="login"/>Accueil</a>
<% } %>

<h2 class="header"> MesBillets.com</h2>
<h2 align="center" class="titre"> Mes billets en ligne </h2>


<ul class="menu">    
    <li> <a href="#" class="current">Bonjour</a> </li>
    <li> <a href="#">Bonjour</a> </li>
    <li> <a href="#" class="last">Bonjour</a> </li>
</ul>

<c:if test="${!flash.emptyMessage}" >
    <div class="flash ${flash.klass}"><strong> ${flash.start} </strong> ${flash.message}</div>
</c:if>



