<%-- 
    Document   : nieuwbericht
    Created on : 4-mei-2013, 3:45:11
    Author     : H
--%>



<%@page import="java.util.List"%>
<%@page import="Dryves.Lid"%>
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

        <div class="background">

            <img src="images/background2.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
				<img src="images/Logo_Dryves.png" />
            </div>


			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="watisdryves"></jsp:param>
            </jsp:include>

			<div class="contentPanel">
                            <form action="Berichtverzenden" method="get">
                                
                                <%   
                                   
 //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
                                
                                
                                
                                
                                %>
                                
                                
                                 Naar: <input type="text" name="naar" />
                                Onderwerp: <input type="text" name="onderwerp"/>
                                Bericht <input type="text" name="inhoud"/>
                               
                                <input type="hidden" name="afzender" value="<% user.getLidnr(); %>"/>
                                <input type="hidden" name="datum"/>
                                <input type="hidden" name="gelezen"/>
                                
                                
                                <input type="submit" value="Verzenden"/>
                            </form>
			
                            
                            
                            
			</div>

        </div>

    </body>
</html>
