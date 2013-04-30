<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<c:import url="Layout/header.jsp"/>      
<h2 align="center" class="header"> Formulaire de création d'un utilisateur  </h2>
     <br/>
          
     <form id="formCreateUser" action="UtilisateursControleur" method="post" class="loginForm" onsubmit="return checkFields()" >
         <label> Nom : </label><input type="text" name="nom"/> <br/> 
         <label> Prénom : </label><input type="text" name="prenom"/> <br/> 
         <label> Login : </label><input type="text" name="username"/> <br/> 
         <label> Mot de passe : </label><input type="password" name="passwd"/> <br/>
         <label> Adresse e-mail : </label><input type="email" name="email" size='40'/> <br/>  <br/>
         <input type="submit" class="btnBlack" name="action" value="Creer" /> 
         <input type="submit" class="btnBlack" name="action" value="Annuler" />
     </form>
     
     <br/>
     
     <script language="JavaScript">
        function checkFields()
        {
            if( document.forms["formCreateUser"].elements["nom"].value == "" )
            {
                alert("Veuillez saisir un nom valide.");
                return false;
            }
            if( document.forms["formCreateUser"].elements["prenom"].value == "" )
            {
                alert("Veuillez saisir un prénom valide.");
                return false;
            }
            if( document.forms["formCreateUser"].elements["username"].value == "" )
            {
                alert("Veuillez saisir un login valide.");
                return false;
            }
            if( document.forms["formCreateUser"].elements["passwd"].value == "" )
            {
                alert("Veuillez saisir un mot de passe valide.");
                return false;
            }
            else
                return true;    
            
        }
    </script>
     
 <c:import url="Layout/footer.jsp"/>

