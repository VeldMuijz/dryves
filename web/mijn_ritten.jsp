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


 Sessie voornaam: <c:out value="${Sessie.getVnaam()}"/>
 Sessie achternaam: <c:out value="${Sessie.getAnaam()}"/>
 Sessie lidnr: <c:out value="${Sessie.getlidnr()}"/>


 </body>
</html>