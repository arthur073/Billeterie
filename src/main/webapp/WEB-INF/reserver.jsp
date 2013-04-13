<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h2 align="center" class="titre"> Réservation de billets </h2>

Vous avez choisi la représentation suivante : <br/>
<div class="reserver" >
    <table>
        <tr>
            <td>
                <img class="reserverImg" src="images/<%= request.getParameter("Image")%>"/>
            </td>
            <td>
                <h3> <%= request.getParameter("NomSpectacle")%> </h3>
                <h3> Le  <%= request.getParameter("Date")%></h3>
            </td>
            <td>
                <form action="ReservationControleur"  class="reserverForm" method="post">
                    <h4> Choisissez vos places : </h4> 
                    <label> Pelouse : </label><input type="text" label="nbPlacesPelouse"/> <span class="price"> (  une place = <%= request.getAttribute("p1")%> &euro; )</span><br/> 
                    <label> Orchestre : </label><input type="text" label="nbPlacesOrchestre"/><span class="price"> (  une place = <%= request.getAttribute("p2")%> &euro; )</span><br/> 
                    <label> Balcon : </label><input type="text" label="nbPlacesBalcon"/> <span class="price"> (  une place = <%= request.getAttribute("p3")%> &euro; )</span> <br/> 
                    <input type="submit" name="action" label="reserverPlaces" value="Réserver mes places" class="btnReserver"/> 
                </form>
            </td>
        </tr>
    </table>
</div>
<br/>
Le spectacle <%= request.getParameter("NomSpectacle")%> est aussi disponible aux dates suivantes : <br/><br/>

<c:forEach items="${representations}" var="rep">
    <div class="reserverSmall" >
        <table>
            <tr>
                <td>
                    <img class="reserverImgSmall" src="images/${rep.image}"/>
                </td>
                
                <td>
                    <h3> Le  ${rep.date}</h3>
                </td>
                <td>
                    <form action="ReservationControleur" method="post">
                        <input type="text" name="Date" value="${rep.date}" style="display:none"/>
                        <input type="text" name="Image" value="${rep.image}" style="display:none"/>
                        <input type="text" name="NomSpectacle" value="${rep.nomSpectacle}" style="display:none"/>
                        <input type="text" name="NoSpectacle" value="${rep.noSpectacle}" style="display:none"/>
                        <input type="text" name="NoRepresentation" value="${rep.noRepresentation}" style="display:none"/>
                        <input type="submit" name="action" label="reserver" value="Réserver" class="btnBlack btnReserverSmall"/>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</c:forEach>


<c:import url="Layout/footer.jsp"/>

