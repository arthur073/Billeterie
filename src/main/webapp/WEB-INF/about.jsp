<%-- 
    Document   : about
    Created on : 16 avr. 2013, 13:00:17
    Author     : arthur
--%>

<%@page import="vue.FlashImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vue.RangToZone"%> 

<c:import url="Layout/header.jsp"/>    

<script>
    if ( window.addEventListener ) {
        var kkeys = [], konami = "38,38,40,40,37,39,37,39,66,65";
        window.addEventListener("keydown", function(e){
            kkeys.push( e.keyCode );
            if ( kkeys.toString().indexOf( konami ) >= 0 ) {
                 alert("La requ�te suivante a bien �t� ex�cut�e :\nUPDATE notes2AEnsimag\nSET noteWeb=20\n"
                        + "WHERE login = 'kuhmm' OR login = 'vernadat' OR login = 'belluzj' OR login = 'vergera'");              
            }
        }, true);
    }
    
</script>



<p class="about">Bienvenue sur le site de r�servation et d'achats de places du th��tre E=MC2,
vous pouvez ici consulter la liste des spectacles � venir ainsi que r�server
vos places afin d'assister � une repr�sentation.</p>


<p class="about">Ce site vous est pr�sent� par la dream team ACVL :</p>
<table class="about">
    <tr>
        <td>
             <img src="images/michel.jpg" class="profilePic"/>
        </td>
        <td>
             <img src="images/michel.jpg" class="profilePic"/>
        </td>
        <td>
             <img src="images/michel.jpg" class="profilePic"/>
        </td>
        <td>
             <img src="images/michel.jpg" class="profilePic"/>
        </td>
    </tr>
    <tr>
        <td>Michou </td>
        <td>Michou </td>
        <td>Michou </td>
        <td>Michou </td>            
    </tr>
</table>

<p class="about">
Disclaimer : this website is provided as is without warranty of any kind,
either express or implied, including but not limited to the implied warranties
of merchantability and fitness for a particular purpose. In no event shall
the North Front Range Metropolitan Planning Organization be liable for any 
damages whatsoever including direct, indirect, incidental consequential, 
loss of business profits, or special damages.</p>

<p class="about">
Nous promettons �galement qu'aucun pc n'a �t� maltrait� et que nous nous livrons
� des sacrifices rituels en faveur de jsp chaque jour. </p>

<img src="images/salle_theatre.jpeg" class="theatreImg"/>
<c:import url="Layout/footer.jsp"/>

