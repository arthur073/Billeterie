<%-- 
    Document   : monCompte
    Created on : 16 avr. 2013, 13:05:22
    Author     : arthur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<strong> Mes informations </strong>
<table class="infoCompte">
<tr>
    <td> Login : </td>
    <td><strong> ${login} </strong></td>
</tr>
<tr>
    <td> Nom : </td>
    <td><strong> ${nom} </strong></td>
</tr>
<tr>
    <td> Pr�nom : </td>
    <td> <strong> ${prenom} </strong> </td>
</tr>
<tr>
    <td> Adresse �lectronique : </td>
    <td><strong> ${email} </strong> </td>
</tr>
</table>

Mes places : ${listAchatSuiv}<br/>
R�serv�es : ${listRep} <br/>
Achet�es : ${listAchatPrec} <br/>


<c:import url="Layout/footer.jsp"/>

