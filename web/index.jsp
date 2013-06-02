<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Model.Lid"%>
<%@page import="java.util.Locale"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
<%
    // Get the session object. If the incoming connection
    // is not associated with any existing session, the 
    // container constructs a new session object.
    HttpSession sess = request.getSession(true);
    // Session never expires. The expiration date/time can also 
    // be set in the servlet container's config file.
    sess.setMaxInactiveInterval(-1);

    // Get username. If no username is found, set it to "anonymous".
    String username = (String) sess.getAttribute("Username");
    if (username == null) {
        username = "anonymous";
    }
%>
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

            <!-- Hier wordt het navigatie menu ingeladen -->

            <jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>


            <br />





            <br /> <br />  

            <!-- Hier wordt de slider ingeladen -->

            <div class="slider-wrapper theme-default">
                <div id="slider" class="nivoSlider">
                    <img src="images/slider_audi.png" alt="" />
                    <img src="images/slider_brandstof.png" alt=""/>
                    <img src="images/slider_dryvesfile.png" alt="" />
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
