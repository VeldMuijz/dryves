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
        <title>Mijn beoordelingen</title>

    </head>
    <body>


        <div class="background">

            <img src="${currentSessionUser.getAchtergrond()}" />

        </div>
        <div class="drvyesWrapper">
            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>
			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>




            <div class="contentPanel">
				<c:choose>
					<c:when test="${pager.statusTotaalPager !=0}"> 
						<p><fmt:message bundle="${rb}" key="pagina" /> ${pager.statusHuidigePage} <fmt:message bundle="${rb}" key="van" /> ${pager.statusTotaalPager}</p> </c:when>

				</c:choose> 

				<c:choose>
					<c:when test="${empty beoordelingen}"> 
						<fmt:message bundle="${rb}" key="geenbeoordeling" />
					</c:when>
					<c:otherwise>
						<c:set var="counter" value="0"/>
						<fmt:message bundle="${rb}" key="beoordeelddooranderen" />
                                                <br /><br />
						<c:forEach items="${beoordelingen}" var="beoordeling">
							<div class="rittenlijst">

								<table>
									<tr><td width="200px;"><fmt:message bundle="${rb}" key="beoordeelddoor" /></td> 
                                                                            <td>${beoordelaars[counter].vnaam} ${beoordelaars[counter].tvoegsel} ${beoordelaars[counter].anaam}</tr>
									<tr><td><fmt:message bundle="${rb}" key="beoordeeldop" /></td> 
                                                                            <td><img src="images/calendar_icon.png" /> ${beoordeling.korteDatum} <img src="images/clock_icon.png" /> ${beoordeling.korteTijd}</td></tr>
									<tr><td><fmt:message bundle="${rb}" key="gezelligheid" /></td> 
                                                                            <td>
                                                                                
                                                                                <c:choose>

												<c:when test="${beoordeling.gezelligheid==1}"><img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.gezelligheid==2}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.gezelligheid==3}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.gezelligheid==4}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.gezelligheid==5}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>

										</c:choose>
                                                                                
                                                                                </td></tr>
									<tr><td><fmt:message bundle="${rb}" key="rijstijl" /></td>
                                                                            <td>
                                                                                
                                                                                <c:choose>

												<c:when test="${beoordeling.rijstijl==1}"><img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.rijstijl==2}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.rijstijl==3}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.rijstijl==4}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.rijstijl==5}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>

										</c:choose>
                                                                            </td></tr>
									<tr><td><fmt:message bundle="${rb}" key="betrouwbaarheid" /></td>
                                                                            <td>
                                                                                
                                                                                <c:choose>

												<c:when test="${beoordeling.betrouwbaarheid==1}"><img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.betrouwbaarheid==2}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.betrouwbaarheid==3}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.betrouwbaarheid==4}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.betrouwbaarheid==5}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>

										</c:choose>
                                                                                </td> </tr>
									<tr><td><fmt:message bundle="${rb}" key="stiptheid" /></td>
                                                                            <td>
                                                                                
                                                                                <c:choose>

												<c:when test="${beoordeling.stiptheid==1}"><img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.stiptheid==2}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.stiptheid==3}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.stiptheid==4}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.stiptheid==5}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>

										</c:choose>
                                                                                </td></tr>
									<tr><td><fmt:message bundle="${rb}" key="opmerking" /></td>
                                                                            <td>
                                                                            
                                                                                ${beoordeling.commentaar}</td></tr>
									<tr height="20px;"><td></td></tr>
									<tr style="font-size: 20px;"><td><fmt:message bundle="${rb}" key="eindbeoordeling" /></td>


										<td>

											<c:choose>

												<c:when test="${beoordeling.waardering==1}"><img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.waardering==2}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.waardering==3}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.waardering==4}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>
												<c:when test="${beoordeling.waardering==5}"><img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /> <img src="images/ratingstar.png" /></c:when>

											</c:choose>

										</td>



									</tr>
								</table>

							</div>
							<c:set var="counter" value="${counter + 1 }"/>
						</c:forEach>

					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${pager.aantalbeoordelingen > 5 &&  pager.maxPositie >= pager.offset}"> 

						<button style="float:right;" onclick="window.location = 'MijnBeoordelingen?offset=${pager.offset}&knop=volgende';" value="Volgende"><fmt:message bundle="${rb}" key="volgende" /></button>
					</c:when>

				</c:choose>


				<c:choose>
					<c:when test="${pager.offset>=5}"> 
						<button onclick="window.location = 'MijnBeoordelingen?offset=${pager.offset}&knop=vorige';" value="Vorige"><fmt:message bundle="${rb}" key="vorige" /></button></c:when>

				</c:choose> 

            </div>
        </div>


    </body>
</html>
