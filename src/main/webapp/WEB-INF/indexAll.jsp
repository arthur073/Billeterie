<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>

      Liste des spectacles : <br/>
      
        <table>
            <tr>
                <th>Nom</th>
                <th>Date</th>
                <th>&nbsp;</th>
            </tr>
            aa${representations}aa
            <c:forEach items="${representations}" var="rep">
                <tr>
                    <td>${rep.auteur}</td><td>${rep.titre}</td>
                    <td><a href="RepresentationsControleur?action=getRepresentation&view=modifier&id=${rep.id}"/>modifier</td>
                    <td><a href="RepresentationsControleur?action=getRepresentation&view=supprimer&id=${rep.id}">supprimer</a></td>
                </tr>
            </c:forEach>
        </table>
     
     
 <c:import url="Layout/footer.jsp"/>

