<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
     Veuillez entrer vos identifiants pour vous connecter : <br/>
     
     
     <form action="PagesControleur" method="get" class="loginForm" >
         <label> Login : </label><input type="text" label="login"/> <br/> 
         <label>Mot de passe : </label><input type="password" label="Password"/> <br/> <br/>
         <input type="submit" class="btnLogin" name="action" value="valider" /> 
         <input type="reset" class="btnLogin" name="action" value="annuler" />

     </form>
     
 <c:import url="Layout/footer.jsp"/>

