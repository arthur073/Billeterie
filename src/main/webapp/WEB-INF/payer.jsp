<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h3 align="center" class="titre"> Paiement de vos places </h3>
<br/>

<div class="confirmation">Montant total de votre facture : ${panier.prixTotal} &euro;</div>

<br/>

<form id="form" action="ReservationControleur"  class="reserverFormCentre" method="post" onsubmit="return checkCbNumber()">
    <label>Numéro de carte bleue : </label><input type="text" name="carteBleue" value="">
    <br/><br/><br/>
     <input type="hidden" name="resAsupprimer" style="" value="${resAsupprimer}" />
     <a href="PagesControleur" style="margin-right: 30px"><button type='button' class="btnReserverCentre">Annuler</button></a>
    <input type="submit" name="action" value="Proceder au paiement" class="btnReserverCentre"/>
</form>

    <script language="JavaScript">
        function checkCbNumber()
        {
            var cb_v = document.forms["form"].elements["carteBleue"].value.toString().replace(/^\s+|\s+$/g, '');
            var reg = new RegExp('^[0-9]+$');
            if( cb_v === "" || !reg.test(cb_v))
            {
                alert("Veuillez saisir un numéro valide.");
                return false;
            }
            else
                return true;    
        }
    </script>

<c:import url="Layout/footer.jsp"/>

