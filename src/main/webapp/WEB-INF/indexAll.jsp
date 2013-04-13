<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<h2 align="center" class="titre"> Mes billets en ligne </h2>

<table class="indexTable">
    <tr>
        <th> </th>
        <th>Spectacle</th>
        <th>Date</th>
        <th> </th>
    </tr>
    <c:forEach items="${representations}" var="rep">
        <tr>
            <td><img height="80px"  src="images/${rep.image}"/></td>
            <td>${rep.nomSpectacle}</td>
            <td>${rep.date}</td>
            <td> 
                <form action="ReservationControleur" method="post">
                    <input type="text" name="Date" value="${rep.date}" style="display:none"/>
                    <input type="text" name="Image" value="${rep.image}" style="display:none"/>
                    <input type="text" name="NomSpectacle" value="${rep.nomSpectacle}" style="display:none"/>
                    <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                    <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                    <input type="submit" name="action" label="reserver" value="Réserver" class="btnBlack"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

