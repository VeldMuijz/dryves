<%-- 
    Document   : index
    Created on : 24-mrt-2013, 19:59:48
    Author     : Kees van Heuven
--%>

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
            
            <div class="loginButton"><button  onclick="window.location='login.jsp';">Login</button></div>
            
            <button onclick="window.location='index.jsp';">Home</button><button onclick="window.location='watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location='faq.jsp';">FAQ</button>
            
        </div>
        
        <div class="contentPanel">
            
Schrijf je per direct in en word lid! Ontdek de vele voordelen die de Dryves community te bieden heeft. 

<br /><br />
            <form action="/dryves/proces.jsp" method=post>
            
                <div class="regformheader">
                        <font size=5>Registratie forumlier</font>
                        <br>
                        <font size=2 color="red"><sup>*</sup> Verplichte velden</font>
                </div>
                
                <div class="formInput">
                            <b>Voornaam<sup>*</sup></b> 
                            <br>
                            <input type="text" name="firstName" value="" size=20 maxlength=20>
                </div>
                <div class="formInput">
                            <b>Achternaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="lastName" value="" size=15 maxlength=20>
                </div>
                <div class="formInput">
                            <b>Tussenvoegsel<sup></sup></b>
                            <br>
                            <input type="text" name="insertion" value="" size=5 maxlength=20>
                </div>
                
                <div class="formInput">
                            <b>Rekening nummer<sup>*</sup></b> 
                            <br>
                            <input type="text" name="billingnumber" value="" size=10  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>E-Mail<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="" size=25  maxlength=125>
                </div>
                <div class="formInput">
                            <b>Straatnaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="street" value="" size=15 maxlength=20>            
                </div>
                <div class="formInput">
                            <b>Huisnummer<sup>*</sup></b>
                            <br>
                            <input type="text" name="homenumber" value="" size=5 maxlength=10>            
                </div>
                <div class="formInput">
                            <b>Postcode<sup>*</sup></b> 
                            <br>
                            <input type="text" name="zip" value="" size=10  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>Stad<sup>*</sup></b>
                            <br>
                            <input type="text" name="city" value="" size=15 maxlength=20>            
                </div>
                <div class="formInput">
                            <b>Gebruikersnaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="userName" size=20 value=""  maxlength=10>
                </div>
                <div class="formInput">
                       
                            <b>Wachtwoord<sup>*</sup></b> 
                            <br>
                            <input type="password" name="password1" size=10 value="" maxlength=10>
                </div>
                <div class="formInput">
                            <b>Bevestig wachtwoord<sup>*</sup></b>
                            <br>
                            <input type="password" name="password2" size=10 value="" maxlength=10>
                </div>
                                     
                <div class="formInputProv">
                            <b>In welke regio zal er veel gebruik worden gemaakt van Dryves?</b>
                            <br>
                            <input type="checkbox" name="faveTech" value="Noord-Holland">Noord-Holland    
                            <input type="checkbox" name="faveTech" value="Zuid-Holland">Zuid-Holland  
                            <input type="checkbox" name="faveTech" value="Limburg">Limburg<br>
                            <input type="checkbox" name="faveTech" value="Zeeland">Zeeland  
                            <input type="checkbox" name="faveTech" value="Utrecht">Utrecht  
                            <input type="checkbox" name="faveTech" value="Friesland">Friesland<br>
                </div>   
                <div class="formInput">
                            <b>Geslacht?</b>
                            <br>
                            <input type="radio" name="notify" value="M" checked>M 

                            <input type="radio" name="notify" value="V" > V
                </div>
                <div class="formInput">
                            <input type="submit" value="Submit"> <input type="reset"  
                                                                        value="Reset">
                </div>
                </div>
          
        </form>
    </body>
</html>