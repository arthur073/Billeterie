<%-- 
    Document   : admin
    Created on : 01 mai 2013, 11:34:17
    Author     : michel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<style type="text/css" media="screen">
    @import url(http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css);
    @import url(statsstyle.css);
</style>

Page à construire..
<ul>
    <li> <a href="AdminControleur?action=archiverBD" id="li1">Archiver BD</a> </li>
    <li> <a href="AdminControleur?action=annulerResaNonPayees" id="li1">Annuler réservations non payées à temps</a> </li>
</ul>

<c:import url="Layout/footer.jsp"/>

