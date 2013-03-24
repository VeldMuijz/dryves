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


            <form action="/dryves/proces.jsp" method=post>
            <center>
                <form action="proces.jsp" method=post>
            <center>
                <table cellpadding=4 cellspacing=2 border=0>
                    <th bgcolor="lightblue" colspan=2>
                        <font size=5>Registratie formulier</font>
                        <br>
                        <font size=2 color="red"><sup>*</sup> Verplichte velden </font>
                    </th>
                    <tr bgcolor="lightblue">
                        <td valign=top> 
                            <B>Voornaam<sup>*</sup></B> 
                            <br>
                            <input type="text" name="firstName" 
                                   value='<%=formHandler.getFirstName()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("firstName")%></font>
                        </td>
                        <td  valign=top>
                            <B>Achternaam<sup>*</sup></B>
                            <br>
                            <input type="text" name="lastName" 
                                   value='<%=formHandler.getLastName()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("lastName")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <B>E-Mail<sup>*</sup></B> 
                            <br>
                            <input type="text" name="email" value='<%=formHandler.getEmail()%>' 
                                   size=25  maxlength=125>
                            <br><font size=2 color=red><%=formHandler.getErrorMsg("email")%></font>
                        </td>
                        <td  valign=top>
                            <B>Postcode<sup>*</sup></B> 
                            <br>
                            <input type="text" name="zip" value='<%=formHandler.getZip()%>' size=5  
                                   maxlength=6>
                            <br><font size=2 color=red><%=formHandler.getErrorMsg("zip")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2> 
                            <B>Gebruikersnaam<sup>*</sup></B>
                            <br>
                            <input type="text" name="userName" size=10 
                                   value='<%=formHandler.getUserName()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("userName")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <B>Wachtwoord<sup>*</sup></B> 
                            <br>
                            <input type="password" name="password1" size=10 
                                   value='<%=formHandler.getPassword1()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("password1")%></font>
                        </td>
                        <td  valign=top>
                            <B>Bevestig wachtwoord<sup>*</sup></B>
                            <br>
                            <input type="password" name="password2" size=10 
                                   value='<%=formHandler.getPassword2()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("password2")%></font>
                        </td>
                    <br>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td colspan=2 valign=top>
                            <B>In welke regio zal er veel gebruik worden gemaakt van Dryves?</B>
                            <br>
                            <input type="checkbox" name="faveTech" value="Noord-Holland"
                                   <%=formHandler.isCbSelected("Noord-Holland")%>>Noord-Holland    
                            <input type="checkbox" name="faveTech" value="Zuid-Holland" 
                                   <%=formHandler.isCbSelected("Zuid-Holland")%>>Zuid-Holland  
                            <input type="checkbox" name="faveTech" value="Limburg" 
                                   <%=formHandler.isCbSelected("Limburg")%>>Limburg<br>
                            <input type="checkbox" name="faveTech" value="Zeeland" 
                                   <%=formHandler.isCbSelected("Zeeland")%>>Zeeland  
                            <input type="checkbox" name="faveTech" value="Utrecht" 
                                   <%=formHandler.isCbSelected("Utrecht")%>>Utrecht  
                            <input type="checkbox" name="faveTech" value="Friesland" 
                                   <%=formHandler.isCbSelected("Friesland")%>>Friesland<br>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td colspan=2 valign=top>
                            <B>Wil je op de hoogte gehouden worden van onze nieuwsbrief?</B>
                            <br>
                            <input type="radio" name="notify" value="Ja" 
                                   <%=formHandler.isRbSelected("Ja")%>>Ja       
                            <input type="radio" name="notify" value="Nee" 
                                   <%=formHandler.isRbSelected("Nee")%>> Nee 
                            <br><br></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td colspan=2 align=center>
                            <input type="submit" value="Submit"> <input type="reset"  
                                                                        value="Reset">
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>