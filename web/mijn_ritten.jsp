<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>Mijn ritten</title>


 </head>
 <body>
 <h1>Hello World!</h1>


<table>
    <c:forEach items="${ritten}" var="rit">
        <tr>
            <td>${rit.ritnr}</td>
            <td>${rit.startpunt}</td>
            <td>${rit.eindpunt}</td>
            <td>${rit.prijs}</td>
        </tr>
    </c:forEach>
</table>


 </body>
</html>