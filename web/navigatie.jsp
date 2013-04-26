<%-- 
    Document   : navigation
    Created on : 5-apr-2013, 13:21:16
    Author     : Vincent
--%>

<%@page import="Dryves.Sessie"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			

            <div><button  class="loginButton" onclick="window.location = 'login.jsp';">Login</button></div>
			

			<button onclick="window.location = 'index.jsp';" <% if(request.getParameter("menu_active").equals("home")){  %> class="active" <% } %> >Home</button>
			<button onclick="window.location = 'watisdryves.jsp';" <% if(request.getParameter("menu_active").equals("watisdryves")){  %> class="active" <% } %> >Wat is dryves</button>
                       	<button onclick="window.location = 'faq.jsp';" <% if(request.getParameter("menu_active").equals("faq")){  %> class="active" <% } %> >FAQ</button>
			<button onclick="window.location = 'mijndryves.jsp';">Mijn Dryves</button>
			<button onclick="window.location = 'registratie.jsp';">Registreer</button>

			
			
			<c:if test="${currentSessionUser.lidnr > 0}"> 
            <div class="welkom" id="welkom" style=" float: right; width: 300px; height: 100%;">
				
				<div style="float: left; margin-right: 5px;">Welkom, <br/>
					<div id="anaam"> <label id="vnaam"></label></div>
					<c:out value="${currentSessionUser.vnaam}"/> <c:out value="${currentSessionUser.anaam}"/></div> 
				
				<!--					TODO Hier nog de actuele aantal ongelezen berichten ophalen-->
				<div style="float: right; margin-right: 5px;"> 
					<a href="mijnberichten.jsp"> 0</a> :nieuwe berichten  <br/> 
					<a href="uitloggen.jsp" style="float: right;"> uitloggen </a>
				</div>
			
			</div>
			</c:if>
						
        </div>


    </body>
</html>
