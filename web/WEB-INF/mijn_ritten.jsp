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
        <title>Mijn ritten</title>

    </head>
    <body>


        <div class="background">

            <img src="${currentSessionUser.getAchtergrond()}" />

        </div>
        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>

            <!-- Hier wordt de navigatie ingeladen -->
			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>

            <div class="contentPanel">         


                <c:choose>
                    <c:when test="${empty ritten}">
                        <fmt:message bundle="${rb}" key="geenritten" />
                    </c:when>
                    <c:otherwise>
                        <!-- Hier wordt de rittenlijst ingeladen -->

                        <c:forEach items="${ritten}" var="rit">
                            <div class="rittenlijst">
                                <table width="100%">

                                    <tr>
                                        <td><img src="images/from_icon.png" /> ${rit.startpunt}</td>
                                        
                                    </tr> 
                                    <tr>
                                        <td><img src="images/arrow_icon.png" /> ${rit.eindpunt}</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <img src="images/calendar_icon.png" /> ${rit.datumkort} <img src="images/clock_icon.png" /> ${rit.tijd} <img src="images/money_icon.png" /> ${rit.prijs}
                                        </td>
                                        <td style="float:right;"><button onclick="window.location = 'RitWijzigen?ritnr=${rit.ritnr}';"><fmt:message bundle="${rb}" key="ritwijzigen" /></button></td>
                                    </tr>

                                </table>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


			</div>
		</div>


	</body>
</html>
