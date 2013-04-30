<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" type="text/css" href="style.css" />
        <script src="jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="jquery-ui.min.js" type="text/javascript"></script>
        <title>MesBillets.com</title>

        <script type="text/javascript">
            function getSelected() {
            <% if (request.getRequestURI().toString().equals("/billeterie/WEB-INF/indexAll.jsp")) {%>
                document.getElementById("li1").className = " current";
            <% } else if (request.getRequestURI().toString().equals("/billeterie/WEB-INF/about.jsp")) {%>
                document.getElementById("li3").className = " current";
            <% } else if (request.getRequestURI().toString().equals("/billeterie/WEB-INF/monCompte.jsp")) {%>
                document.getElementById("li2").className = " current";
            <% } else if (request.getRequestURI().toString().equals("/billeterie/WEB-INF/admin.jsp")) {%>
                document.getElementById("li4").className = " current";
            <% }%>
            }
        </script>
    </head>



    <body onload="getSelected();">
        <h2 class="header"> MesBillets.com</h2>


        <% Boolean logged = (Boolean) request.getSession().getAttribute("LoggedIn");
            if (logged != null && logged) {%>
        <a href="PagesControleur?action=goToLogOut" class="login">Me déconnecter</a>
        <% } else {%>
    <a href="PagesControleur?action=goToLogin&from=indexAll" class="login">Me connecter</a>
    <% }%>


<% if (!request.getRequestURI().equalsIgnoreCase("/billeterie/WEB-INF/indexAll.jsp")) {%>
<a href="PagesControleur" class="login"/>Accueil</a>
<% }%>

<h2 style="padding-left: 37%" > ${titre} </h2>



<ul class="menu">    
    <li> <a href="PagesControleur" id="li1">Les spectacles</a> </li>
    <li> <a href="UtilisateursControleur?action=goToMyAccount" id="li2">Mon compte</a> </li>
    <li> <a href="PagesControleur?action=goToAbout" id="li3">A propos</a> </li>
        <%  Boolean loggedAdmin = (Boolean) request.getSession().getAttribute("Admin");
            logged = (Boolean) request.getSession().getAttribute("LoggedIn");
            if (logged != null && logged) {
                if (loggedAdmin != null && loggedAdmin) {%>
    <li> <a href="UtilisateursControleur?action=goToAdmin" id="li4">Admin</a> </li>
        <% }
            }%>
</ul>

<c:if test="${!flash.emptyMessage}" >
    <div class="flash ${flash.klass}"><strong> ${flash.start} </strong> ${flash.message}</div>
</c:if>



