<%-- 
    Document   : helebericht
    Created on : 30-apr-2013, 18:05:00
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
                            <button onclick="window.location = 'mijnberichten3.jsp';">Inbox</button>
                       
                            
                          
				
                            
                             <c:forEach items="${gehelebericht}" var="bericht">
                 
                 
                              <h1>${bericht.onderwerp}</h1>
                              
                     
                               
                                <p>${bericht.datum}  </p>
                                <p> ${bericht.inhoud}</p>
                                
                           
                          
               
                                
                              
                  
                </c:forEach>
                
                            
                            
                            
                            
			</div>

        </div>

    </body>
</html>
