<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
<h2 align="center" class="header"> Formulaire de création d'un utilisateur  </h2>
     <br/>
          
     <form action="UtilisateursControleur" method="post" class="loginForm" >
         <label> Nom : </label><input type="text" name="nom"/> <br/> 
         <label> Prénom : </label><input type="text" name="prenom"/> <br/> 
         <label> Login : </label><input type="text" name="username"/> <br/> 
         <label> Mot de passe : </label><input type="password" name="passwd"/> <br/>
         <label> Adresse e-mail : </label><input type="email" name="email" size='40'/> <br/>  <br/>
         <input type="submit" class="btnBlack" name="action" value="Creer" /> 
         <input type="submit" class="btnBlack" name="action" value="Annuler" />
     </form>
     
     <br/>
     
     
 <c:import url="Layout/footer.jsp"/>

