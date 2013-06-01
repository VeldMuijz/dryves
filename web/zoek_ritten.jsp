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
  <c:choose>
                <c:when test="${pager.statusTotaalPager !=0}"> 
                   <p>Pagina ${pager.statusHuidigePage}  van de ${pager.statusTotaalPager}</p> </c:when>
               
            </c:choose> 
                
              
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
                                        <td>Van:</td>
                                        <td>${rit.straatnummer}</td>
                                        <td><img src ="images/pijl.jpg" /></td>
                                        <td>Naar:</td>
                                        <td>${rit.straatnummerEnd}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>${rit.postcodeplaats}</td>
                                        <td></td>
                                        <td></td>
                                        <td>${rit.postcodeplaatsEnd}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>${rit.land}</td>
                                        <td></td>
                                        <td></td>
                                        <td>${rit.landEnd}</td>
                                    </tr>
                                    
                                    <tr>
                                        <td>Prijs: € ${rit.prijs}</td>
                                        <td>Ritnr: ${rit.ritnr}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td>${rit.datumkort} ${rit.tijd}</td>
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
            
           
            <c:choose>
                <c:when test="${pager.aantalZoekResultaten > 5 &&  pager.maxPositie >= pager.offset}"> 
                    <input type="button" onclick="window.location = 'ZoekenPager?offset=${pager.offset}&knop=volgende&zoekritbegin=${pager.startpunt}&zoekriteind=${pager.eindpunt}';" value="Volgende"/></c:when>
              
            </c:choose>

                     
                     <c:choose>
                <c:when test="${pager.offset>=5}"> 
                    <input type="button" onclick="window.location = 'ZoekenPager?offset=${pager.offset}&knop=vorige&zoekritbegin=${pager.startpunt}&zoekriteind=${pager.eindpunt}';" value="Vorige"/></c:when>
               
            </c:choose> 
        </div>


    </body>
</html>
