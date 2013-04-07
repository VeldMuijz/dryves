<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.UserBean"%>
<%@page import="Dryves.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<br />
<br />



<div id="showDiv">
    
    <%
    
    UserBean user = new UserBean();
    
    
    if (!user.isValid())
        
     {
         
         System.out.println("De gebruiker is niet gevalideert");
         
         %>
     Het inloggen is niet gelukt, probeer het opnieuw.
    <br />
    Bent u nog niet geregistreerd, klik hier.
     <%}
 %>
    


    <br /><br />
    
</div>


<form action="LoginServlet" method="get">
<table style="width:320px;">
    <tr>
        <td>
           Gebruikersnaam: 
        </td>
        <td>
          <input type="text" id="email" name="email" style="width:205px;"/>  
        </td>
    </tr>
    <tr>
        <td>Wachtwoord:</td>
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
