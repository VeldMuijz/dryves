<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

package Dryves;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

<%@page import="Dryves.Lid"%>
<jsp:useBean id="formHandler" class="Dryves.Lid" scope="request"/>
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

                <div style="float:right; margin-right: -1px;"><button  onclick="window.location = 'login.jsp';">Login</button></div>

                <button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location = 'faq.jsp';">FAQ</button>

            </div>

            <div class="contentPanel"> 


                <form action="proces.jsp" method=post>
            
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
                            <input type="text" name="voornaam" 
                                   value='<%=formHandler.getVnaam()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("voornaam")%></font>
                        </td>
                        <td  valign=top>
                            <B>Achternaam<sup>*</sup></B>
                            <br>
                            <input type="text" name="achternaam" 
                                   value='<%=formHandler.getAnaam()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("achternaam")%></font>
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
                            <input type="text" name="zip" value='<%=formHandler.getPostcode()%>' size=5  
                                   maxlength=6>
                            <br><font size=2 color=red><%=formHandler.getErrorMsg("postcode")%></font>
                        </td>
                    </tr>

                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <B>Wachtwoord<sup>*</sup></B> 
                            <br>
                            <input type="wachtwoord" name="wachtoord" size=10 
                                   value='<%=formHandler.getWachtwoord()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("wachtwoord")%></font>
                        </td>
                        <td  valign=top>
                            <B>Bevestig wachtwoord<sup>*</sup></B>
                            <br>
                            <input type="wachtwoord2" name="wachtwoord2" size=10 
                                   value='<%=formHandler.getWachtwoord2()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("wachtwoord2")%></font>
                        </td>
                    <br>
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
