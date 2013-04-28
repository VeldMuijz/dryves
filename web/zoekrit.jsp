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
        <title>JSP Page</title>
    </head>
    <body>
                     <form action="RitZoeken" method="get" >
                    <b>Zoek rit: <b> <input id='zoekveld' name="zoekrit" onchange="RitZoeken" placeholder="Zoek op adres, stad, postcode" ></input> 
                            <button onclick="RitZoeken">Uitvoeren</button>
                            </form>   
    </body>
</html>
