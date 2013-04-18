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


			<jsp:include page="navigatie.jsp"  flush="true" />
			<% session.invalidate();%>
			<div class="contentPanel">

				U bent uitgelogd. <br/>
				Bedankt voor uw  bezoek.
			</div>

        </div>

    </body>
</html>
