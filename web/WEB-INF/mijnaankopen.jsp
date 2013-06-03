<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
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
        <title>Mijn ritten</title>

    </head>
    <body>


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

            <div class="contentPanel">  <c:choose>
                    <c:when test="${pager.statusTotaalPager !=0}"> 
                        <p><fmt:message bundle="${rb}" key="pagina" /> ${pager.statusHuidigePage} <fmt:message bundle="${rb}" key="van" /> ${pager.statusTotaalPager}</p> </c:when>

                </c:choose> 
                <c:choose>
                    <c:when test="${empty aankopen}">
                        <fmt:message bundle="${rb}" key="geenaankopen" />
                    </c:when>
                    <c:otherwise>


                        <c:forEach items="${aankopen}" var="aankoop">
                            <div class="rittenlijst">
                                <table style="width:100%;">
                                    <tr>
                                        <td><fmt:message bundle="${rb}" key="aankoopnummer" /> ${aankoop.aankoopnr}</td>
                                        <td>
                                    </tr>
                                    <tr>
                                        <td><fmt:message bundle="${rb}" key="ritnummer" /> ${aankoop.ritnr}</td>
                                        <td></td>
                                    </tr>   
                                    <td><img src="images/calendar_icon.png" /> ${aankoop.stringDatum} <img src="images/clock_icon.png" /> ${aankoop.stringTijd}</td>
                                        
                                        <c:choose>
                                            <c:when test="${aankoop.beoordeeld < 1}">
                                                <td style="float:right;"><button onclick="window.location = 'LidBeoordelen?aankoopnr=${aankoop.aankoopnr}';"><fmt:message bundle="${rb}" key="beoordeelknop" /></button></td>
                                                </c:when>
                                                <c:otherwise>
                                                <td style="float: right;"><fmt:message bundle="${rb}" key="albeoordeeld" /></td>
                                            </c:otherwise>
                                        </c:choose>

                                </table>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


                <c:choose>
                    <c:when test="${pager.aantalAankopen > 5 &&  pager.maxPositie >= pager.offset}"> 
                        <button style="float:right;" onclick="window.location = 'MijnAankopen?offset=${pager.offset}&knop=volgende';" value="Volgende"><fmt:message bundle="${rb}" key="volgende" /></button></c:when>

                </c:choose>


                <c:choose>
                    <c:when test="${pager.offset>=5}"> 
                        <button onclick="window.location = 'MijnAankopen?offset=${pager.offset}&knop=vorige';" value="Vorige"><fmt:message bundle="${rb}" key="vorige" /></button></c:when>

                </c:choose>

            </div>
        </div>


    </body>
</html>
