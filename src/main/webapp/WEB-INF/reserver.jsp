<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>    

Vous souhaitez réserver pour le spectacle <%= request.getParameter("nomSpectacle") %> dont la representation
est le <%= request.getParameter("date") %>.
     
<form action="PagesControleur" method="get">
         Nombre de places souhaitées : <input type="text" label="nbPlaces"/> <br/> 
         En zone :
	<SELECT name="zone">
		<OPTION VALUE="Pelouse">Pelouse</OPTION>
		<OPTION VALUE="SiegeCuir">Siège Cuir</OPTION>
		<OPTION VALUE="CarreGold">Carré Gold</OPTION>
		<OPTION VALUE="ChezLaSoeurAtiti">Chez la soeur à Titi</OPTION>
        </SELECT>
         <input type="submit" name="action" value="annuler" />
         <input type="submit" name="action" value="valider" /> 
     </form>



 <c:import url="Layout/footer.jsp"/>

