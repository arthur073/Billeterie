<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
     Veuillez entrer vos identifiants pour vous connecter : <br/>
     
     
     <form action="PagesControleur" method="push" class="loginForm" >
         <label> Login : </label><input type="text" name="username"/> <br/> 
         <label>Mot de passe : </label><input type="password" name="passwd"/> <br/> <br/>
         <input type="submit" class="btnLogin" name="action" value="Valider" /> 
         <input type="submit" class="btnLogin" name="action" value="Annuler" />

     </form>
     
 <c:import url="Layout/footer.jsp"/>

