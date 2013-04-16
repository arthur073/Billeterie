<%@page import="java.util.LinkedList"%>
<%@page import="modele.Reservation"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vue.RangToZone"%> 

<c:import url="Layout/header.jsp"/>    

<script>
    //document.getElementById("demo").innerHTML = "My First JavaScript";
    //document.getElementById("chairs").onclick = selectChair();
    function getElementPosition(theElement) {

        var posX = 0;
        var posY = 0;

        while (theElement !== null) {
            posX += theElement.offsetLeft;
            posY += theElement.offsetTop;
            theElement = theElement.offsetParent;
        }

        return {x: posX, y: posY}
    }

    function convertToInt(position) {
        offsetX = 208;
        offsetY = 263;

        if (position.x >= 512) {
            offsetX += 20;
        }
        if (position.x >= 816) {
            offsetX += 20;
        }
        if (position.y >= 351) {
            offsetY += 22;
        }


        return Math.floor((position.x - offsetX) / 28 + 1) + ((position.y - offsetY) / 22 * 30);
    }

    function selectChair(obj) {
        //       alert(obj.innerHTML+document.getElementById("selected").innerHTML); 
       
        
        
        var noPlace = obj.getAttribute("noPlace");
        var noRang = obj.getAttribute("noRang");
        var noZone = obj.getAttribute("noZone");

        if (obj.className === "poulailler" || obj.className === "balcon" || obj.className === "orchestre" || obj.className === "loge") {
            obj.EtatPrec = obj.className;
            obj.className =  "sat";
            document.getElementById("selected").value += noPlace + "/" + noRang
                    + "/" + noZone + "!";
            //alert(pos.x + "," + pos.y+"->"+convertToInt(pos));
        } else {
            if (obj.className === "sat") {
                obj.className =  obj.EtatPrec;
                document.getElementById("selected").value = document.getElementById("selected").value.replace(noPlace + "/" + noRang
                       + "/" + noZone + "!", '');
            }
        }
    }

</script>


<%  LinkedList<Reservation> PlacesOccupees = (LinkedList<Reservation>) request.getAttribute("PlacesOccupees"); %>
<%  int noSpectacle = Integer.parseInt(request.getParameter("NoSpectacle")); %>
<%  int noRepresentation = Integer.parseInt(request.getParameter("NoRepresentation")); %>

noSpectacle = <%= noSpectacle %>
noRepresentation = <%= noRepresentation %>
Cliquez sur les places que vous désirez : <br/>

<% RangToZone rtz = new RangToZone(); %>
<table id="chairs" >
 
    <tr>
        <% for (int rang = 1 ; rang <= 12 ; rang++) { %>
            <% for (int place = 1; place<=10 ; place++) {%>
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, rtz.rangToZone(rang)) %>" 
                    noPlace="<%= place %>" noRang="<%= rang %>" noZone="1" onclick="selectChair(this);"/>
            <% } %>
            <td class="sitstop"/>
               <% if (rang % 4 == 0) { %>
                    </tr>
                    <tr>
               <% } %>
        <% } %>
    </tr>
    <tr class="sitstop"/>
    <tr>
        <% for (int rang = 13 ; rang <= 21 ; rang++) { %>
            <% for (int place = 1; place<=10 ; place++) {%>
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, rtz.rangToZone(rang)) %>" 
                    noPlace="<%= place %>" noRang="<%= rang %>" noZone="2" onclick="selectChair(this);"/>
            <% } %>
            <td class="sitstop"/>
               <% if ((rang-12) % 3 == 0) { %>
                    </tr>
                    <tr>
               <% } %>
        <% } %>
    </tr>
    
    </tr>
    <tr class="sitstop"/>
    <tr>
        <% for (int rang = 22 ; rang <= 27 ; rang++) { %>
            <% for (int place = 1; place<=10 ; place++) {%>
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, rtz.rangToZone(rang)) %>" 
                    noPlace="<%= place %>" noRang="<%= rang %>" noZone="4" onclick="selectChair(this);"/>
            <% } %>
            <td class="sitstop"/>
               <% if ((rang-21) % 2 == 0) { %>
                    </tr>
                    <tr>
               <% } %>
        <% } %>
    </tr>
    

</table>


<div id="scene">SCÈNE</div>

<form action="ReservationControleur"  class="reserverForm" method="post">
    <input type="text" id="selected" name="places"/> <br/>
    <input type="submit" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver"/>
</form>


<c:import url="Layout/footer.jsp"/>

