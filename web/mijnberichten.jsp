<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Model.Lid"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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

	<%
        
        
        //Staat bijvoorbeeld in de servlet RitPlannen.java
 // Haal de huidige sessie op
      
 //Haal de userbean (dit moet sessiebean worden) op uit de sessie
        Lid user = (Lid) session.getAttribute("currentSessionUser");
 

 

        
        
        
        %>		 <p>   ${currentSessionUser.lidnr}</p>
     <form action="BerichtServlet" method="get">
         <table>
      <% 

      int lidnr=user.getLidnr();    
          Connection c = Dryves.ConnectionManager.getConnection();
        Statement statm= c.createStatement();
     ResultSet   rs =statm.executeQuery("select IDBERICHT, ONDERWERP, DATUM  from BERICHTEN  where lidnr="+ lidnr);
      
      
     
      while(rs.next()){
      
      %> 
	
      <tr>
mails:<% out.println(rs.getString(2)); %>, <% out.println(rs.getString(3)); %> <input type="radio"  name="berichtid" value="<% out.println(rs.getInt(1)); %>" >   <br>


              
      </tr>
           
	
	
     <% } %>
	
	
	<input type="submit" value="Submit">

   
    

     
     
     
     
         </table>
     
     
     
     
     
     
     
     
    
     
     //nu die status balk
   
   <p>  </p>
      
     
     
     </form>
			</div>

        </div>

    </body>
</html>
