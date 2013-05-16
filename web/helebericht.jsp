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
             <input type="button"  value="Inbox" onclick="location.href='MijnBerichten'">
                
                <br />
                <br><br>
              
                
                <c:forEach items="${berichten}" var="bericht">
                   
                                <td>Bericht lidnr ${bericht.lidnr} </td>
                                <td></td>
                                <h1>${bericht.onderwerp}</h1>
                                <p>Datm: ${bericht.datum}</p>
                                <p>Ritnummer: ${bericht.ritnr}</p>
                                <p>Bericht: ${bericht.inhoud}</p>
                                
                                
                                
               
                                
                             
                    </div>
                </c:forEach>
                
             
            </div>
        </div>


    </body>
</html>

