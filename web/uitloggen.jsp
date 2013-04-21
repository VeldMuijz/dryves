<%-- 
    Document   : uitloggen
    Created on : Apr 17, 2013, 9:17:42 PM
    Author     : jeroen
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves uitgelogd</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
    </head>
    <body>

        <div class="background">

            <img src="images/background2.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
				<img src="images/Logo_Dryves.png" />
            </div>


			<div class="navigation">
			

            <div><button  class="loginButton" onclick="window.location = 'login.jsp';">Login</button></div>
			

			<button onclick="window.location = 'index.jsp';">Home</button>
			<button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button>
			<button onclick="window.location = 'faq.jsp';">FAQ</button>
			<button onclick="window.location = 'mijndryves.jsp';">Mijn Dryves</button>
			<button onclick="window.location = 'registratie.jsp';">Registreer</button>
						
        </div>
			<% session.invalidate(); %>
			<div class="contentPanel">
				<div style="height: 350px; margin-left: 15px;">
				<br/><br/>
				U bent uitgelogd. <br/>
				Bedankt voor uw bezoek.
				</div>
			</div>

        </div>

    </body>
</html>
