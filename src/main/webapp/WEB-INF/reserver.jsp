<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>    

Vous souhaitez r�server pour le spectacle <%= request.getParameter("nomSpectacle") %> dont la representation
est le <%= request.getParameter("date") %>.
     
<form action="PagesControleur" method="get">
         Nombre de places souhait�es : <input type="text" label="nbPlaces"/> <br/> 
         En zone :
	<SELECT name="zone">
		<OPTION VALUE="Pelouse">Pelouse</OPTION>
		<OPTION VALUE="SiegeCuir">Si�ge Cuir</OPTION>
		<OPTION VALUE="CarreGold">Carr� Gold</OPTION>
		<OPTION VALUE="ChezLaSoeurAtiti">Chez la soeur � Titi</OPTION>
        </SELECT>
         <input type="submit" name="action" value="annuler" />
         <input type="submit" name="action" value="valider" /> 
     </form>



 <c:import url="Layout/footer.jsp"/>

