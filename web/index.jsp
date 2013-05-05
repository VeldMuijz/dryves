<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />


<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css" />
        <link rel= "stylesheet" href="css/nivo-slider.css"  media="screen" />
        <link rel= "stylesheet" href="themes/default/default.css"  media="screen" />
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>



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
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>


            <br />

            <div class="contentPanel">

                <!--                <form action="RitZoeken" method="get" >
                                    <b>Zoek rit: <b> <input id='zoekveld' name="zoekrit" onchange="RitZoeken" placeholder="Zoek op adres, stad, postcode" ></input> 
                                            <button onclick="RitZoeken">Uitvoeren</button>
                                            </form>      -->

                <jsp:include page="zoekrit.jsp" flush="true" ></jsp:include> 
                

            </div>
                
            <br /> <br />  
                
                
            <div class="slider-wrapper theme-default">
                <div id="slider" class="nivoSlider">
                    <img src="images/slider_audi.png" alt="" title="Dit is een mooie audi" />
                    <a href="http://dev7studios.com">
                        <img src="images/toystory.jpg" alt="" title="#htmlcaption" /></a>
                    <img src="images/up.jpg" alt="" title="This is an example of a caption" />
                    <img src="images/walle.jpg" alt="" />
                </div>
            </div>
            <div id="htmlcaption" class="nivo-html-caption">
                <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
            </div>
            <script type="text/javascript">
                $(window).load(function() {
                    $('#slider').nivoSlider();
                });
            </script>
        </div>        

    </body>
</html>
