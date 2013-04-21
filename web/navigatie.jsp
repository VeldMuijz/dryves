<%-- 
    Document   : navigation
    Created on : 5-apr-2013, 13:21:16
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
    </head>
    <body>
               <div class="navigation">
            
            <div class="loginButton"><button  onclick="window.location='login.jsp';">Login</button></div>
            
            <button onclick="window.location='index.jsp';">Home</button><button onclick="window.location='watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location='faq.jsp';">FAQ</button><button onclick="window.location='mijndryves.jsp';">Mijn Dryves</button><button onclick="window.location='registratie.jsp';">Registreer</button>
            
            
        </div>
    </body>
</html>
