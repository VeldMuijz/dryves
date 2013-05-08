<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            
            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
              <jsp:param name="menu_active" value="home"></jsp:param>
              </jsp:include>




            <div class="contentPanel">  
                
                <jsp:include page="zoekrit.jsp" flush="true" ></jsp:include> 

                <br /><br />
                
                <c:forEach items="${ritten}" var="rit">
                    <div class="rittenlijst">
                        <table onclick="window.location = 'RitPlannen?ritnr=${rit.ritnr}';">
                            
                                <tr>
                                <td>${rit.startpunt}</td>
                                <td><img src ="images/pijl.jpg" /></td>
                                <td>${rit.eindpunt}</td>
                                <td>${rit.prijs}</td>
<!--                                <td><button onclick="RitPlannen?ritnr=${rit.ritnr}"> Rit wijzigen</button></td>-->
                                </tr> 

                        </table>
                    </div>
                </c:forEach>
                
             
            </div>
        </div>


    </body>
</html>