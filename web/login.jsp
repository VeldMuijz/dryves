<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Lid"%>
<%@page import="Dryves.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Dryves.Login"%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        
   
        
        <script>
           
        
             function verbergKnopVerde() {
     
            document.getElementById('GaVerderKnop').style.visibility = 'hidden';
       }
    
    
            
            
        </script>
        
        
    </head>
    <body onload="verbergKnopVerder();">
        
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

<form action="LoginServlet" method="post">
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

        
        
        
        
        
<fb:profile-pic uid="loggedinuser" facebook-logo="true" linked="false" 
width="300" height="400"></fb:profile-pic>


        
        
        
        
        
        
        
        
        
        
        
</form>
      <script src="http://connect.facebook.net/en_US/all.js"></script>

    <fb:login-button autologoutlink="true" onlogin="OnRequestPermission(); VanFaceNaarP() "   >
Login met Facebook</fb:login-button>
<script language="javascript" type="text/javascript">
    FB.init({
        
        
       
        appId: '475117289226684',
        status: true, 
        cookie: true, 
        xfbml: true
    
    
    
    
    });   
    
      FB.Event.subscribe('auth.login', function () {
         VanFaceNaarP();
      });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</script>



<script language="javascript" type="text/javascript">
   
    
    
    
    
    
    
    
    // In deze functie wordt de gegevens die van Facebook afkomen in de paragraph opgeslagen
    // Na het inloggen kan je op dezelfde pagina je naam voornaam etc zien
 function VanFaceNaarP() {
       
        FB.api("/me",
                function (response) {
                    document.getElementById("idp").innerHTML=  response.id;
                    document.getElementById("naamp").innerHTML=  response.name;
                    document.getElementById("voornaamp").innerHTML=response.first_name;
                    document.getElementById("achternaamp").innerHTML=response.last_name;
                    document.getElementById("sexp").innerHTML=response.gender;
                    document.getElementById("emailp").innerHTML=response.email;
                    document.getElementById('GaVerderKnop').style.visibility = 'visible';
                     
                });
         
    }
    
    
    // De waarde die in de paragraaf zijn opgeslagen worden hier doorverwezen naar de form 
    // zodat deze verstuurd kunnen worden naar een servlet
    function VanPnaarHF() {
        
                
              document.getElementById("idh").value  = document.getElementById("idp").innerHTML;
              document.getElementById("naamh").value  = document.getElementById("naamp").innerHTML;
              document.getElementById("voornaamh").value  = document.getElementById("voornaamp").innerHTML;
              document.getElementById("achternaamh").value  = document.getElementById("achternaamp").innerHTML;
              document.getElementById("sexh").value  = document.getElementById("sexp").innerHTML;
              document.getElementById("emailh").value  = document.getElementById("emailp").innerHTML;
              document.getElementById('GaVerderKnop').style.visibility = 'visible';
           
    }
         // Met deze functie vargen we toestemming en vargen we wat zijn email is.
     function getEmail() {  
    FB.login(function(response) {
      if (response.session && response.perms) {
        FB.api('/me',  function(response) {
            
          }
        );
      }
    } , {perms:'email'}); 
}

     // De refresh functie zodat de velden leeg worden
    function rfr() {
   window.location = facebooklogin.jsp;
VanFaceNaarP();
}
//-->

    
    
    
    var message_str= 'Facebook JavaScript Graph API Tutorial';
FB.api('/me/feed', 'post', { message: message_str}, function(response) {
  if (!response || response.error) {  rfr();
      
  } else { 
    alert("Message successfully posted to your wall");
    VanFaceNaarP();
  }
});
    
    
</script>
       <form action="FacebookLoginServlet" method="get">
    
    <input type="hidden" id="idh" name="id" value="" />
    <input type="hidden" id="naamh" name="naam" value="" />
    <input type="hidden" id="voornaamh" name="voornaam" value="" />
    <input type="hidden" id="achternaamh" name="achternaam" value="" />
    <input type="hidden" id="sexh" name="sex" value="" />
    <input type="hidden" id="emailh" name="email" value="" />

    
    
    
    
    


      
<p id="idp" ></p> 
   <p type="hidden" id="naamp" ></p>
   <p id="voornaamp"></p> 
   <p id="achternaamp" ></p>
   <p id="sexp"></p> 
   <p id="emailp"></p> 
   
   
   <br >
   
   
<input id="GaverderKnop" type="submit" value="Ga verder met Dryves" onclick="VanPnaarHF();" />
   
   </form> 
   
   
       </div>
        
        </div>
        
    </body>
</html>

        
        
