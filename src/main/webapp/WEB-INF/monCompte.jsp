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
    <span> Pr�nom : </span> <strong> ${prenom} </strong> <br/>
    <span> Adresse �lectronique : </span><strong> ${email} </strong> <br/>
</div>

Mes places : <br/>
R�serv�es : <br/>
Achet�es : <br/>


<c:import url="Layout/footer.jsp"/>

