<%-- 
    Document   : mijndryves
    Created on : 27-mrt-2013, 18:10:27
    Author     : Vincent
--%>

<%@page import="Dryves.Lid"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

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
                
                <% Lid currentUser = (Lid) (session.getAttribute("currentSessionUser"));%>

        <fmt:message bundle="${rb}" key="welkom" /> <%= currentUser.getVnaam() + " " + currentUser.getAnaam() %>
                
                <button onclick="window.location = 'mijndryves_gegevens.jsp';">Mijn gegevens</button>
                <button onclick="window.location = 'rit_plannen.jsp';">Mijn berichten</button>
                <button onclick="window.location = 'mijn_ritten.jsp';">Mijn ritten</button>







                <br /><br />
                <form id="RegistratieForm" action="Registreren" method="get" onsubmit="return validateForm();">

                    <div class="regformheader">
                        <font size=5>Registratie formulier</font>
                        <br>
                        <font size=1 color="red"><sup>*</sup> Verplichte velden</font>
                    </div>

                    <div class="formInput">                   
                        <b>Voornaam<sup>*</sup></b>                             
                        <br>
                        <font id="vnaamerror" size=1 color="red"> </font>
                        <input type="text" name="vnaam" value="" size=15 maxlength=20>
                    </div>
                    <div class="formInput">
                        <b>Achternaam<sup>*</sup></b>
                        <br>
                        <font id="anaamerror" size=1 color="red"> </font>
                        <input type="text" name="anaam" value="" size=15 maxlength=20>
                    </div>

                    <div>
                        <b>Kies een foto:</b> 
                        <img src="images/NoPhotoAvailable.png />
                        <td colspan="2" align="center"><input type="file" value="Upload">                           
                    </div>
 
                    <div class="formInput">
                        <b>Tussenvoegsel<sup></sup></b>
                        <br>                        
                        <input type="text" name="tvoegsel" value="" size=5 maxlength=20>
                    </div>

                    <div class="formInput">
                        <b>Rekening nummer<sup>*</sup></b> 
                        <br>
                        <font id="reknrerror" size=1 color="red"> </font>
                        <input type="text" name="reknr" value="" size=10  maxlength=8>
                    </div>

                    <div class="formInput">
                        <b>Telefoonnummer<sup>*</sup></b> 
                        <br>
                        <font id="telnrerror" size=1 color="red"> </font>
                        <input type="text" name="telnr" value="" size=15  maxlength=15>
                    </div>

                    <div class="formInput">
                        <b>E-Mail<sup>*</sup></b> 
                        <br>
                        <font id="emailerror" size=1 color="red"> </font>
                        <input type="text" name="email" value="" size=25  maxlength=25>
                    </div>
                    <div class="formInput">
                        <b>Straatnaam<sup>*</sup></b>
                        <br>
                        <font id="straaterror" size=1 color="red"> </font>
                        <input type="text" name="straat" value="" size=15 maxlength=20>            
                    </div>
                    <div class="formInput">
                        <b>Huisnummer<sup>*</sup></b>
                        <br>
                        <font id="huisnummererror" size=1 color="red"> </font>
                        <input type="text" name="huisnummer" value="" size=5 maxlength=8>            
                    </div>
                    <div class="formInput">
                        <b>Postcode<sup>*</sup></b> 
                        <br>
                        <font id="postcodeerror" size=1 color="red"> </font>
                        <input type="text" name="postcode" value="" size=5  maxlength=8>
                    </div>

                    <div class="formInput">
                        <b>Stad<sup>*</sup></b>
                        <br>
                        <font id="staderror" size=1 color="red"> </font>
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
                        <input type="radio" name="locale" value="EN"> <img src="images/en_GB.png" />
                    </div>


<!--                    <input type="submit" value="Aanmelden"> <input type="reset" value="Reset">-->
                    <button type ="submit">Aanmelden</button>
                    <input type="reset" value="Reset">
                </form>
            </div>
        </div>
    </body>
</html>