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
            <td><img height="80"  src="images/${rep.spectacle.image}" class="shadowImg"/></td>
            <td>${rep.spectacle.nom}</td>
            <td>${rep.getDate(null)}</td>
            <td> 
                <form action="ReservationControleur" method="post" >
                    <input type="text" name="Date" value="${rep.getDate(null)}" style="display:none"/>
                    <input type="text" name="Image" value="${rep.spectacle.image}" style="display:none"/>
                    <input type="text" name="NomSpectacle" value="${rep.spectacle.nom}" style="display:none"/>
                    <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                    <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                    <%  Boolean loggedAdmin = (Boolean) request.getSession().getAttribute("Admin");
                        if (loggedAdmin != null && loggedAdmin) {%>
                            <input type="submit" name="action" value="Reserver" class="btnBlack" disabled="disabled"/>
                    <% } else {%>
                            <input type="submit" name="action" value="Reserver" class="btnBlack"/>
                    <% }%>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:import url="Layout/footer.jsp"/>

