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
                    <td>${rep.nomSpectacle}</td><td>${rep.date}</td>
                   <td><a href="RepresentationsControleur?action=reserver&view=reserver&nomSpectacle=${rep.nomSpectacle}&date=${rep.date}"/>Réserver</td>                       
</tr>
            </c:forEach>
        </table>
     
<a href="PagesControleur?action=goToLogin"/>Me connecter</td>                       

 <c:import url="Layout/footer.jsp"/>

