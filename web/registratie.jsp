
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
        
        
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script>

function readURL(input) {

if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
        $('#previewImg')
            .attr('src', e.target.result)
            .width(80);            
    };
reader.readAsDataURL(input.files[0]);

}else{
          var filename = "";
         filename = "file:\/\/"+input.value;
         document.form2.previewImg.src=filename;
         document.form2.previewImg.style.width="130px";

 }
}
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
            
Schrijf je in en word lid! Ontdek de vele voordele die de Dryves community te bieden heeft. 

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
                            <input type="text" name="vnaam" value="Katja" size=15 maxlength=20>
                </div>
                <div class="formInput">
                            <b>Achternaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="anaam" value="Schruurman" size=15 maxlength=20>
                </div>
                
                <div>
               <b>Kies een foto:</b>
               <input type='file' onchange="readURL(this);" />
               <img id="previewImg" src="#"/>
               </div>
               <div>                          
                <button onclick="window.location='index.jsp';">Upload</button>
                <div class="formInput">
                            <b>Tussenvoegsel<sup></sup></b>
                            <br>
                            <input type="text" name="tvoegsel" value="van" size=5 maxlength=20>
                </div>
                
                <div class="formInput">
                            <b>Rekening nummer<sup>*</sup></b> 
                            <br>
                            <input type="text" name="reknr" value="8332595" size=10  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>Telefoonnummer<sup>*</sup></b> 
                            <br>
                            <input type="text" name="telnr" value="0681399020" size=10  maxlength=10>
                </div>
                
                <div class="formInput">
                            <b>E-Mail<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="kvheuv@gmail.com" size=25  maxlength=30>
                </div>
                <div class="formInput">
                            <b>Straatnaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="straat" value="Dorpsstraat" size=15 maxlength=20>            
                </div>
                <div class="formInput">
                            <b>Huisnummer<sup>*</sup></b>
                            <br>
                            <input type="text" name="huisnummer" value="806" size=5 maxlength=8>            
                </div>
                <div class="formInput">
                            <b>Postcode<sup>*</sup></b> 
                            <br>
                            <input type="text" name="postcode" value="1566ev" size=5  maxlength=8>
                </div>
                
                <div class="formInput">
                            <b>Stad<sup>*</sup></b>
                            <br>
                            <input type="text" name="stad" value="Assendelft" size=15 maxlength=20>            
                </div>

                <div class="formInput">
                       
                            <b>Wachtwoord<sup>*</sup></b> 
                            <br>
                            <input type="password" name="wachtwoord" size=10 value="123" maxlength=30>
                </div>
                <div class="formInput">
                            <b>Bevestig wachtwoord<sup>*</sup></b>
                            <br>
                            <input type="password" name="wachtwoord2" size=10 value="123" maxlength=30>
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
                            <input type="radio" name="locale" value="nl_NL" checked> <img src="images/nl_NL.png" />
                            <input type="radio" name="locale" value="en_GB"> <img src="images/en_GB.png" />
                </div>        
                <div>
                <input type="submit" value="Aanmelden"> <input type="reset" value="Reset">
                </div>
                           </form>
                </div>
                </div>
    </body>
</html>
