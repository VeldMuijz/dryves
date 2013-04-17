<%-- 
    Document   : navigation
    Created on : 5-apr-2013, 13:21:16
    Author     : Vincent
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
		<script type="text/javascript">
		</script>
		<script>

		</script>
    </head>
    <body>
		<div class="navigation">

            <div><button  class="loginButton" onclick="window.location = 'login.jsp';">Login</button></div>

      
				<button onclick="window.location = 'index.jsp';">Home</button>
				<button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button>
				<button onclick="window.location = 'faq.jsp';">FAQ</button>
				<button onclick="window.location = 'mijndryves.jsp';">Mijn Dryves</button>
				<button onclick="window.location = 'registratie.jsp';">Registreer</button>
	
				
            <div class="welkom" style="display: inline; float: right; width: 300px; height: 100%;">
				<div style="float: left;">Welkom, <br/>
					<c:out value="${currentSessionUser.vnaam}"/> <c:out value="${currentSessionUser.anaam}"/></div> 
					<div style="display: inline; width: 1px; height: 35px; background-color: black;"> </div>
					
					<div style="float: right;"> 0 :nieuwe berichten  <br/> <a href="/uitloggen.jsp" style="float: right;"> uitloggen </a></div>
					
			</div>
        </div>


    </body>
</html>
