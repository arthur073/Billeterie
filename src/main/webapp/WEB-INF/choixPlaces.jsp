<%@page import="java.util.LinkedList"%>
<%@page import="modele.Reservation"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vue.RangToZone"%> 

<c:import url="Layout/header.jsp"/>    

<script>
    function selectChair(obj) {
        var noPlace = obj.getAttribute("noPlace");
        var noRang = obj.getAttribute("noRang");
        var noZone = obj.getAttribute("noZone");

           // changer le etat prec !! 
        if (obj.className === "poulailler" || obj.className === "balcon" || obj.className === "orchestre" || obj.className === "loge") {
            obj.EtatPrec = obj.className;
            obj.className = "sat";
            document.getElementById("selected").value += noPlace + "/" + noRang
                    + "/" + noZone + "!";
        } else {
            if (obj.className === "sat") {
                obj.className = obj.EtatPrec;
                document.getElementById("selected").value = document.getElementById("selected").value.replace(noPlace + "/" + noRang
                        + "/" + noZone + "!", '');
            }
        }
        document.getElementById("action").disabled = (document.getElementById("selected").value === "");
    }

    function griserSubmit() {
        document.getElementById("action").disabled = (document.getElementById("selected").value === "");
    }
 
    window.onload = griserSubmit;
</script>

<script>    
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
               $('div#pop-up').html(
                    "<h3>" + zone + "</h3>" +
                    "<p>" + "Place : " + place + "</p>" +
                    "<p>" + "Rang : " + rang + "</p>"
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
    

</script>

<script>
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
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" onclick="selectChair(this);"/>
            <% } %>
        <% } %>
        <% if (rang == 13|| rang == 8 || rang == 4) { %>
            <% for (int place = 1; place <= 43; place++) {%>
                <% if (place < 5 || place > 39) { %>
                    <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 3)%>" 
                        noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" onclick="selectChair(this);"/>
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
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" onclick="selectChair(this);"/>
                <% } else { %>
                    <!-- le poulailler -->
                    <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 1)%>" 
                    noPlace="<%= place%>" noRang="<%= rang%>" noZone="1" onclick="selectChair(this);"/>
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
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" onclick="selectChair(this);"/>
            <% } else { %>
                <!-- les loges -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 2)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="2" onclick="selectChair(this);"/>
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
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="3" onclick="selectChair(this);"/>
            <% } else { %>
                <!-- l'orchestre -->
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 4)%>" 
                noPlace="<%= place%>" noRang="<%= rang%>" noZone="4" onclick="selectChair(this);"/>
            <% } %>
        <% } %>
        <% } %>
    </tr>
    <% } %>    
</table>

<div id="scene">SCÈNE</div>

<form action="ReservationControleur"  class="reserverForm" method="post">
    <input type="text" id="selected" name="places" style="display: none;" onchange="griserSubmit()"/> <br/>
    <input type="text" name="NomSpectacle" style="display:none;" value="${NomSpectacle}" />
    <input type="text" name="NoSpectacle" style="display:none;" value="${NoSpectacle}" />
    <input type="text" name="NoRepresentation" style="display:none;" value="${NoRepresentation}" />
    <input type="text" name="Date" style="display:none;" value="${Date}" />
    <input type="text" name="Image" style="display:none;" value="${Image}" />
    <input type ="text" name="from" style="display: none" value="confirmation"/>
    <input type ="text" name="params" style="display: none" value="<%= request.getParameterMap()%>"/>
    <input type="submit" id="action" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver"/>
</form>
    
<div id='pop-up'/>

<c:import url="Layout/footer.jsp"/>

