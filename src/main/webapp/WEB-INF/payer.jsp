<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h3 align="center" class="titre"> Paiement de vos places </h3>
<br/>

<div class="confirmation">Montant total de votre facture : ${prixTotal} &euro;</div>

<br/>
<br/>
<br/>





<form action="ReservationControleur"  class="reserverFormCentre" method="post">
    <label>Numéro de carte bleue : </label><input type="text" name="carteBleue" value="">
    <br/>
    <input type="text" name="places" style="display:none;" value="${places}" />
     <input type="text" name="map" style="display:none;" value="${map}" />
     <input type="text" name="prixTotal" style="display:none;" value="${prixTotal}" />
     <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
     <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
     <input type="text" name="resAsupprimer" style="" value="${resAsupprimer}" />
    <input type="submit" name="action" label="acheterPlaces" value="Proceder au paiement" class="btnReserverCentre"/>
</form>

<c:import url="Layout/footer.jsp"/>

