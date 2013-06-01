<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        <title>Rit zoeken</title>


    </head>
    <body>


        <div class="background">

            <img src="images/background1.jpg" />

        </div>
        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>

            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>




            <div class="contentPanel">  

              
                    <br />
                <c:choose>
                    <c:when test="${empty ritten}"> 
                        <h2>Er zijn geen ritten gevonden</h2>
                        <p>Probeert u alstublieft opnieuw met andere zoekcriteria</p>
                    </c:when>
                    <c:otherwise>
                        <h2>Gevonden ritten</h2>



                        <c:forEach items="${ritten}" var="rit">
                            <div class="rittenlijst">
                                
                                <table width="100%">
                                    <tr>
                                        <td>
                                            <img src="images/from_icon.png" /> ${rit.straatnummer} - ${rit.postcodeplaats} -  ${rit.land}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                         
                                            <img src="images/to_icon.png" /> ${rit.straatnummerEnd} - ${rit.postcodeplaatsEnd} -  ${rit.landEnd}
                                        </td>
                                    </tr>
                                </table>
                                
                                
                                <table width="100%">
                                    
                                    <tr>
                                        <td><img src="images/money_icon.png" /> € ${rit.prijs}</td>
                                        <td>Ritnr: ${rit.ritnr}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td><img src="images/calendar_icon.png" /> ${rit.datumkort} <img src="images/clock_icon.png" /> ${rit.tijd}</td>
                                        <td></td>
                                        <td></td>
                                        <td>Nog ${rit.zitplaatsen} beschikbaar</td>
                                        <td style="float:right;">
                                            <c:choose>
                                                <c:when test="${currentSessionUser.lidnr > 0}"> 
                                                    <button onclick="window.location = 'RitBeschikbaarCheck?ritnr=${rit.ritnr}';"><fmt:message bundle="${rb}" key="ritkopen" /></button>
												<button onclick="window.location = 'NieuwBericht?ritnr=${rit.ritnr}&lidnr=${currentSessionUser.lidnr} ';"><fmt:message bundle="${rb}" key="berichtverzenden" /></button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button onclick="window.location = 'login.jsp';">Login/registreer <br>om te bekijken</button>
                                                    </c:otherwise>
                                                </c:choose>
                                        </td>
                                    </tr> 

                                </table>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


            </div>
        </div>


    </body>
</html>
