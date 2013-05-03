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
    if (window.addEventListener) {
        var kkeys = [], konami = "38,38,40,40,37,39,37,39,66,65";
        window.addEventListener("keydown", function(e) {
            kkeys.push(e.keyCode);
            if (kkeys.toString().indexOf(konami) >= 0) {
                kkeys = [];
                var name = $("table.about tr td img").attr('src');
                if (name[name.length - 1] === "2") {
                    return;
                }
                $("table.about tr td img").each(function(i) {
                    var image = $(this), originalSrc = image.attr('src');
                    image.attr('src', originalSrc + "2");
                    image.attr('class', "profilePic2");
                });
                $("#flashDiv").html("<strong> Bravo </strong> Vous avez découvert la page secrête");
                $("#flashDiv").attr("class", "success");
                $("table.about tr.top").append('<td><img src="images/soeurThib.jpg" class="profilePic2"/></td>');
                $("table.about tr.bottom").append('<td>La soeur à Thibault</td>');
            }
        }, true);
    }



</script>




<p class="about">Bienvenue sur le site de réservation et d'achats de places du théâtre E=MC2,
    vous pouvez ici consulter la liste des spectacles à venir ainsi que réserver
    vos places afin d'assister à une représentation.</p>


<p class="about">Ce site vous est présenté par la dream team ACVL :</p>
<table class="about">
    <tr class="top">
        <td>
            <img src="images/michel.jpg" class="profilePic"/>
        </td>
        <td>
            <img src="images/arthur.jpg" class="profilePic"/>
        </td>
        <td>
            <img src="images/jany.jpg" class="profilePic"/>
        </td>
        <td>
            <img src="images/thibault.jpg" class="profilePic"/>
        </td>
    </tr>
    <tr class="bottom">
        <td>Michou </td>
        <td>Arthur </td>
        <td>Jany </td>
        <td>Thibault </td>            
    </tr>
</table>

<p class="about disclaimer">
    Disclaimer : this website is provided as is without warranty of any kind,
    either express or implied, including but not limited to the implied warranties
    of merchantability and fitness for a particular purpose. In no event shall
    the North Front Range Metropolitan Planning Organization be liable for any 
    damages whatsoever including direct, indirect, incidental consequential, 
    loss of business profits, or special damages.</p>
<img src="images/salle_theatre.jpeg" class="theatreImg"/>

<p class="about">
    Nous promettons également qu'aucun pc n'a été maltraité et que nous nous livrons
    à des sacrifices rituels en faveur de jsp chaque jour. </p>



<c:import url="Layout/footer.jsp"/>


