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
                                    <input type="text" name="firstName" 
                                           value='<%=formHandler.getFirstName()%>' size=15 maxlength=20>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("firstName")%></font>
                                </div> 
                                <div class="formInput">
                                    <B>Achternaam<sup>*</sup></B>
                                    <br>
                                    <input type="text" name="lastName" 
                                           value='<%=formHandler.getLastName()%>' size=15 maxlength=20>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("lastName")%></font>
                                </div>
                                <div class="formInput">
                                    <B>Tussenvoegsel<sup></sup></B>
                                    <br>
                                    <input type="text" name="insertion" value='<%=formHandler.getInSertion()%>' 
                                           size=5 maxlength=20>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("insertion")%></font>
                                </div>

                                <div class="formInput">
                                    <B>E-Mail<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="email" value='<%=formHandler.getEmail()%>' 
                                           size=25  maxlength=125>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("email")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Straat<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="zip" value='<%=formHandler.getStreet()%>' size=15 maxlength=20>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("straat")%></font>
                                </div>
                                
                                <div class="formInput">
                                    <B>Huisnummer<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="homenumber" value='<%=formHandler.gethomenumber()%>' size=5 maxlength=10>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("homenumber")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Postcode<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="zip" value='<%=formHandler.getZip()%>' size=5  
                                           maxlength=6>
                                    <font size=2 color=red><%=formHandler.getErrorMsg("zip")%></font>
                                </div>
                                
                                 <div class="formInput">
                                    <B>Stad<sup>*</sup></B> 
                                    <br>
                                    <input type="text" name="city" value='<%=formHandler.getCity()%>' size=15 maxlength=20>  
                                    <font size=2 color=red><%=formHandler.getErrorMsg("city")%></font>
                                </div>

                                <div class="formInput"> 
                                    <B>Gebruikersnaam<sup>*</sup></B>
                                    <br>
                                    <input type="text" name="userName" size=10 
                                           value='<%=formHandler.getUserName()%>'  maxlength=10>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("userName")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Wachtwoord<sup>*</sup></B> 
                                    <br>
                                    <input type="password" name="password1" size=10 
                                           value='<%=formHandler.getPassword1()%>'  maxlength=10> <font size=2 
                                           color=red><%=formHandler.getErrorMsg("password1")%></font>
                                </div>

                                <div class="formInput">
                                    <B>Bevestig wachtwoord<sup>*</sup></B>
                                    <br>
                                    <input type="password" name="password2" size=10 
                                           value='<%=formHandler.getPassword2()%>'  maxlength=10>
                                    <font size=2 
                                          color=red><%=formHandler.getErrorMsg("password2")%></font>
                                </div>

                                <div class="formInputProv">
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