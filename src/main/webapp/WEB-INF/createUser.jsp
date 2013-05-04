<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      

Veuillez remplir le formulaire suivant pour créer votre compte : <br/>

     <form id="formCreateUser" action="UtilisateursControleur" method="post" class="loginForm" onsubmit="return checkFields();" >
         <label> Nom : </label><input type="text" name="nom"/> <br/> 
         <input type="text" name="redirectionVers" style="display: none" value="${redirectionVers}"/> 
         <label> Prénom : </label><input type="text" name="prenom"/> <br/> 
         <label> Login : </label><input type="text" name="username"/> <br/> 
         <label> Mot de passe : </label><input type="password" name="passwd"/> <br/>
         <label> Adresse e-mail : </label><input type="email" name="email" size='40'/> <br/>  <br/>
         <input type="submit" class="btnBlack" name="action" value="Creer" /> 
     </form>
     <form id="formCreateUser2" action="UtilisateursControleur" method="post" class="loginForm">
         <input type="submit" class="btnBlack" name="action" value="Annuler" />
     </form>
     
     <br/>
     
     <script language="JavaScript">
        function checkFields()
        {
            var nom_v = document.forms["formCreateUser"].elements["nom"].value.toString().replace(/^\s+|\s+$/g, '');
            if( nom_v === "" )
            {
                alert("Veuillez saisir un nom valide.");
                return false;
            }
            var prenom_v = document.forms["formCreateUser"].elements["prenom"].value.toString().replace(/^\s+|\s+$/g, '');
            if( prenom_v === "" )
            {
                alert("Veuillez saisir un prénom valide.");
                return false;
            }
            var login_v = document.forms["formCreateUser"].elements["username"].value.toString().replace(/^\s+|\s+$/g, '');
            if( login_v === "" )
            {
                alert("Veuillez saisir un login valide.");
                return false;
            }
            var mdp_v = document.forms["formCreateUser"].elements["passwd"].value.toString().replace(/^\s+|\s+$/g, '');
            if( mdp_v  === "" )
            {
                alert("Veuillez saisir un mot de passe valide.");
                return false;
            }
            else
                return true;    
            
        }
    </script>
     
 <c:import url="Layout/footer.jsp"/>

