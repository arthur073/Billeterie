<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<script language="javascript">
    $(document).ready(function () {
        // Constantes à afficher
        var Zones = {
            <c:forEach items="${Zones}" var="zone">
                "${zone.noZone}" : { "tarifBase" : "${zone.tarifBase}", "categorie" : "${zone.categorie}" },
            </c:forEach>
        };

        $(".place").click(function () {
            if ($(this).hasClass("occupee")) {
                return false;
            }
            var zone = $(this).find(".zone").html();
            var place = $(this).find(".numero").html();
            var rang = $(this).find(".rang").html();
            if ($(this).hasClass("sat")) {
                $(this).removeClass("sat");
                // cas du deuxième click
                var newVal = $("#selected").val().replace(place + "/" + rang + "/" + zone + "!", '');
                $("#selected").val(newVal);
            } else {
                // cas du premier click
                $(this).addClass("sat");
                $("#selected").val($("#selected").val() + place + "/" + rang + "/" + zone + "!");
            }
            $('#selected').trigger('change');
        });
        
        $(".place").hover(function () {
            if ($(this).hasClass("occupee")) {
                $('div#pop-up').hide();
                return false;
            }
            var zone = $(this).find(".zone").html();
            var place = $(this).find(".numero").html();
            var rang = $(this).find(".rang").html();
            $('div#pop-up').html(
                "<h3>" + Zones[zone].categorie + "</h3>" +
                "<p>" + "Place : " + place + "</p>" +
                "<p>" + "Rang : " + rang + "</p>" +
                "<p>" + "Prix : " + Zones[zone].tarifBase + "</p>"
            );
            $('div#pop-up').show();
        },
        function (e) {
            $('div#pop-up').hide();
        });
    
        // Change l'état du bouton action
        $("#action").attr("disabled", "disabled");         
        $("#selected").change(function () {
            if ($("#selected").val().length === 0) {
                // input vide
                $("#action").attr("disabled", "disabled");
            } else {
                $("#action").removeAttr("disabled");
            }
        });
        $('#selected').trigger('change');
        
        $('#chairs').mousemove(function(e) {
            $("div#pop-up").css('top', e.pageY + 10).css('left', e.pageX + 10);
        });

        // on sat tous les sièges selectionnés 
        if ($("#selected").val().length > 0) {
            var placesArray = $("#selected").val().split('!');
            var champsArray;
            var noPlace;
            var noRang;
            var noZone;
            for(i = 0; i< placesArray.length-1; i++){
               // pour chaque place on récupère les champs
               champsArray = placesArray[i].split('/');
               noPlace = champsArray[0]; 
               noRang = champsArray[1];
               noZone = champsArray[2];
               // on la met à sat
               $('td.numero-'+noPlace+'.rang-'+noRang+'.zone-'+noZone).addClass("sat");
            }
        }
    });
</script>
        
<div class="confirmation">
    <table>
        <tr>
            <td>
                <img class="reserverImg" src="images/${rep.spectacle.image}"/>
            </td>
            <td>
                <h3> ${rep.spectacle.nom}</h3>
                <h3> Le ${rep.getDate(null)}</h3>
            </td>
        </tr>
    </table>
</div>


Cliquez sur les places que vous désirez : <br/>

<table id="chairs">
    <c:forEach items="${ToutesPlaces}" var="rang">
        <tr>
            <c:forEach items="${rang}" var="place">
                <c:choose>
                    <c:when test="${empty place}">
                        <td class="vide"></td>
                    </c:when>
                    <c:when test="${PlacesOccupees.contains(place)}">
                        <td class="place occupee"></td>
                    </c:when>
                    <c:otherwise>
                        <td class="place zone-${place.noZone} rang-${place.noRang} numero-${place.noPlace}">
                            <span class="zone">${place.noZone}</span>
                            <span class="numero">${place.noPlace}</span>
                            <span class="rang">${place.noRang}</span>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </c:forEach>
</table>


<div id="scene">SCÈNE</div>

<form action="ReservationControleur"  class="reserverForm" method="post">
    <input type="hidden" id="selected" name="places"/> 
    <input type="hidden" name="NoSpectacle" value="${NoSpectacle}" />
    <input type="hidden" name="NoRepresentation" value="${NoRepresentation}" />
    <input type="submit" id="action" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver centre"/>
</form>
    
<div id="pop-up"></div>

<c:import url="Layout/footer.jsp"/>

