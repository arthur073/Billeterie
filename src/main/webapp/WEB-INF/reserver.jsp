<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    


Vous souhaitez réserver pour le spectacle <%= request.getParameter("NomSpectacle")%> dont la representation
est le <%= request.getParameter("Date")%>.

<form action="ReservationControleur" method="post">
    Nombre de places souhaitées : <br/>
    Pelouse : <input type="text" label="nbPlacesPelouse"/> <%= request.getAttribute("p1") %> <br/> 
    Orchestre : <input type="text" label="nbPlacesOrchestre"/> <%= request.getAttribute("p2") %> <br/> 
    Balcon (avec la soeur à titi) : <input type="text" label="nbPlacesBalcon"/> <%= request.getAttribute("p3") %> <br/> 
    <input type="submit" name="action" value="annuler" />
    <input type="submit" name="action" value="valider" /> 
</form>



<c:import url="Layout/footer.jsp"/>

