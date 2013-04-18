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
    
    function griserSubmit(){
        document.getElementById("action").disabled = (document.getElementById("selected").value === "");
    }
    
    window.onload=griserSubmit;
</script>

<%  LinkedList<Reservation> PlacesOccupees = (LinkedList<Reservation>) request.getAttribute("PlacesOccupees"); %>
<%  int noSpectacle = Integer.parseInt(request.getParameter("NoSpectacle")); %>
<%  int noRepresentation = Integer.parseInt(request.getParameter("NoRepresentation")); %>

Cliquez sur les places que vous désirez : <br/>


<% RangToZone rtz = new RangToZone(); %>
<table id="chairs">
    <tr>
        <c:forEach var="rang" begin="1" end="12" step="1">
          <c:forEach var="place" begin="1" end="12" step="1">
              <td class="${rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 1)}" 
                    noPlace="${place}" noRang="${rang}" noZone="1" onclick="selectChair(this);"/>
          </c:forEach>
            <td class="sitstop"/>
            <c:if test="${ rang % 4 == 0}">
                </tr>
                <tr>
            </c:if>
            
        </c:forEach>
    </tr>
</table>

<table id="chairs" > 
    <tr>
        <% for (int rang = 1 ; rang <= 12 ; rang++) { %>
            <% for (int place = 1; place<=10 ; place++) {%>
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 1) %>" 
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
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 2) %>" 
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
                <td class="<%= rtz.etatSiege(PlacesOccupees, noSpectacle, noRepresentation, place, rang, 4) %>" 
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
    <input type="text" id="selected" name="places" style="display:none;" onchange="griserSubmit()"/> <br/>
    <input type="text" name="NomSpectacle" style="display:none;" value="${NomSpectacle}" />
     <input type="text" name="Date" style="display:none;" value="${Date}" />
     <input type="text" name="Image" style="display:none;" value="${Image}" />
     
     <input type="submit" id="action" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver"/>
</form>


<c:import url="Layout/footer.jsp"/>

