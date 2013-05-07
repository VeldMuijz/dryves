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
                <button onclick="window.location = '/Dryves/RitPlannen';">Rit plannen</button> <br />

                
                
                <c:forEach items="${ritten}" var="rit">
                    <div class="rittenlijst">
                        <table>
                            
                                <tr>
                                <td>${rit.startpunt}</td>
                                <td><img src ="images/pijl.jpg" /></td>
                                <td>${rit.eindpunt}</td>
                                <td>${rit.prijs}</td>
                                <td><button onclick="window.location = 'RitWijzigen?ritnr=${rit.ritnr}';"> Rit wijzigen</button></td>
                                <td><button onclick="window.location = 'RitKopen?ritnr=${rit.ritnr}';"> Rit kopen</button></td>
                                </tr> 

                        </table>
                    </div>
                </c:forEach>
                
             
            </div>
        </div>


    </body>
</html>