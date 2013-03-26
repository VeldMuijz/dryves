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

                <div class="loginButton"><button  onclick="window.location = 'login.jsp';">Login</button></div>

                <button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button onclick="window.location = 'faq.jsp';">FAQ</button>

            </div>

            <div class="contentPanel">           

                <div class="regformheader">
                    <font size=5>Registratie succesvol!</font>
                </div>
                <div class="formInput">
                    <b>Voornaam</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="firstName"/>
                </div>

                <div class="formInput">   
                    <b>Achternaam</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="lastName"/>
                </div>

                <div class="formInput">   
                    <b>Tussenvoegsel</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="insertion"/>
                </div>

                <div class="formInput">
                    <b>E-Mail</b>                 
                    <jsp:getProperty name="formHandler" property="email"/>
                </div>  

                <div class="formInput">   
                    <b>Straat</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="street"/>
                </div>
                
                <div class="formInput">   
                    <b>Huisnummer</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="homenumber"/>
                </div>

                <div class="formInput">
                    <b>Postcode</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="zip"/>
                </div>
                
                <div class="formInput">   
                    <b>Stad</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="city"/>
                </div>

                <div class="formInput">
                    <b>Gebruikersnaam</b>
                    <jsp:getProperty name="formHandler" property="userName"/>
                </div>

                <div class="formInputProv">
                    <b>In welke regio zal er veel gebruik worden gemaakt van Dryves?</b>
                    <%
                        String[] faveTech = formHandler.getFaveTech();
                        if (!faveTech[0].equals("1")) {
                            out.println("<ul>");
                            for (int i = 0; i < faveTech.length; i++) {
                                out.println("<li>" + faveTech[i]);
                            }
                            out.println("</ul>");
                        } else {
                            out.println("Er was niets geselecteerd");
                        }
                    %>
                </div>

                <div class="formInput">
                    <b>Geslacht</b>
                    <jsp:getProperty name="formHandler" property="notify"/>
                </div>
            </div>
        </form>
</body>
</html>