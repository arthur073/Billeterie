<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

TEMPORAIRE : prix des places <br/>
Orchestre :  <%= request.getAttribute("p1") %> <br/>
Poulailler : ? <br/>
Balcon : ? <br/>


Vous souhaitez r�server pour le spectacle <%= request.getParameter("NomSpectacle")%> dont la representation
est le <%= request.getParameter("Date")%>.

<form action="ReservationControleur" method="post">
    Nombre de places souhait�es : <input type="text" label="nbPlaces"/> <br/> 
    En zone :
    <SELECT name="zone">
        <OPTION VALUE="Pelouse">Pelouse</OPTION>
        <OPTION VALUE="SiegeCuir">Si�ge Cuir</OPTION>
        <OPTION VALUE="CarreGold">Carr� Gold</OPTION>
        <OPTION VALUE="Sur les genoux de la soeur � Titi">Chez la soeur � Titi</OPTION>
    </SELECT>
    <input type="submit" name="action" value="annuler" />
    <input type="submit" name="action" value="valider" /> 
</form>



<c:import url="Layout/footer.jsp"/>

