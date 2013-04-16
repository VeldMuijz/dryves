<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>
<%@page import="Dryves.Lid"%>
<jsp:useBean id="formHandler" class="Dryves.Lid" scope="request"/>
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

                <div class="loginButton"><button  onclick="window.location = 'login.jsp';">Login</button></div>

                <button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location = 'faq.jsp';">FAQ</button>

            </div>

            <div class="contentPanel">           

                <div class="regformheader">
                    <font size=5>Registratie succesvol!</font>
                </div>
                <div class="formInput">
                    <b>Voornaam</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="vnaam"/>
                </div>

                <div class="formInput">   
                    <b>Achternaam</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="anaam"/>
                </div>
                
                <div class="formInput">
                    <b>Rekening nummer</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="reknr"/>
                </div>
                
                <div class="formInput">   
                    <b>Tussenvoegsel</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="tvoegsel"/>
                </div>

                <div class="formInput">
                    <b>E-Mail</b>                 
                    <jsp:getProperty name="formHandler" property="email"/>
                </div>  

                <div class="formInput">   
                    <b>Straat</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="adres"/>
                </div>
                
                <div class="formInput">   
                    <b>Huisnummer</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="huisnummer"/>
                </div>

                <div class="formInput">
                    <b>Postcode</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="postcode"/>
                </div>
                
                <div class="formInput">   
                    <b>Stad</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="stad"/>
                </div>

                <div class="formInput">
                    <b>Geslacht</b>
                    <jsp:getProperty name="formHandler" property="notify"/>
                </div>
            </div>
        </form>
</body>
</html>