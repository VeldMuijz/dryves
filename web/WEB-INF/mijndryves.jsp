<%-- 
    Document   : mijndryves
    Created on : 27-mrt-2013, 18:10:27
    Author     : Vincent
--%>

<%@page import="java.util.Locale"%>
<%@page import="Dryves.Model.Lid"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
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
                
            <fmt:message bundle="${rb}" key="ubentingelogdals" /> ${currentSessionUser.getVnaam()} ${currentSessionUser.getAnaam()}
            
            <br /><br />
            
            <div class="mijndryves">
                
                <button onclick="window.location = 'MijnGegevens';"><img src="images/personal.png" /> <br /><br /> <fmt:message bundle="${rb}" key="mijngegevens" /></button><button onclick="window.location = 'RitPlannen';"><img src="images/mapicon.png" /> <br /><br /> <fmt:message bundle="${rb}" key="planrit" /></button><button onclick="window.location = 'MijnRitten';"><img src="images/tire.png" /> <br /><br /> <fmt:message bundle="${rb}" key="mijnritten" /></button><button onclick="window.location = 'MijnAankopen';"><img src="images/cart.png" /> <br /><br /> <fmt:message bundle="${rb}" key="mijnaankopen" /></button><button onclick="window.location = 'MijnBerichten';"><img src="images/envelope.png" /> <br /><br /> <fmt:message bundle="${rb}" key="mijnberichten" /></button><button onclick="window.location = 'MijnBeoordelingen';"><img src="images/rateicon.png" /> <br /><br /> <fmt:message bundle="${rb}" key="mijnbeoordeling" /></button>
                
            </div>      
            
            
                                                         
            
                            
            </div>
        </div>
</body>
</html>
