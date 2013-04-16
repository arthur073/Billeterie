<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>


<table class="indexTable">
    <tr>
        <th> </th>
        <th>Spectacle</th>
        <th>Date</th>
        <th> </th>
    </tr>
    <c:forEach items="${representations}" var="rep">
        <tr>
            <td><img height="80px"  src="images/${rep.spectacle.image}"/></td>
            <td>${rep.spectacle.nom}</td>
            <td>${rep.date}</td>
            <td> 
                <form action="ReservationControleur" method="post" >
                    <input type="text" name="Date" value="${rep.date}" style="display:none"/>
                    <input type="text" name="Image" value="${rep.spectacle.image}" style="display:none"/>
                    <input type="text" name="NomSpectacle" value="${rep.spectacle.nom}" style="display:none"/>
                    <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                    <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                    <input type="submit" name="action" label="reserver" value="Reserver" class="btnBlack"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

