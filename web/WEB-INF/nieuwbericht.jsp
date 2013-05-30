<%-- 
    Document   : nieuwbericht
    Created on : 16-mei-2013, 21:12:34
    Author     : H
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nieuw bericht</title>
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



            <jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="faq"></jsp:param>
            </jsp:include>

            <div class="contentPanel">


						<%--    <p><fmt:message bundle="${rb}" key="datum" /> <% out.print(datum);%> </p> --%>


						<fmt:message bundle="${rb}" key="hieronderbericht" />

						<br /><br />

						<textarea placeholder="<fmt:message bundle="${rb}" key="schrijfjebericht" />" type="text" name="inhoud" rows="6" style="width:400px; padding: 6px;"></textarea>

						<br /><br />

						<button onclick="window.location = 'VerstuurBericht?ritnr=${bericht.ritnr}&lidnr=${bericht.lidnr}';"><fmt:message bundle="${rb}" key="berichtverzenden" /></button>



			</div>
	</body>
		</html>