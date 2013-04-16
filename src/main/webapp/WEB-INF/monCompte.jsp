<%-- 
    Document   : monCompte
    Created on : 16 avr. 2013, 13:05:22
    Author     : arthur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    


<div class="infoCompte">
    Mes informations : <br/>
    <span> Login : </span><strong> ${login} </strong><br/>
    <span> Nom : </span><strong> ${nom} </strong><br/>
    <span> Prénom : </span> <strong> ${prenom} </strong> <br/>
    <span> Adresse électronique : </span><strong> ${email} </strong> <br/>
</div>

Mes places : <br/>
Réservées : <br/>
Achetées : <br/>


<c:import url="Layout/footer.jsp"/>

