<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>
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


                <form action="/dryves/proces.jsp" method=post>
                    <center>
                        <form action="proces.jsp" method=post>
                            <center>
                                <div class="regformheader">
                                    <font size=5>Registratie formulier</font>
                                    <br>
                                    <font size=2 color="red"><sup>*</sup> Verplichte velden </font>
                                </div>
                                <div class="formInput">
                                    <B>Voornaam<sup>*</sup></B> 
                                    <input type="text" name="vnaam" 
                                           value='<%=formHandler.getVnaam()%>' size=15 maxlength=20>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("vnaam")%></font>
                                </div> 
                                <div class="formInput">
                                    <B>Achternaam<sup>*</sup></B>
                                    <br>
                                    <input type="text" name="anaam" 
                                           value='<%=formHandler.getAnaam()%>' size=15 maxlength=20>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("anaam")%></font>
                                </div>
                                <div class="formInput">
                                    <B>Tussenvoegsel<sup></sup></B>
                                    <br>
                                    <input type="text" name="tvoegsel" value='<%=formHandler.getTvoegsel()%>' 
                                           size=5 maxlength=20>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("tvoegsel")%></font>
                                </div>
                                
                                <div class="formInput">
                                    <B>Rekening nummer<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="reknr" value='<%=formHandler.getReknr()%>' size=8  
                                           maxlength=10>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("reknr")%></font>
                                </div>

                                <div class="formInput">
                                    <B>E-Mail<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="email" value='<%=formHandler.getEmail()%>' 
                                           size=25  maxlength=125>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("email")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Adres<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="adres" value='<%=formHandler.getAdres()%>' size=15 maxlength=20>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("adres")%></font>
                                </div>
                                
                                <div class="formInput">
                                    <B>Huisnummer<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="huisnummer" value='<%=formHandler.getHuisnummer()%>' size=5 maxlength=10>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("homenumber")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Postcode<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="postcode" value='<%=formHandler.getPostcode()%>' size=5  
                                           maxlength=6>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("postcode")%></font>
                                </div>
                                
                                 <div class="formInput">
                                    <B>Stad<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="stad" value='<%=formHandler.getStad()%>' size=15 maxlength=20>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("stad")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Wachtwoord<sup>*</sup></B> 
                                    <br>
                                    <input type="password" name="wachtwoord" size=10 
                                           value='<%=formHandler.getWachtwoord()%>'  maxlength=10> <font size=2 
                                           color=red><%=formHandler.getErrorMsg("wachtwoord")%></font>
                                </div>
                                
                                                 <div class="formInput">
                                    <B>Wachtwoord<sup>*</sup></B> 
                                    <br>
                                    <input type="wachtwoord" name="wachtwoord" size=10 
                                           value='<%=formHandler.getWachtwoord()%>'  maxlength=10> <font size=2 
                                           color=red><%=formHandler.getErrorMsg("wachtwoord")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Bevestig wachtwoord<sup>*</sup></B>
                                    <br>
                                    <input type="wachtwoord2" name="wachtwoord2" size=10 
                                           value='<%=formHandler.getWachtwoord2()%>'  maxlength=10>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("wachtwoord2")%></font>
                                </div>

                                <div class="formInputProv">
                                    <B>Geslacht?</B>
                                    <br>
                                    <input type="radio" name="notify" value="Ja" 
                                           <%=formHandler.isRbSelected("Ja")%>>M       
                                    <input type="radio" name="notify" value="Nee" 
                                           <%=formHandler.isRbSelected("Nee")%>>V 
                                </div>
                                </div>
                            </center>
                        </form>
                        </body>
                        </html>