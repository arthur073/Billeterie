<%-- 
    Document   : admin
    Created on : 17 avr. 2013, 11:34:17
    Author     : michel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        return $("#datepickerDebut").datepicker({ dateFormat: 'yy-mm-dd' }).val();
    }
    function getDateFin() {
        return $("#datepickerFin").datepicker({ dateFormat: 'yy-mm-dd' }).val();
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
                //parseur a deplacer surement
                var parseXml;
                if (typeof window.DOMParser !== "undefined") {
                    racine = ( new window.DOMParser() ).parseFromString(xmlHttp.responseText, "text/xml");
                } else if (typeof window.ActiveXObject !== "undefined" &&
                       new window.ActiveXObject("Microsoft.XMLDOM")) {
                    var racine = new window.ActiveXObject("Microsoft.XMLDOM");
                    racine.async = "false";
                    racine.loadXML(xmlHttp.responseText);
                } else {
                    alert("et merde ! ");
                    throw new Error("No XML parser found");
                }
                //fin parseur

                //parcours des elements
                var arbre = racine.childNodes[0];
                var benefTotal = arbre.childNodes[0].childNodes[0].textContent;
                alert(benefTotal);
                //element.getAttribute            
                document.getElementById( "valeurBenefTotal"    ).innerHTML = benefTotal;
            }   
        }
    }
}
    
</script>
 
<div style="float:left;padding-left: 10%">D&eacute;but période : <input type="text" id="datepickerDebut" onchange="checkDate();changeStats()"/></div>
<div style="float:left;padding-left: 15%">Fin période : <input type="text" id="datepickerFin" onchange="checkDate();changeStats()"/></div>
<br><br>
<table style="clear:both; border: 1px solid black">
    <tr>
        <td>Bénéfice total</td>
        <td id="valeurBenefTotal">100</td>
    </tr>
    <tr>
        <td>Liste deroulante Spectacle </td>
        <td id="listeSpectacles">vide</td>
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

