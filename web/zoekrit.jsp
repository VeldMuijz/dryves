<%-- 
    Document   : zoekrit
    Created on : 28-apr-2013, 15:03:25
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                     <form action="RitZoeken" method="get" >
                    <b><fmt:message bundle="${rb}" key="zoekrit" />: <b> <input id='zoekveld' name="zoekrit" onchange="RitZoeken" placeholder="<fmt:message bundle="${rb}" key="placeholder" />" ></input> 
                            <button onclick="RitZoeken"><fmt:message bundle="${rb}" key="uitvoeren" /></button>
                            
                            
                          
                            
                            </form>   
    </body>
</html>
