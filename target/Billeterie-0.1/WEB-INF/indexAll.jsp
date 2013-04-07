<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>

      Ceci est ma première application JSP-MVC ! 


     <%--  <p>
            <a href="ajouter.xhtml">Ajouter une référence bibliographique</a>
        </p> 

        <table>
            <tr>
                <th>Auteur</th>
                <th>Titre</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${ouvrages}" var="ouvrage">
                <tr>
                    <td>${ouvrage.auteur}</td><td>${ouvrage.titre}</td>
                    <td><a href="controleur?action=getOuvrage&view=modifier&id=${ouvrage.id}"/>modifier</td>
                    <td><a href="controleur?action=getOuvrage&view=supprimer&id=${ouvrage.id}">supprimer</a></td>
                </tr>
            </c:forEach>
        </table>
     
     --%>
     
 <c:import url="Layout/footer.jsp"/>

