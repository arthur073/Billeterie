<%-- 
    Document   : admin
    Created on : 17 avr. 2013, 11:34:17
    Author     : michel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vue.RangToZone"%> 

<c:import url="Layout/header.jsp"/>    

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css" />
<script>
    $(function() {
        //en supposant que le debut d'une saison est le 1er janvier et la fin le 31 dec
        // on met le 1er janvier de l'annee suivante pour prendre en compte la journée du 31 dec complete
        // on initialise les champs a cette valeur pour avoir des stats qui s'affichent direct
      $( "#datepickerDebut" ).datepicker({dateFormat: "dd-mm-yy"}).datepicker('setDate', '01-01-2013');
      $( "#datepickerFin" ).datepicker({dateFormat: "dd-mm-yy"}).datepicker('setDate', '01-01-2014');
    });
    
    function checkDate() {
        if( $("#datepickerDebut").datepicker("getDate") > $("#datepickerFin").datepicker("getDate") )
            {
                alert("La date de fin ne peut être inférieur à la date de début");
                $("#datepickerFin").datepicker('setDate', $( "#datepickerDebut" ).datepicker("getDate"));
            }
    }
    function changeStats() {
        var Url = "StatsControlleur?action=rafraichir&dateDebut=" + getDateDebut() +"&dateFin=" + getDateFin();

        xmlHttp = new XMLHttpRequest(); 
        xmlHttp.onreadystatechange = ProcessRequest;
        xmlHttp.open( "GET", Url, true );
        xmlHttp.send( null );
        alert("TODO");
        alert(getDateDebut());
        alert(getDateFin());
        majBenefTotal();
        majListeSpectacles();
        majMeilleursTauxRemplissage();
    }
    function majBenefTotal() {
 
    }
    function majListeSpectacles() {
        alert("TODO");
    }
    function majSpectaclesPlusRentables() {
        alert("TODO");    
    }
    function majMeilleursTauxRemplissage() {
        alert("TODO");        
    }

    function getDateDebut() {
        return $("#datepickerDebut").datepicker("getDate");
    }
    function getDateFin() {
        return $("#datepickerFin").datepicker("getDate");
    }
    
    var xmlHttp = null;

function ProcessRequest() 
{
    if ( xmlHttp.readyState == 4 && xmlHttp.status == 200 ) 
    {
        if ( xmlHttp.responseText == "Not found" ) 
        {
            document.getElementById( "TextBoxCustomerName"    ).value = "Not found";
            document.getElementById( "TextBoxCustomerAddress" ).value = "";
        }
        else
        {
            var info = eval ( "(" + xmlHttp.responseText + ")" );

            // No parsing necessary with JSON!        
            document.getElementById( "TextBoxCustomerName"    ).value = info.jsonData[ 0 ].cmname;
            document.getElementById( "TextBoxCustomerAddress" ).value = info.jsonData[ 0 ].cmaddr1;
        }                    
    }
}
    
</script>
 
<div style="float:left;padding-left: 10%">D&eacute;but période : <input type="text" id="datepickerDebut" /></div>
<div style="float:left;padding-left: 5%">Fin période : <input type="text" id="datepickerFin" onchange="checkDate()"/></div>
<div style="float:left; padding-left: 5%"><button onclick="changeStats()">Valider</button></div>
<br><br>
<table style="clear:both; border: 1px solid black">
    <tr>
        <td>Bénéfice total</td>
        <td></td>
    </tr>
    <tr>
        <td>Liste deroulante Spectacle </td>
        <td></td>
    </tr>
    <tr>
        <td>Liste des 5 spectacles les plus rentables</td>
        <td></td>
    </tr>
    <tr>
        <td>Taux de remplissage les plus élevés</td>
        <td></td>
    </tr>
</table>
<c:import url="Layout/footer.jsp"/>

