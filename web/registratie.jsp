<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
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
            
            <div style="float:right; margin-right: -1px;"><button  onclick="window.location='login.jsp';">Login</button></div>
            
            <button onclick="window.location='index.jsp';">Home</button><button onclick="window.location='watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location='faq.jsp';">FAQ</button>
            
        </div>
        
        <div class="contentPanel">
            
Schrijf je per direct in en word lid! Ontdek de vele voordelen die de Dryves community te bieden heeft. 

<br /><br />

<table style="width:940px;">
    <tr bgcolor="lightblue">
                        <td valign=top> 
                            <b>Voornaam<sup>*</sup></b> 
                            <br>
                            <input type="text" name="firstname" value="" size=20 maxlength=20></td>
                        <td valign=top>
                            <b>Achternaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="lastName" value="" size=20 maxlength=20></td>
                            <br>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <b>E-Mail<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="" size=25 maxlength=125>
                            <br></td>
                        <td valign=top>
                            <b>Tussenvoegsel<sup>*</sup></b> 
                            <br>
                            <input type="text" name="zip" value="" size=10 maxlength=8></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2>
                            <b>Gebrukersnaam<sup>*</sup></b>
                            <br>
                            <input type="text" name="userName" size=20 value="" maxlength=10>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <b>Wachtwoord<sup>*</sup></b> 
                            <br>
                            <input type="password" name="password1" size=10 value="" maxlength=10></td>
                        <td valign=top>
                            <b>Herhaal wachtwoord<sup>*</sup></b>
                            <br>
                            <input type="password" name="password2" size=10 value="" maxlength=10></td>
                    <br>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <b>Straatnaam<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="" size=25 maxlength=125>
                            <br></td>
                        <td valign=top>
                            <b>Postcode<sup>*</sup></b> 
                            <br>
                            <input type="text" name="zip" value="" size=10 maxlength=8></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2>
                            <b>Stad of dorp<sup>*</sup></b>
                            <br>
                            <input type="text" name="userName" size=20 value="" maxlength=10>
                        </td>
                    </tr>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td align=center colspan=2>
                            <input type="submit" value="Verzenden"> <input type="reset"  value="Reset">
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>
