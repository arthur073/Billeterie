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
    Pr�nom : ${prenom}<br/>
    Adresse �lectronique : ${email}<br/>
</div>

Mes places : <br/>
R�serv�es : <br/>
Achet�es : <br/>


<c:import url="Layout/footer.jsp"/>

