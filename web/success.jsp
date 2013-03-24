<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>
<jsp:useBean id="formHandler" class="test.FormBean" scope="request"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves</title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
        
        <script>
            
            
            
            
            
        </script>
        
        
    </head>
    <body>
        
        <div class="background">
            
            <img src="images/background1.jpg" />
            
        </div>
        
        <div class="drvyesWrapper">
        
            <div class="logo">    
        <img src="images/Logo_Dryves.png" />
            </div>
        
        
        <div class="navigation">
            
            <div style="float:right; margin-right: -1px;"><button  onclick="window.location='login.jsp';">Login</button></div>
            
            <button onclick="window.location='index.jsp';">Home</button><button onclick="window.location='watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location='faq.jsp';">FAQ</button>
            
        </div>
        
        <div class="contentPanel">           

            <table cellpadding=4 cellspacing=2 border=0>
            <th bgcolor="lightblue" colspan=2>
                <font size=5>Registratie succesvol!</font>
            </th>
            <font size=4>
            <tr bgcolor="lightblue">
                <td valign=top> 
                    <b>Voornaam</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="firstName"/>
                </td>
                <td valign=top>
                    <b>Achternaam</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="lastName"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td valign=top>
                    <b>E-Mail</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="email"/>
                    <br></td>
                <td valign=top>
                    <b>Postcode</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="zip"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td valign=top colspan=2>
                    <b>Gebruikersnaam</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="userName"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td colspan=2 valign=top>
                    <b>In welke regio zal er veel gebruik worden gemaakt van Dryves?</b>
                    <br>
                    <%
                      String[] faveTech = formHandler.getFaveTech();
                      if (!faveTech[0].equals("1")) {
                        out.println("<ul>");
                        for (int i=0; i<faveTech.length; i++)  
                          out.println("<li>"+faveTech[i]);
                        out.println("</ul>");
                      } else out.println("Er was niets geselecteerd");
                    %>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td colspan=2 valign=top>
                    <b>Wil je onze nieuwsbrief ontvangen?</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="notify"/>
                </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>