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


            <div class="navigation">

                <div style="float:right; margin-right: -1px;"><button  onclick="window.location = 'login.jsp';">Login</button></div>

                <button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location = 'faq.jsp';">FAQ</button><button onclick="window.location = 'registratie.jsp';">Registreer</button>        
            </div>

            <div class="contentPanel">
                <button onclick="window.location = 'rit_plannen.jsp';">Home</button>

                Dit is de homepage! 

                <br /><br />
                        <div class="slider-wrapper theme-default">
                    <div id="slider" class="nivoSlider">
                        <img src="images/nemo.jpg" alt="" />
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

        

            








        </div>

    </body>
</html>
