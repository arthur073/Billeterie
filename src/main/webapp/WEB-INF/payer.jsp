<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h3 align="center" class="titre"> Paiement de vos places </h3>
<br/>

<div class="confirmation">Montant total de votre facture : ${prixTotal} &euro;</div>

<br/>

<form id="form" action="ReservationControleur"  class="reserverFormCentre" method="post" onsubmit="return checkCbNumber()">
    <label>Num�ro de carte bleue : </label><input type="text" name="carteBleue" value="">
    <br/><br/><br/>
    <input type="text" name="places" style="" value="${places}" />
     <input type="text" name="map" style="display:none;" value="${map}" />
     <input type="text" name="prixTotal" style="display:none;" value="${prixTotal}" />
     <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
     <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
     <input type="text" name="resAsupprimer" style="display:none;" value="${resAsupprimer}" />
     <a href="PagesControleur" style="margin-right: 30px"><button type='button' class="btnReserverCentre">Annuler</button></a>
    <input type="submit" name="action" value="Proceder au paiement" class="btnReserverCentre"/>
</form>

    <script language="JavaScript">
        function checkCbNumber()
        {
            if( document.forms["form"].elements["carteBleue"].value == "" )
            {
                alert("Veuillez saisir un num�ro valide.");
                return false;
            }
            else
                return true;    
        }
    </script>

<c:import url="Layout/footer.jsp"/>

