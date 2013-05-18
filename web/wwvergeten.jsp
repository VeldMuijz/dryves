<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Model.Lid"%>
<%@page import="Dryves.Controller.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Dryves.Controller.Login"%>


<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

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


            <div class="navigation">

                <div style="float:right; margin-right: -1px;"><button>Login</button></div>

                <button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location = 'faq.jsp';">FAQ</button><button onclick="window.location = 'registratie.jsp';">Registreer</button>

            </div>

            <div class="contentPanel">

                 <fmt:message bundle="${rb}" key="wwvergeten" />

                <br /><br />

                <form action="LoginServlet" method="post">
                    <table style="width:320px;">
                        <tr>
                            <td><input class="zoektextveld" placeholder="<fmt:message bundle="${rb}" key="email" />" type="text" name="email" value="" size=25  maxlength=25></td>
                            <td></td>
                            <td></td>
                        </tr>

                </form>



            </div>

        </div>

    </body>
</html>



