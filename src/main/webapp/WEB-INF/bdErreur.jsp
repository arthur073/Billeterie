<%-- 
    Document   : bdErreur
    Created on : Mar 22, 2012, 12:32:53 AM
    Author     : reignier
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/REC-html40/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erreur BD</title>
    </head>
    <body>
        <h1 style="text-align: center">Erreur</h1>
        <img src="images/scared.png"/>
        <p>Une erreur d'accès à la base de données vient de se produire</p>
        <p>Message : <br/>
        <div style="font-family: monospace">${erreurMessage}</div>
        </p>
</body>
</html>
