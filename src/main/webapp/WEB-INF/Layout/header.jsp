<%@ page contentType="text/html; charset=utf-8"%>
<html>
    <head>
       <link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    
    <body>
        <% if(request.getRequestURI().equalsIgnoreCase("/billeterie/WEB-INF/indexAll.jsp")) { %>
        <a href="PagesControleur?action=goToLogin" class="login"/>Me connecter</a>
        <% } else { %>
        <a href="PagesControleur" class="login"/>Accueil</a>
        <% } %>
    

        <h2 align="center" class="header"> Mes billets en ligne </h2>
        
