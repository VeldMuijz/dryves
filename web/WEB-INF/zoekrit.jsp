<%-- 
    Document   : zoekrit
    Created on : 28-apr-2013, 15:03:25
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
        <link type="text/css" rel="stylesheet" href="css/dryver.css" />
        <title>Rit zoeken</title>
    </head>
    <body>
       
        
        <form action="RitZoeken" method="get" >
            <input class="zoektextveld" name="zoekritbegin" onchange="RitZoeken" placeholder="<fmt:message bundle="${rb}" key="placeholderbegin" />" ></input><!--<button onclick="RitZoeken" class="heliosActionBarIconButton"><img src="images/zoom_grey.png" /></button>-->
            <input class="zoektextveld" name="zoekriteind" onchange="RitZoeken" placeholder="<fmt:message bundle="${rb}" key="placeholdereind" />" ></input><button onclick="RitZoeken" class="heliosActionBarIconButton"><img src="images/zoom_grey.png" /></button>
        </form>   
    </body>
</html>
