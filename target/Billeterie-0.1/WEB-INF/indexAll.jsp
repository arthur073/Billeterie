<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<h2 align="center" class="header"> Mes billets en ligne </h2>

<table >
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
            <td><a class="reserver" href="ReservationControleur?action=reserver&Date=${rep.date}&NomSpectacle=${rep.nomSpectacle}&NoSpectacle=${rep.noSpectacle}&NoRepresentation=${rep.noRepresentation}"/>Réserver</td>                       
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

