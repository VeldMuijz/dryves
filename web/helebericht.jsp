<%-- 
    Document   : mijnberichten3
    Created on : 16-mei-2013, 1:19:05
    Author     : H
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        <title>Mijn berichten</title>

    </head>
    <body>


        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
        </script>
        <script>
            $(document).ready(function() {

                $("#beantwoorden").hide();


                $("#hide").click(function() {
                    $("#beantwoorden").hide();
                });
                $("#show").click(function() {
                    $("#beantwoorden").show();
                });
            });
        </script>



        <div class="background">

            <img src="${currentSessionUser.getAchtergrond()}" />

        </div>
        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>

            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>




            <div class="contentPanel">         
                <input type="button"  value="Inbox" onclick="location.href = 'MijnBerichten'">
                <button id="show"><fmt:message bundle="${rb}" key="berichtbeantwoorden" /></button>
                <br />
                <br><br>
                <c:forEach items="${berichten}" var="bericht">
                    <form id="beantwoorden" action="BerichtBeantwoorden" method="get">

                        <input type="hidden" name="datum" value="${bericht.datum}" />
                        <input type="hidden" name="afzender" value="${currentSessionUser.lidnr}"/>
                        <input type="hidden" name="naar" value="${bericht.afzender}"/>
                        <input type="hidden" name="ritnr" value="${bericht.ritnr}"/>

                        <br>
                        <h1><fmt:message bundle="${rb}" key="typhieruwbericht" /></h1><br>
                        <textarea type="text" id="beantwoorden" name="inhoud" rows="4"></textarea>

                        <br>

                        <button><fmt:message bundle="${rb}" key="berichtverzenden" /></button>


                    </form>






                    <h1><fmt:message bundle="${rb}" key="ritnummeris" /> ${bericht.ritnr}</h1>
                    <p><fmt:message bundle="${rb}" key="lidnr" /> ${bericht.lidnr} </p>
                    <p><fmt:message bundle="${rb}" key="afzender" /> ${bericht.afzender}</p>                         
                    <p><fmt:message bundle="${rb}" key="datum" /> ${bericht.datum}</p>

                    <p><fmt:message bundle="${rb}" key="bericht" /> ${bericht.inhoud}</p>


                    <p> </p>




                </div>
            </c:forEach>





        </div>

        <br><br>
    </div>


</body>
</html>

