<%-- 
    Document   : index
    Created on : Mar 22, 2012, 12:08:32 AM
    Author     : reignier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//FR" "http://www.w3.org/TR/REC-html40/loose.dtd">

<% request.getSession().setAttribute("LoggedIn", false); %>
<% request.getSession().setAttribute("FailedLogIn", false); %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:forward page="/PagesControleur?action=listeSpectacles"/>
    </body>
</html>
