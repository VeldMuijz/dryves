<%-- 
    Document   : zoekrit
    Created on : 28-apr-2013, 15:03:25
    Author     : Vincent
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/dryver.css" />
        <title>JSP Page</title>
    </head>
    <body>
                     <form action="RitZoeken" method="get" >
                         <b>Zoek rit: <b> <input class="zoektextveld" name="zoekrit" onchange="RitZoeken" placeholder="Zoek op adres, stad, postcode..." ></input><button onclick="RitZoeken" class="heliosActionBarIconButton"><img src="images/zoom_grey.png"</button>
                            </form>   
    </body>
</html>
