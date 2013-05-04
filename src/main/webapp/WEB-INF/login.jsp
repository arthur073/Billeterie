<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>      


Veuillez entrer vos identifiants pour vous connecter : <br/>

<form action="LoginControleur" method="post" class="loginForm" >
    <input type="hidden" name="redirectionVers" value="${redirectionVers}" />
    <label> Login : </label><input type="text" name="username"/> <br/> 
    <label> Mot de passe : </label><input type="password" name="passwd"/> <br/> <br/>
    <input type="submit" class="btnBlack" name="action" value="Valider" /> 
    <input type="submit" class="btnBlack" name="action" value="Annuler" />
</form>

<br/>
Vous n'avez pas de compte? <br/><br/>

<a href="PagesControleur?action=CreateAccount&redirectionVers=${redirectionVers}" class="btnBlack">Créer un compte</a> 

<c:import url="Layout/footer.jsp"/>

