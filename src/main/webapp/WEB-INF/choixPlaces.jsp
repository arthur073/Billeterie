<%@page import="java.util.LinkedList"%>
<%@page import="modele.Reservation"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vue.RangToZone"%> 

<c:import url="Layout/header.jsp"/>    

<script>
    $("#chairs").ready(function () {
    $("#chairs").children().click(
        function (e) {       
            if (e.target.attributes.length > 1) {

                var zone = e.target.attributes.item(3).nodeValue;
                var place = e.target.attributes.item(1).nodeValue;
                var rang = e.target.attributes.item(2).nodeValue;
                var classe = e.target.attributes.item(0).nodeValue;
                                
                if (classe != "sat") {
                    // cas du premier click
                    e.target.attributes.item(0).nodeValue = "sat";
                    $("#selected").val($("#selected").val() + place + "/" + rang + "/" + zone + "!");
                } else {
                    // cas du deuxième click
                    var newVal = $("#selected").val().replace(place + "/" + rang + "/" + zone + "!", '');
                    $("#selected").val(newVal);
                    // retrouver la zone 
                    var zonePrec = e.target.attributes.item(3).nodeValue;
                    
                    if (zonePrec === "1") {
                        e.target.attributes.item(0).nodeValue = "poulailler";
                    }
                    if (zonePrec === "2") {
                        e.target.attributes.item(0).nodeValue = "orchestre";
                    }
                    if (zonePrec === "3") {
                        e.target.attributes.item(0).nodeValue = "balcon";
                    }
                    if (zonePrec === "4") {
                        e.target.attributes.item(0).nodeValue = "loge";
                    }
                }
                $('#selected:first').trigger('change');

            }
        }
    );
    });
    
    // Change l'état du bouton action
     $("#chairs").ready(function () {
        $("#action").attr("disabled", "disabled");         
        $("#selected").change(function () {
            var empty = false;
            if ($("#selected").val().length === 0) {
                // input vide
                        $("#action").attr("disabled", "disabled");
                    } else {
                        $("#action").removeAttr("disabled");
                    }
                });
         $('#selected:first').trigger('change');
      });
   
   // popup
    $("#chairs").ready(function () {
        var zone;
        var place;
        var rang;
        var moveLeft = 20;
        var moveDown = 10;
        
        
        $("#chairs").children().hover(
        function (e) {
            $('div#pop-up').show();
            
            if (e.target.attributes.length > 1) {
                zone = e.target.attributes.item(0).nodeValue;
                place = e.target.attributes.item(1).nodeValue;
                rang = e.target.attributes.item(2).nodeValue;
                noZone = e.target.attributes.item(3).nodeValue;
                var tmp= document.getElementById("prix").value.split(",");
                var p1 = tmp[0] + " &euro;"
                var p2 = tmp[1] + " &euro;"
                var p3 = tmp[2] + " &euro;"
                var p4 = tmp[3] + " &euro;"
                var prix = new Array (p1,p2,p3,p4);
                
            prix 
               $('div#pop-up').html(
                    "<h3>" + zone + "</h3>" +
                    "<p>" + "Place : " + place + "</p>" +
                    "<p>" + "Rang : " + rang + "</p>" +
                    "<p>" + "Prix : " + prix[noZone-1] + "</p>"
               ); 
            } else {
                $('div#pop-up').hide();
            }
        },
        function (e) {
            $('div#pop-up').hide();
        }
    );
        
    $('#chairs').mousemove(function(e) {
        $("div#pop-up").css('top', e.pageY + moveDown).css('left', e.pageX + moveLeft);
    });
    });
    

    // on sat tous les sièges selectionnés 
    $("#chairs").ready(function () {
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
               //alert(noPlace + ":" + noRang + ":" + noZone);
               $('td[noPlace='+noPlace+'][noRang='+noRang+'][noZone='+noZone+']').attr("class", "sat");
            };
        }
    });
</script>
    

        
<%  LinkedList<Reservation> PlacesOccupees = (LinkedList<Reservation>) request.getAttribute("PlacesOccupees");%>
<%  int noSpectacle = Integer.parseInt(request.getParameter("NoSpectacle"));%>
<%  int noRepresentation = Integer.parseInt(request.getParameter("NoRepresentation"));%>


<div class="confirmation">
    <table>
        <tr>
            <td>
                <img class="reserverImg" src="images/${Image}"/>
            </td>
            <td>
                <h3> ${NomSpectacle}</h3>
                <h3> Le ${Date}</h3>
            </td>
        </tr>
    </table>
</div>


Cliquez sur les places que vous désirez : <br/>


<% RangToZone rtz = new RangToZone();%>

<table id="chairs">
    <% for (int rang = 15; rang >= 1; rang-- ) { %>
    <tr>
        <!-- un rang -->
        <% if (rang > 13) { %>
            <!-- les balcons -->
            <% for (int place = 1; place <= 43; place++) {%>
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" />
            <% } %>
        <% } %>
        <% if (rang == 13|| rang == 8 || rang == 4) { %>
            <% for (int place = 1; place <= 43; place++) {%>
                <% if (place < 5 || place > 39) { %>
                    <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                        noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" />
                <% } else { %>
                    <td/>
                <% } %>
            <% } %>        
        <% } %>
        <% if (rang < 13 && rang > 8) { %>
            <% for (int place = 1; place <= 41; place++) {%>
                <% if (place  == 5 || place == 38) { %>
                    <td class="sitstop"/>
                <% } %>
                <% if (place < 5 || place > 37) { %>
                    <!-- de nouveau des balcons -->
                    <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" />
                <% } else { %>
                    <!-- le poulailler -->
                    <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 1)%>" 
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="1" />
                <% } %>
            <% } %>
        <% } %>
        <% if (rang < 8 && rang > 4) { %>
        <% for (int place = 1; place <= 41; place++) {%>
            <% if (place  == 5 || place == 38) { %>
                <td class="sitstop"/>
            <% } %>
            <% if (place < 5 || place > 37) { %>
                <!-- de nouveau des balcons -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" />
            <% } else { %>
                <!-- les loges -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 4)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="4" />
            <% } %>
        <% } %>
        <% } %>
        <% if (rang < 4 && rang > 0) { %>
        <% for (int place = 1; place <= 41; place++) {%>
            <% if (place  == 5 || place == 38) { %>
                <td class="sitstop"/>
            <% } %>
            <% if (place < 5 || place > 37) { %>
                <!-- de nouveau des balcons -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" />
            <% } else { %>
                <!-- l'orchestre -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 2)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="2" />
            <% } %>
        <% } %>
        <% } %>
    </tr>
    <% } %>    
</table>

<div id="scene">SCÈNE</div>

<form action="ReservationControleur"  class="reserverForm" method="post">
    <input type="text" id="selected" name="places" style="display: none" /> <br/>
    <input type="text" name="NomSpectacle" style="display:none;" value="${NomSpectacle}" />
    <input type="text" name="prix" id="prix" style="display:none;" value="${prix}" />
    <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
    <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
    <input type="text" name="Date" style="display:none;" value="${Date}" />
    <input type="text" name="Image" style="display:none;" value="${Image}" />
    <input type ="text" name="from" style="display: none" value="confirmation"/>
    <input type ="text" name="params" style="display: none" value="<%= request.getParameterMap()%>"/>
    <input type="submit" id="action" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver centre"/>
</form>
    
<div id='pop-up'/>

<c:import url="Layout/footer.jsp"/>

