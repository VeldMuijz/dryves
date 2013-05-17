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


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js">
</script>
<script>
$(document).ready(function(){
    
     $("#beantwoorden").hide();
     
     
  $("#hide").click(function(){
    $("#beantwoorden").hide();
  });
  $("#show").click(function(){
    $("#beantwoorden").show();
  });
});
</script>



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
                <button id="show">Bericht beantwoorden</button>
                <br />
                <br><br>
              <c:forEach items="${berichten}" var="bericht">
                 <form id="beantwoorden" action="BerichtBeantwoorden" method="get">
                
                <input type="hidden" name="datum" value="${bericht.datum}" />
                <input type="hidden" name="afzender" value="${currentSessionUser.lidnr}"/>
                <input type="hidden" name="naar" value="${bericht.afzender}"/>
                <input type="hidden" name="ritnr" value="${bericht.ritnr}"/>
                
                <br>
                <h1>Typ hier uw bericht in</h1><br>
                <input type="text" id="beantwoorden" name="inhoud"/>
                
                <br>
                
                <input type="submit" />
                
                
            </form>
                
                
                
                
                   
                                <td>Bericht lidnr ${bericht.lidnr} </td>
                                <td></td>
                                <h1></h1>
                                <p>Datm: </p>
                                <p>Ritnummer: ${bericht.ritnr}</p>
                                <p>Bericht: ${bericht.inhoud}</p>
                                <p>Afzender: ${bericht.afzender}</p>
                                
                               <p> </p>
                                
               
                                
                             
                    </div>
                </c:forEach>
                
             
            
           
           
            </div>
            
            <br><br>
        </div>


    </body>
</html>

