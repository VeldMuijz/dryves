<%-- 
    Document   : facebooklogin
    Created on : 14-apr-2013, 4:19:53
    Author     : H
--%>

<%@page import="com.facebook.Datv"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="ShowMyName();">
        <h1>Hello World!</h1>
        
        
        
        
      <script src="http://connect.facebook.net/en_US/all.js"></script>

    <fb:login-button autologoutlink="true" onlogin="OnRequestPermission();"   >
</fb:login-button>
<script language="javascript" type="text/javascript">
    FB.init({
        
        
        
        appId: '475117289226684',
        status: true, 
        cookie: true, 
        xfbml: true
    
    
    
    
    });   
    
    
    
    
    
</script>



<script language="javascript" type="text/javascript">
   
    
     function hivi() {
     
            document.getElementById('knop2').style.visibility = 'hidden';
       }
    
    
    
    
    
    
    
 function ShowMyName() {
        FB.api("/me",
                function (response) {
                    document.getElementById("idp").innerHTML=  response.id;
                     document.getElementById("naamp").innerHTML=  response.name;
                     
                     
                    document.getElementById("voornaamp").innerHTML=response.first_name;
            document.getElementById("achternaamp").innerHTML=response.last_name;
                document.getElementById("sexp").innerHTML=response.gender;
           
                     document.getElementById('knop1').style.visibility = 'hidden';
                      document.getElementById('knop2').style.visibility = 'visible';
                     
                });
         
    }
    
    
    
    
    function ShowMyName2() {
        
                
              document.getElementById("idh").value  = document.getElementById("idp").innerHTML;
              document.getElementById("naamh").value  = document.getElementById("naamp").innerHTML;
                              document.getElementById("voornaamh").value  = document.getElementById("voornaamp").innerHTML;
              document.getElementById("achternaamh").value  = document.getElementById("achternaap").innerHTML;
                document.getElementById("sexh").value  = document.getElementById("sexp").innerHTML;
    
                
         
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
  }
});
    
    
   
    

    
</script>
        
        
        
        
        
        
       
        
<p id="naamp"></p>   
       <br>
        
    <input type="hidden" id="naamp" value="" />    
        


     

   

<button id="knop1" onclick="ShowMyName();"> Klik hier om verder te gaan</button>


<form action="serv" method="get">
    
    <input type="hidden" id="idh" name="id" value="" />
    <input type="hidden" id="naamh" name="naam" value="" />
    <input type="hidden" id="voornaamh" name="voornaam" value="" />
    <input type="hidden" id="achternaamh" name="achternaam" value="" />
    <input type="hidden" id="sexh" name="sex" value="" />

    
    <input id="knop2" type="submit" value="Ga verder met Dryves" onclick="ShowMyName2()" />
    
    
    
</form>    
    </body>
</html>
