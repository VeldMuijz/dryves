<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
    </head>
    <body>

        <div class="background">

            <img src="images/background2.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
				<img src="images/Logo_Dryves.png" />
            </div>


			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="watisdryves"></jsp:param>
            </jsp:include>

			<div class="contentPanel">

				<fmt:message bundle="${rb}" key="watisdryves" /> 

				<br /><br />

				<fmt:message bundle="${rb}" key="watisdryves1" />

				<br /><br />

				<fmt:message bundle="${rb}" key="watisdryves2" />
                                    
                                <br /><br />
                                    
                                <fmt:message bundle="${rb}" key="watisdryves21" />
                                
                                <br /><br />
                                
                                <fmt:message bundle="${rb}" key="watisdryves3" />
                                
                                 <br /><br />
                                 
                                 <fmt:message bundle="${rb}" key="watisdryves31" />
                                 
                                 
			</div>

        </div>

    </body>
</html>
