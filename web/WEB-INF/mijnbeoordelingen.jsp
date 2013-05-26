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
						<fmt:message bundle="${rb}" key="geenbeoordeling" />
					</c:when>
					<c:otherwise>
						<c:set var="counter" value="0"/>
						<h2>Jouw huidige beoordeling door andere leden: ${currentSessionUser.beoordeling}</h2>
						<c:forEach items="${beoordelingen}" var="beoordeling">
							<div class="rittenlijst">

								<table>
									<tr><td><fmt:message bundle="${rb}" key="beoordeelddoor" /></td> <td> ${beoordelaars[counter].vnaam} ${beoordelaars[counter].tvoegsel} ${beoordelaars[counter].anaam}</tr>
									<tr><td><fmt:message bundle="${rb}" key="beoordeeldop" /></td> <td>${beoordeling.korteDatum} ${beoordeling.korteTijd}</td></tr>
									<tr><td><fmt:message bundle="${rb}" key="gezelligheid" /></td> <td>${beoordeling.gezelligheid}</td></tr>
									<tr><td><fmt:message bundle="${rb}" key="rijstijl" /></td><td>${beoordeling.rijstijl}</td></tr>
									<tr><td><fmt:message bundle="${rb}" key="betrouwbaarheid" /></td><td>${beoordeling.betrouwbaarheid}</td> </tr>
									<tr><td><fmt:message bundle="${rb}" key="stiptheid" /></td><td>${beoordeling.stiptheid}</td></tr>
									<tr><td><fmt:message bundle="${rb}" key="opmerking" /></td><td>${beoordeling.commentaar}</td></tr>
									<tr style="font-size: 25px;"><td><fmt:message bundle="${rb}" key="eindbeoordeling" /></td><td>${beoordeling.waardering}</td></tr>
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
