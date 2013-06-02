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
                

        <fmt:message bundle="${rb}" key="welkom" /> ${currentSessionUser.getVnaam()} ${currentSessionUser.getAnaam()}

        <br /><br />
        
        <form id="adminPanel" action="Admindryves" method="Get">
        
        <fieldset>
            <legend>Achtergronden</legend> 

            1. <img src="images/background1_thumb.png" />  
            2. <img src="images/background2_thumb.png" />  
            3. <img src="images/background3_thumb.png" />
 
        </fieldset>
        
        <br />
        
        <fieldset>
            
            <legend>Achtergrond</legend>
            
            <input type="text" name="achtergrond" value="${currentSessionUser.getAchtergrond()}" />
            
        </fieldset>
            
            <br />
        
        <fieldset>
            
            <legend>Prijs per kilometer</legend>
            
            <input type="text" name="ritprijs" value="${currentSessionUser.getRitprijs()}" />
            
        </fieldset>
            
            <br />
            
        <button type ="submit">Wijzig</button>
            
        </form>
            
         
            </div>
        </div>


</body>
</html>
