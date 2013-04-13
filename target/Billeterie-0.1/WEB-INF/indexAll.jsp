<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<h2 align="center" class="titre"> Mes billets en ligne </h2>

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
            <td> 
                <form action="ReservationControleur" method="get">
                    <input type="text" label="Date" value="${rep.date}" style="display:none"/>
                    <input type="text" label="NomSpectacle" value="${rep.nomSpectacle}" style="display:none"/>
                    <input type="text" label="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                    <input type="text" label="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                    <input type="submit" name="action" label="reserver" value="reserver" class="btnBlack"/>
                </form>


            </td>
            <td><a class="reserver" href="ReservationControleur?action=reserver&Date=${rep.date}&NomSpectacle=${rep.nomSpectacle}&NoSpectacle=${rep.noSpectacle}&NoRepresentation=${rep.noRepresentation}"/>Réserver</td>                       
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

