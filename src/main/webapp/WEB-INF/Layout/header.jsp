<%@ page contentType="text/html; charset=utf-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
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
<% }%>

<h2 align="center" class="header"> MesBillets.com</h2>



