<%-- 
    Document   : admin
    Created on : 01 mai 2013, 11:34:17
    Author     : michel
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.io.FileWriter"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="Layout/header.jsp"/>

<style type="text/css" media="screen">
    @import url(http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css);
    @import url(statsstyle.css);
</style>

<center>
    <button class="btnBlack" onclick='document.location.href="AdminControleur?action=annulerResaNonPayees"'>Annuler réservations non payées à temps</button>
    <br/><br/>
    <button class="btnBlack" onclick='document.location.href="AdminControleur?action=archiverBD"'>Archiver BD</button>
    <br/><br/>
    <button class="btnBlack" onclick='document.location.href="AdminControleur?action=peuplerBD"'>Peupler BD</button>
    <br/>
</center>
<%

    String backupPresent = (String)request.getAttribute("backupFile");
    if (backupPresent != null) {
        response.reset();
        response.setHeader("Content-type","application/octet-stream");
        response.setHeader("Content-disposition","attachment; filename=backup_"+Calendar.getInstance().getTime().toString().replace(' ', '_')+".txt");
        out.clear();
        out.write(backupPresent);
        out.close();
    }
%>
<c:import url="Layout/footer.jsp"/>

