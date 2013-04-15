<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
<h2 align="center" class="titre"> Connexion à votre espace client  </h2>

    <% Boolean b = (Boolean) request.getSession().getAttribute("FailedLogIn"); %>
    
    <% if (b) { %>
        !! Mauvais identifiants !! <br/><br/>
    <% } %>

     Veuillez entrer vos identifiants pour vous connecter : <br/>
     
     
     <form action="LoginControleur" method="post" class="loginForm" >
         <label> Login : </label><input type="text" name="username"/> <br/> 
         <label>Mot de passe : </label><input type="password" name="passwd"/> <br/> <br/>
         <input type="submit" class="btnBlack" name="action" value="Valider" /> 
         <input type="submit" class="btnBlack" name="action" value="Annuler" />
     </form>
     
     <br/>
     Vous n'avez pas de compte? <br/><br/>
     
     <form action="PagesControleur" method="push" >
         <input type="submit" class="btnBlack" name="action" value="Creer un compte" /> 
     </form>
     
 <c:import url="Layout/footer.jsp"/>

