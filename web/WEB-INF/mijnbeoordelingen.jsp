<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
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
        <title>Mijn beoordelingen</title>

    </head>
    <body>


        <div class="background">

            <img src="${currentSessionUser.getAchtergrond()}" />

        </div>
        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>
			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>




            <div class="contentPanel">
				<c:choose>
					<c:when test="${empty beoordelingen}"> 
						<h2>U bent nog niet beoordeeld</h2>
						<p>Kom hier later nog eens terug</p>
					</c:when>
					<c:otherwise>
						<c:set var="counter" value="0"/>
						<h2>Jouw huidige beoordeling door andere leden: ${currentSessionUser.beoordeling}</h2>
						<c:forEach items="${beoordelingen}" var="beoordeling">
							<div class="rittenlijst">

								<table>
									<tr><td>Beoordeeld door: </td> <td> ${beoordelaars[counter].vnaam} ${beoordelaars[counter].tvoegsel} ${beoordelaars[counter].anaam}</tr>
									<tr><td>Beoordeeld op: </td> <td>${beoordeling.korteDatum} ${beoordeling.korteTijd}</td></tr>
									<tr><td>Gezelligheid: </td> <td>${beoordeling.gezelligheid}</td></tr>
									<tr><td>Rijstijl: </td><td>${beoordeling.rijstijl}</td></tr>
									<tr><td>Betrouwbaarheid: </td><td>${beoordeling.betrouwbaarheid}</td> </tr>
									<tr><td>Stiptheid: </td><td>${beoordeling.stiptheid}</td></tr>
									<tr><td>Commentaar: </td><td>${beoordeling.commentaar}</td></tr>
									<tr style="font-size: 25px;"><td>Eindbeoordeling: </td><td>${beoordeling.waardering}</td></tr>
								</table>

							</div>
							<c:set var="counter" value="${counter + 1 }"/>
						</c:forEach>

					</c:otherwise>
				</c:choose>

            </div>
        </div>


    </body>
</html>
