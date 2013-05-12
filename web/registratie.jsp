<%-- 
    Document   : index
    Created on : 24-mrt-2013, 19:59:48
    Author     : Kees van Heuven
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>

        <script>
            function validateForm() {
                
                //var vnaam = document.getElementById("vnaam").value;                                            
                var vnaam = document.forms["RegistratieForm"]["vnaam"].value;
                var anaam = document.forms["RegistratieForm"]["anaam"].value;
                var reknr = document.forms["RegistratieForm"]["reknr"].value;
                var telnr = document.forms["RegistratieForm"]["telnr"].value;
                var email = document.forms["RegistratieForm"]["email"].value;
                var atpos=email.indexOf("@");
                var dotpos=email.lastIndexOf(".");
                var straat = document.forms["RegistratieForm"]["straat"].value;
                var huisnummer = document.forms["RegistratieForm"]["huisnummer"].value;
                var postcode = document.forms["RegistratieForm"]["postcode"].value;
                var stad = document.forms["RegistratieForm"]["stad"].value;
                var wachtwoord = document.forms["RegistratieForm"]["wachtwoord"].value;
                var wachtwoord2 = document.forms["RegistratieForm"]["wachtwoord2"].value;
                
                console.log(vnaam);

                var check = true;

                if (vnaam === "")
                {
                    document.getElementById("vnaamerror").innerHTML = "Geen voornaam ingevuld!";                    
                    check = false;
                };
                if (anaam === "") {                                
                    document.getElementById("anaamerror").innerHTML = "Geen achternaam ingevuld!";
                    check = false;
                };
                if (reknr === "" || (/[^0-9]+$/.test(reknr))) {
                   document.getElementById("reknrerror").innerHTML = "Geen geldig rekeningnummer ingevuld!";
                   check = false;
                };
                if(telnr === "" || (/[^0-9]+$/.test(telnr))) {		
                    document.getElementById("telnrerror").innerHTML = "Geen geldig telefoonnummer ingevuld!"; 
                    check = false;                
                };                       
                if (email === "" || atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {                                                      
                    document.getElementById("emailerror").innerHTML = "Geen geldig of geen email adres ingevuld!";
                    check = false;                                    
                };
                if (straat === "") {
                    document.getElementById("straaterror").innerHTML = "Geen straatnaam ingevuld!";
                    check = false;                    
                };
                if (huisnummer === "") {
                    document.getElementById("huisnummererror").innerHTML = "Geen huisnummer ingevuld!";
                    check = false;
                };
                if (postcode === "") {
                    document.getElementById("postcodeerror").innerHTML = "Geen postcode ingevuld!";
                    check = false;
                };
                if (stad === "") {
                    document.getElementById("staderror").innerHTML = "Geen stad ingevuld!";
                    check = false;
                };
                if (wachtwoord === "") {
                    document.getElementById("wachtwoorderror1").innerHTML = "Geen wachtwoord ingevuld!";
                    check = false;
                };
                if (wachtwoord2 === "") {
                    document.getElementById("wachtwoorderror2").innerHTML = "Geen wachtwoord ingevuld!";
                    check = false;
                };
                if (wachtwoord !== wachtwoord2) {
                    document.getElementById("wachtwoorderror3").innerHTML = "Wachtwoorden komen niet overeen!";  
                    check = false;
                };
                
                
            
            
                return check;
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


            <jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="registratie"></jsp:param>
            </jsp:include>

            <div class="contentPanel">

                <fmt:message bundle="${rb}" key="sloganregistratie" />
                
                <br /><br />
                
                <form id="RegistratieForm" action="Registreren" method="get" onsubmit="return validateForm();">
                    
                    <div class="regformheader">
                        <font size=5><fmt:message bundle="${rb}" key="registratieformulier" /></font>
                        <br>
                        <font size=1 color="red"><sup>*</sup><fmt:message bundle="${rb}" key="verplichtevelden" /></font>
                    </div>
                    
                        <table class="registratietabel">
                            
                            <!--<table class="registratietabel">-->

                            <tr>
                                <td><input class="zoektextveld" type="text" name="vnaam" value="" size=15 maxlength=20 placeholder="<fmt:message bundle="${rb}" key="voornaam" />"></td>
                                <td><font id="vnaamerror" size=1 color="red"> </font></td>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="straatnaam" />" type="text" name="straat" value="" size=15 maxlength=20></td>
                                <td><font id="straaterror" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td><input class="zoektextveld" type="text" name="tvoegsel" value="" size=5 maxlength=20 placeholder="<fmt:message bundle="${rb}" key="tussenvoegsel" />"></td>
                                <td></td>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="huisnummer" />" type="text" name="huisnummer" value="" size=5 maxlength=8></td>
                                <td><font id="huisnummererror" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td><input class="zoektextveld" type="text" name="anaam" value="" size=15 maxlength=20 placeholder="<fmt:message bundle="${rb}" key="achternaam" />"></td>
                                <td><font id="anaamerror" size=1 color="red"> </font></td>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="stad" />" type="text" name="stad" value="" size=15 maxlength=20></td>
                                <td><font id="staderror" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="e-mail" />" type="text" name="email" value="" size=25  maxlength=25></td>
                                <td><font id="emailerror" size=1 color="red"> </font></td>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="rekeningnummer" />" type="text" name="reknr" value="" size=10  maxlength=8></td>
                                <td><font id="reknrerror" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="telefoonnummer" />" type="text" name="telnr" value="" size=15  maxlength=15></td>
                                <td><font id="telnrerror" size=1 color="red"> </font></td>
                                <td><input class="zoektextveld" type="password" name="wachtwoord" placeholder="<fmt:message bundle="${rb}" key="wachtwoord" />" size=10 value="" maxlength=30></td>
                                <td><font id="wachtwoorderror1" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="postcode" />" type="text" name="postcode" value="" size=5  maxlength=8></td>
                                <td><font id="postcodeerror" size=1 color="red"> </font></td>
                                <td><input class="zoektextveld" type="password" placeholder="<fmt:message bundle="${rb}" key="bevestigwachtwoord" />" name="wachtwoord2" size=10 value="" maxlength=30></td>
                                <td><font id="wachtwoorderror2" size=1 color="red"> </font> <font id="wachtwoorderror3" size=1 color="red"> </font></td>
                            </tr>
                            <tr>
                                <td>                        
                                    <fmt:message bundle="${rb}" key="geslacht" />
                                    <br>
                                    <input type="radio" name="geslacht" value="M" checked>M 
                                    <input type="radio" name="geslacht" value="V"> V</td>
                                <td></td>
                                <td>                        
                                    <fmt:message bundle="${rb}" key="taal" />
                                    <br>
                                    <input type="radio" name="locale" value="nl_NL" checked> <img src="images/nl_NL.png" />
                                    <input type="radio" name="locale" value="en_GB"> <img src="images/en_EN.png" /></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td height="20px;"></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td><button type="submit"><fmt:message bundle="${rb}" key="aanmelden" /></button></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>

                        </table>
                    

                        
                        
                  

<!--                    <div>
                        <b>Kies een foto:</b> 
                        <img src="images/NoPhotoAvailable.png />
                        <td colspan="2" align="center"><input type="file" value="Upload">                           
                    </div>-->

<!--                    <input type="submit" value="Aanmelden"> <input type="reset" value="Reset">-->
                    
                    
                </form>
            </div>
        </div>
    </body>
</html>
