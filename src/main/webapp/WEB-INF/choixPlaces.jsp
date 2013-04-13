<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>    

<h2 align="center" class="titre"> Choisissez vos places </h2>

<script>
    function click() {
        alert("hi");
    }
</script>

Choisissez vos places mes amis : 

<table id="selectable">
    <tr>
        <td class="ui-state-default" onclick="click();">1</td>
        <td class="ui-state-default">1</td>
        <td class="ui-state-default">1</td>
        <td class="ui-state-default">1</td>
    </tr> 
    <tr>
        <td class="ui-state-default">2</td>
        <td class="ui-state-default">2</td>
        <td class="ui-state-default">2</td>
        <td class="ui-state-default">2</td>
    </tr>
</table>





<c:import url="Layout/footer.jsp"/>

