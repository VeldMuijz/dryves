<%-- 
Document : oops
 Created on : 15-mei-2013, 21:23:53
 Author : Jeroen
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        <title>Ooops!!</title>

    </head>
    <body>


		<div class="background">

            <img src="images/background1.jpg" />

        </div>

        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>

            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
				<jsp:param name="menu_active" value="mijndryves"></jsp:param>
			</jsp:include>




            <div class="contentPanel">         
				<h2>Oops!</h2>
				
				<p>Er is iets mis gegaan, ga terug door op vorige te drukken en controleer of alles goed ingevuld is. <br/>
				Probeer vervolgens opnieuw.</p>

            </div>
        </div>


    </body>
</html>
