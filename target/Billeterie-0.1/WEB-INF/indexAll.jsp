<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>

      Liste des spectacles : <br/>
      
        <table>
            <tr>
                <th>Spectacle</th>
                <th>Date</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${representations}" var="rep">
                <tr>
                    <td>${rep.noSpectacle}</td><td>${rep.date}</td>
                 <%--   <td><a href="RepresentationsControleur?action=getRepresentation&view=modifier&id=${rep.id}"/>modifier</td>
                    <td><a href="RepresentationsControleur?action=getRepresentation&view=supprimer&id=${rep.id}">supprimer</a></td>
                       --%>
</tr>
            </c:forEach>
        </table>
     
     
 <c:import url="Layout/footer.jsp"/>

