<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Sessie"%>
<%@page import="Dryves.Login"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<fmt:setLocale value="nl_NL" />
<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        
        <script>
            
        
            
            
            
        </script>
        
        
    </head>
    <body>
        
        <div class="background">
            
            <img src="images/background1.jpg" />
            
        </div>
        
        <div class="drvyesWrapper">
        
            <div class="logo">    
        <img src="images/Logo_Dryves.png" />
            </div>
        
        
        <div class="navigation">
            
            <div style="float:right; margin-right: -1px;"><button>Login</button></div>
            
            <button onclick="window.location='index.jsp';">Home</button><button onclick="window.location='watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location='faq.jsp';">FAQ</button><button onclick="window.location='registratie.jsp';">Registreer</button>
            
        </div>
        
        <div class="contentPanel">
            
Dit is de login page! 

<br /><br />

<form action="LoginServlet" method="get">
<table style="width:320px;">
    <tr>
        <td>
           <fmt:message bundle="${rb}" key="gebruikersnaam" />
        </td>
        <td>
          <input type="text" id="email" name="email" style="width:205px;"/>  
        </td>
    </tr>
    <tr>
        <td>
            <fmt:message bundle="${rb}" key="wachtwoord" />
        </td>
        <td><input type="text" id="wachtwoord" name="wachtwoord" style="width:205px;" /></td>
    </tr>
    <tr>
        <td>
            &nbsp;
        </td>
    </tr>
    <tr>
        <td>
            
        </td>
        <td><button type="submit" style="float:right;" />Login</button></td>
    </tr>
</table>

</form>

        </div>
        
        </div>
        
    </body>
</html>
