<%-- 
    Document   : monCompte
    Created on : 16 avr. 2013, 13:05:22
    Author     : arthur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<div class="infoCompte">
    Mes informations : <br/>
    Login : ${login}<br/>
    Nom : ${nom}<br/>
    Prénom : ${prenom}<br/>
    Adresse électronique : ${email}<br/>
</div>

Mes places : <br/>
Réservées : <br/>
Achetées : <br/>


<c:import url="Layout/footer.jsp"/>

