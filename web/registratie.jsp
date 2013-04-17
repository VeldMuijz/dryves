
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
        
        
        	<jsp:include page="navigatie.jsp"  flush="true" />
        
        <div class="contentPanel">
            
Schrijf je per direct in en word lid! Ontdek de vele voordele die de Dryves community te bieden heeft. 

<br /><br />
            <form action="Registreren" method=get>
            
                <div class="regformheader">
                        <font size=5>Registratie formulier</font>
                        <br>
                        <font size=2 color="red"><sup>*</sup> Verplichte velden</font>
                </div>
                
                <div class="formInput">
                            <b>Voornaam<sup>*</sup></b> 
                            <br>
                            <input type="text" name="vnaam" value="Kees" size=15 maxlength=20>
                </div>
                <div class="formInput">
                            <b>Achternaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="anaam" value="" size=15 maxlength=20>
                </div>
                
                <div>
               <b>Kies een foto:</b>
               <INPUT NAME="file" TYPE="file">
               </div>
               <div>
               
               </div>
               <div>
               <td colspan="2" align="center"><input type="submit" value="Upload">
               </div>
                
                <div class="formInput">
                            <b>Tussenvoegsel<sup></sup></b>
                            <br>
                            <input type="text" name="tvoegsel" value="" size=5 maxlength=20>
                </div>
                
                <div class="formInput">
                            <b>Rekening nummer<sup>*</sup></b> 
                            <br>
                            <input type="text" name="reknr" value="" size=10  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>Telefoonnummer<sup>*</sup></b> 
                            <br>
                            <input type="text" name="telnr" value="" size=10  maxlength=10>
                </div>
                
                <div class="formInput">
                            <b>E-Mail<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="" size=25  maxlength=25>
                </div>
                <div class="formInput">
                            <b>Straatnaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="straat" value="" size=15 maxlength=20>            
                </div>
                <div class="formInput">
                            <b>Huisnummer<sup>*</sup></b>
                            <br>
                            <input type="text" name="huisnummer" value="" size=5 maxlength=8>            
                </div>
                <div class="formInput">
                            <b>Postcode<sup>*</sup></b> 
                            <br>
                            <input type="text" name="postcode" value="" size=5  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>Stad<sup>*</sup></b>
                            <br>
                            <input type="text" name="stad" value="" size=15 maxlength=20>            
                </div>

                <div class="formInput">
                       
                            <b>Wachtwoord<sup>*</sup></b> 
                            <br>
                            <input type="password" name="wachtwoord" size=10 value="" maxlength=30>
                </div>
                <div class="formInput">
                            <b>Bevestig wachtwoord<sup>*</sup></b>
                            <br>
                            <input type="password" name="wachtwoord2" size=10 value="" maxlength=30>
                </div>
                
                <div class="formInput">
                            <b>Geslacht?</b>
                            <br>
                            <input type="radio" name="geslacht" value="M" checked>M 
                            <input type="radio" name="geslacht" value="V"> V
                </div>
                
                <div class="formInput">
                            <b>Taal?</b>
                            <br>
                            <input type="radio" name="locale" value="NL" checked> <img src="images/nl_NL.png" />
                            <input type="radio" name="locale" value="EN"> <img src="images/en_EN.png" />
                </div>

                           
                           <input type="submit" value="Aanmelden"> <input type="reset" value="Reset">
                           </form>
                </div>
                </div>
    </body>
</html>
