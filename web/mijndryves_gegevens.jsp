<%-- 
    Document   : mijndryves
    Created on : 27-mrt-2013, 18:10:27
    Author     : Vincent
--%>

<%@page import="Dryves.Sessie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                
        <% Sessie currentUser = (Sessie) (session.getAttribute("currentSessionUser"));%>

        Welkom <%= currentUser.getVnaam() + " " + currentUser.getAnaam() %>
                
                <button onclick="window.location = 'mijndryves_gegevens.jsp';">Mijn gegevens</button>
                <button onclick="window.location = 'rit_plannen.jsp';">Mijn berichten</button>
                <button onclick="window.location = 'mijn_ritten.jsp';">Mijn ritten</button>







                <br /><br />
                <form action="/dryves/proces.jsp" method=post>



                    <div class="regformheader">
                        <font size=3>Persoonsgegevens</font> 
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

                    <div class="formInput">
                        <b>Geslacht</b>
                        <br>
                        <input type="radio" name="notify" value="M" checked>M 
                        <input type="radio" name="notify" value="V"> V
                    </div>


                    <input type="submit" value="Wijzigen"> 




                </form>

            </div>
        </div>







</body>
</html>
