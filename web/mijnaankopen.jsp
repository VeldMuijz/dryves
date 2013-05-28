<%-- 
Document : mijn_ritten
 Created on : 15-apr-2013, 21:23:53
 Author : Vincent
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="javassist.bytecode.stackmap.BasicBlock.Catch"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Dryves.Model.Lid"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<fmt:setLocale value="${locale}" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        <title>Mijn aankoop</title>

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
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>

<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>



            <div class="contentPanel">         
                <h1>Mijn aankoop</h1>

                <%


                    //Staat bijvoorbeeld in de servlet RitPlannen.java
                    // Haal de huidige sessie op

                    //Haal de userbean (dit moet sessiebean worden) op uit de sessie
                    Lid user = (Lid) session.getAttribute("currentSessionUser");







                %>		 <p>   ${currentSessionUser.lidnr}</p>
                <form action="Berichtverzenden" method="get">
                    <table>
                        <%

                            Connection con = Dryves.ConnectionManager.getConnection();
                           
                                ResultSet rs;
                                PreparedStatement pstmt = con.prepareStatement("SELECT lidnr, datum, ritnr FROM aankoop WHERE IDBERICHT =?");
                                
                                pstmt.setInt(1,user.getLidnr() );    
//hier 
                                rs = pstmt.executeQuery();
                                int lidnr;
                                while (rs.next()) {



                              lidnr  =    rs.getInt(1);

                                








                        %> 



                        <div class="rittenlijst">
                            <table>

                                <tr>
                                    <td></td>
                                    <td><img src ="images/pijl.jpg" /></td>
                                    <td>          






                                    </td>
                                    <td>
                                        <fmt:message bundle="${rb}" key="lidnr" /><% out.println(rs.getString(1));%>, <fmt:message bundle="${rb}" key="aankoopnummer" /><% out.println(rs.getString(3));%>   <fmt:message bundle="${rb}" key="datum" /> <% out.println(rs.getString(3));%> 




                                    </td>
                                    <% }%>

                                    <td><button value="lidnr" name="lidnr" > <fmt:message bundle="${rb}" key="verstuurbericht" /></button></td>
                                    <td><button onclick="window.location = 'RitKopen?ritnr=${rit.ritnr}';"> <fmt:message bundle="${rb}" key="geen" /></button></td>
                                </tr> 

                            </table>

                            </form>

                        </div>



                        </div>
                        </div>


                        </body>
                        </html>