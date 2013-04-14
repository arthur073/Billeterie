<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        var pos = getElementPosition(obj);

        if (obj.className === "sit") {
            obj.className = "sat";
            document.getElementById("selected").value += convertToInt(pos) + "/";
            //alert(pos.x + "," + pos.y+"->"+convertToInt(pos));
        } else {
            obj.className = "sit";
            document.getElementById("selected").value = document.getElementById("selected").value.replace(convertToInt(pos) + "/", '');
        }
    }

</script>


<h2 align="center" class="titre"> Choisissez vos places </h2>


Cliquez sur les places que vous désirez : <br/>


<table id="chairs" >
    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 
    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 
    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 
    <tr class="sitstop"/>

    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 

    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 
    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 
    <tr>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sitstop"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
        <td class="sit" onclick="selectChair(this);"/>
    </tr> 

</table>


<div id="scene">SCÈNE</div>

<form action="ReservationControleur"  class="reserverForm" method="post">
    <input type="text" id="selected" name="places"/> <br/>
    <input type="submit" name="action" label="validerPlaces" value="Valider mes places" class="btnReserver"/>
</form>


<c:import url="Layout/footer.jsp"/>

