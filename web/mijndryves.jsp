<%-- 
    Document   : mijndryves
    Created on : 27-mrt-2013, 18:10:27
    Author     : Vincent
--%>

<%@page import="Dryves.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
    </head>
    <body>

        <div class="background">

            <img src="images/background1.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>

            <jsp:include page="navigatie.jsp"  flush="true" />






            <div class="contentPanel">
                

                
                <button onclick="window.location = 'mijndryves_gegevens.jsp';">Mijn gegevens</button>
                <button onclick="window.location = 'rit_plannen.jsp';">Mijn berichten</button>
                <button onclick="window.location = 'rit_plannen.jsp';">Mijn ritten</button>


        <% UserBean currentUser = (UserBean) (session.getAttribute("currentSessionUser"));%>

        Welkom <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>

            </div>
        </div>


</body>
</html>
