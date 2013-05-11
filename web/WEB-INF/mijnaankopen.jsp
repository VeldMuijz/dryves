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




            <div class="contentPanel">         
                <button onclick="window.location = '/Dryves/RitPlannen';"><fmt:message bundle="${rb}" key="planrit" /></button> <br />

                
                
                <c:forEach items="${aankopen}" var="aankoop">
                    <div class="rittenlijst">
                        <table>
                                <tr>
                                <td>${aankoop.aankoopnr}</td>
								<td>${aankoop.factuurnr}</td>
								<td>${aankoop.ritnr}</td>
                                <td>${aankoop.datum}</td>
                               <td><button onclick="window.location = 'LidBeoordelen?aankoopnr=${aankoop.aankoopnr}';">Beoordelen</button></td>
                                
                                </tr> 

                        </table>
                    </div>
                </c:forEach>
                
             
            </div>
        </div>


    </body>
</html>
