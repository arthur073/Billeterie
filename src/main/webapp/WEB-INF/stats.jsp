<%-- 
    Document   : stats
    Created on : 17 avr. 2013, 11:34:17
    Author     : michel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<style type="text/css" media="screen">
    @import url(http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css);
    @import url(statsstyle.css);
</style>

<script>
    $(function() {
        //en supposant que le debut d'une saison est le 1er sept et la fin le 30 juin
        // on met le 1er juillet  pour prendre en compte la journée du 30 juin complete
        // on initialise les champs a cette valeur pour avoir des stats qui s'affichent direct
      $( "#datepickerDebut" ).datepicker({dateFormat: "dd-mm-yy"}).datepicker('setDate', '${dateDebutLue}');
      $( "#datepickerFin" ).datepicker({dateFormat: "dd-mm-yy"}).datepicker('setDate', '${dateFinLue}');
    });
    
    function checkDate() {
        if( $("#datepickerDebut").datepicker("getDate") > $("#datepickerFin").datepicker("getDate") )
            {
                alert("La date de fin ne peut être inférieur à la date de début");
                var date = new Date($( "#datepickerDebut" ).datepicker("getDate"));
                date.setDate(date.getDate() + 1);
                $("#datepickerFin").datepicker('setDate', date);
            }
    }
    function changeStats() {
        var Url = "StatsControleur?action=rafraichir&dateDebut=" + getDateDebut() +"&dateFin=" + getDateFin();
        xmlHttp = new XMLHttpRequest(); 
        xmlHttp.onreadystatechange = ProcessRequest;
        xmlHttp.open( "GET", Url, true );
        xmlHttp.send( null );
    }

    function getDateDebut() {
        return $("#datepickerDebut").datepicker({ dateFormat: 'dd-mm-yy' }).val();
    }
    function getDateFin() {
        return $("#datepickerFin").datepicker({ dateFormat: 'dd-mm-yy' }).val();
    }
    
    var xmlHttp = null;

function ProcessRequest() 
{
    if ( xmlHttp.readyState === 4 ) 
    {
        if( xmlHttp.status === 200 )
        {            
            if ( xmlHttp.responseText === "Not found" ) 
            {
                alert("error");
            }
            else
            {
                // on récupère directement du HTML
                $("#stats-container").html(xmlHttp.responseText);
            }   
        }
    }
}
    
</script>
 
<div style="float:left;padding-left: 10%;">D&eacute;but période : <input type="text" id="datepickerDebut" onchange="checkDate();changeStats()"/></div>
<div style="float:left;padding-left: 15%;">Fin période : <input type="text" id="datepickerFin" onchange="checkDate();changeStats()"/></div>

<div id="stats-container" class="clearer">
    <c:import url="statsWidget.jsp"/>
</div>

<c:import url="Layout/footer.jsp"/>

