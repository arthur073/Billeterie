<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
     Veuillez entrer vos identifiants pour vous connecter : <br/>
     
     
     <form action="PagesControleur" method="get">
         Login : <input type="text" label="login"/> <br/> 
         Mot de passe : <input type="password" label="Password"/> <br/>
         <input type="submit" name="action" value="annuler" />
         <input type="submit" name="action" value="valider" /> 
     </form>
     
 <c:import url="Layout/footer.jsp"/>

