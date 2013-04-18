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
      $( "#datepickerDebut" ).datepicker({dateFormat: "dd-mm-yy"});
      $( "#datepickerFin" ).datepicker({dateFormat: "dd-mm-yy"});
    });
    
    function checkDate() {
        if( $("#datepickerDebut").datepicker("getDate") > $("#datepickerFin").datepicker("getDate") )
            {
                alert("La date de fin ne peut être inférieur à la date de début");
                $("#datepickerFin").datepicker('setDate', $( "#datepickerDebut" ).datepicker("getDate"));
            }
    }
</script>
 
<div style="float:left;padding-left: 20%">D&eacute;but période : <input type="text" id="datepickerDebut" /></div>
<div style="float:right;padding-right: 20%">Fin période : <input type="text" id="datepickerFin" onchange="checkDate()"/></div>
<br><br>
<table style="clear:both">
    <tr>
        <td>Bénéfice total</td>
        <td></td>
    </tr>
    <tr>
        <td> Liste deroulante Spectacle </td>
        <td></td>
    </tr>
    <tr>
        <td> Liste des 5 spectacles les plus rentables</td>
        <td></td>
    </tr>
</table>
<c:import url="Layout/footer.jsp"/>

