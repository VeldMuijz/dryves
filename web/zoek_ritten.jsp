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
            
             <!-- Hier wordt de navigatie ingeladen -->

            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>




            <div class="contentPanel">  
                
             
            <c:choose>
                <c:when test="${pager.statusTotaalPager !=0}"> 
                   <p> <fmt:message bundle="${rb}" key="pagina" /> ${pager.statusHuidigePage} <fmt:message bundle="${rb}" key="van" /> ${pager.statusTotaalPager}</p> </c:when>
               
            </c:choose> 

              
                    <br />
                <c:choose>
                    <c:when test="${empty ritten}"> 
                       <h2><fmt:message bundle="${rb}" key="geenritgevonden" /></h2>
                        <p><fmt:message bundle="${rb}" key="geenritgevondenopn" /></p>
                    </c:when>
                    <c:otherwise>
                         <h2><fmt:message bundle="${rb}" key="zoekritgevonden" /></h2>

 <!-- Hier wordt de rittenlijst ingeladen -->

                        <c:forEach items="${ritten}" var="rit">
                            <div class="rittenlijst">
                                
                                <table width="100%">
                                    <tr>
                                        <td width="500px">
                                            <img src="images/from_icon.png" /> ${rit.straatnummer} - ${rit.postcodeplaats} -  ${rit.land}
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                         <td style="float:right;">
                                            <fmt:message bundle="${rb}" key="nog" /> <b>${rit.zitplaatsen}</b> <fmt:message bundle="${rb}" key="beschikbaar" />
                                        </td>
           
                                    </tr>
                                    <tr>
                                        <td>
                                            <img src="images/arrow_icon.png" /> ${rit.straatnummerEnd} - ${rit.postcodeplaatsEnd} -  ${rit.landEnd}
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <img src="images/calendar_icon.png" /> ${rit.datumkort} <img src="images/clock_icon.png" /> ${rit.tijd} <img src="images/money_icon.png" /> € ${rit.prijs}
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                        <td style="float:right;">
                                            <c:choose>
                                                <c:when test="${currentSessionUser.lidnr > 0}"> 
                                                    <button onclick="window.location = 'RitBeschikbaarCheck?ritnr=${rit.ritnr}';"><fmt:message bundle="${rb}" key="ritkopen" /></button>
												<button onclick="window.location = 'NieuwBericht?ritnr=${rit.ritnr}&lidnr=${currentSessionUser.lidnr} ';"><fmt:message bundle="${rb}" key="berichtverzenden" /></button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button onclick="window.location = 'login.jsp';"><fmt:message bundle="${rb}" key="loginregistreer" /></button>
                                                    </c:otherwise>
                                                </c:choose>
                                            
                                        </td>
                                        
                                    </tr>
                                </table>
                                
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
 
              <c:choose>
                <c:when test="${pager.aantalZoekResultaten > 5 &&  pager.maxPositie >= pager.offset}"> 
                    <button style="float:right;" onclick="window.location = 'RitZoeken?offset=${pager.offset}&knop=volgende&zoekritbegin=${pager.startpunt}&zoekriteind=${pager.eindpunt}';" value="Volgende"><fmt:message bundle="${rb}" key="volgende" /></button></c:when>
              
            </c:choose>

                     
                     <c:choose>
                <c:when test="${pager.offset>=5}"> 
                    <button onclick="window.location = 'RitZoeken?offset=${pager.offset}&knop=vorige&zoekritbegin=${pager.startpunt}&zoekriteind=${pager.eindpunt}';" value="Vorige"><fmt:message bundle="${rb}" key="vorige" /></button></c:when>
               
            </c:choose> 


            </div>
        </div>


    </body>
</html>
