<%-- 
    Document   : mijnberichten3
    Created on : 16-mei-2013, 1:19:05
    Author     : Hosni
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
        <title>Mijn berichten</title><script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
        </script>
 <script>
                            function redirect(){
   window.location = $('a').attr('href');
}

setTimeout(redirect, 5000);
</script>

    </head>
    <body >
<a  href="MijnDryves"></a>
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
                <h1><fmt:message bundle="${rb}" key="succesvol" /></h1>
                <br><br>                                                    
                    <p> </p>
                </div>        
        </div>
        <br><br>
</body>
</html>

