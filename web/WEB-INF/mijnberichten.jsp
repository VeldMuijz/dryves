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
 <c:choose>
                <c:when test="${pager.statusTotaalPager !=0}"> 
                   <p>Pagina ${pager.statusHuidigePage}  van de ${pager.statusTotaalPager}</p> </c:when>
               
            </c:choose> 
				<c:choose>
					<c:when test="${empty berichten}"> 
						<h2>Er zijn geen berichten voor u</h2>
						<p>Kom hier later nog eens terug</p>
					</c:when>
					<c:otherwise>

						<c:set var="counter" value="0"/>
						<c:forEach items="${berichten}" var="bericht">
							<div class="rittenlijst">

								<table>
									<td width="50px;">
										<c:choose>
											<c:when test="${bericht.ongelezen==1}"><img src="images/envelope.png" /></c:when>
											<c:when test="${bericht.ongelezen==0}"></c:when>
										</c:choose>
									</td>

									<td width="200px;"><fmt:message bundle="${rb}" key="afzender" /> ${afzender[counter].vnaam} ${afzender[counter].anaam} <br/> <fmt:message bundle="${rb}" key="datum" />  ${bericht.stringDatum} ${bericht.stringTijd}</td>
									<td width="82px;"></td>  
									<td><button onclick="window.location = 'BerichtLezen?berichtid=${bericht.berichtid}';"><fmt:message bundle="${rb}" key="bekijkbericht" /></button></td>
									<c:set var="counter" value="${counter + 1 }"/>
                                                                        
                                                                      


								</table>


							</div>

						</c:forEach>
					</c:otherwise>
				</c:choose>
                                                  
      

			</div>
            <c:choose>
                <c:when test="${pager.aantalberichten > 5 &&  pager.maxPositie >= pager.offset}"> 
                    <input type="button" onclick="window.location = 'PagerServlet?offset=${pager.offset}&knop=volgende';" value="Volgende"/></c:when>
              
            </c:choose>

                     
                     <c:choose>
                <c:when test="${pager.offset>=5}"> 
                    <input type="button" onclick="window.location = 'PagerServlet?offset=${pager.offset}&knop=vorige';" value="Vorige"/></c:when>
               
            </c:choose> 
                    
		</div>


	</body>
</html>

