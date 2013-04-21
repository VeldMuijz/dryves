<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Sessie"%>
<%@page import="Dryves.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Dryves.Sessie"%>
<%@page import="Dryves.Login"%>

<%@page import="com.facebook.Datv"%>

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
            
        
             function hivi() {
     
            document.getElementById('khovtg').style.visibility = 'hidden';
       }
    
    
            
            
        </script>
        
        
    </head>
    <body onload="hivi();">
        
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
      <script src="http://connect.facebook.net/en_US/all.js"></script>

    <fb:login-button autologoutlink="true" onlogin="OnRequestPermission(); ShowMyName() "   >
Login met Facebook</fb:login-button>
<script language="javascript" type="text/javascript">
    FB.init({
        
        
        
        appId: '475117289226684',
        status: true, 
        cookie: true, 
        xfbml: true
    
    
    
    
    });   
    
      FB.Event.subscribe('auth.login', function () {
         ShowMyName();
      });
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
</script>



<script language="javascript" type="text/javascript">
   
    
    
    
    
    
    
    
    
 function ShowMyName() {
        FB.api("/me",
                function (response) {
                    document.getElementById("idp").innerHTML=  response.id;
                     document.getElementById("naamp").innerHTML=  response.name;
                     
                     
                    document.getElementById("voornaamp").innerHTML=response.first_name;
            document.getElementById("achternaamp").innerHTML=response.last_name;
                document.getElementById("sexp").innerHTML=response.gender;
           
                  
                      document.getElementById('khovtg').style.visibility = 'visible';
                     
                });
         
    }
    
    
    
    
    function ShowMyName2() {
        
                
              document.getElementById("idh").value  = document.getElementById("idp").innerHTML;
              document.getElementById("naamh").value  = document.getElementById("naamp").innerHTML;
                              document.getElementById("voornaamh").value  = document.getElementById("voornaamp").innerHTML;
              document.getElementById("achternaamh").value  = document.getElementById("achternaap").innerHTML;
                document.getElementById("sexh").value  = document.getElementById("sexp").innerHTML;
    
                
           window.location = "mijndryves.jsp";
           document.getElementById('khovtg').style.visibility = 'visible';
           
    }
    
    
function rfr() {
window.location = facebooklogin.jsp;
ShowMyName();
}
//-->

    
    
    
    var message_str= 'Facebook JavaScript Graph API Tutorial';
FB.api('/me/feed', 'post', { message: message_str}, function(response) {
  if (!response || response.error) {  rfr();
      
  } else { 
    alert("Message successfully posted to your wall");
    ShowMyName();
  }
});
    
    
</script>
       <form action="serv" method="get">
    
    <input type="hidden" id="idh" name="id" value="" />
    <input type="hidden" id="naamh" name="naam" value="" />
    <input type="hidden" id="voornaamh" name="voornaam" value="" />
    <input type="hidden" id="achternaamh" name="achternaam" value="" />
    <input type="hidden" id="sexh" name="sex" value="" />

    
    
    
    
    


       
<p id="idp" ></p> 
   <p type="hidden" id="naamp" ></p>
   <p id="voornaamp"></p> 
   <p id="achternaamp" ></p>
   <p id="sexp"></p> 
   
   
   <br >
   
   
<input id="khovtg" type="submit" value="Ga verder met Dryves" onclick="ShowMyName2()" />
   
   </form> 
   
   
       </div>
        
        </div>
        
    </body>
</html>
