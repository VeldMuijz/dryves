<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

<%@page import="Dryves.Model.Lid"%>
<%@page import="Dryves.Controller.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="Dryves.Controller.Login"%>


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

            <img src="images/background1.jpg" />

        </div>

        <div class="drvyesWrapper">

            <div class="logo">    
                <img src="images/Logo_Dryves.png" />
            </div>


                 
            
            <jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="home"></jsp:param>
            </jsp:include>

            <div class="contentPanel">

                 <fmt:message bundle="${rb}" key="ditisdeloginpage" />

                <br /><br />

                <form action="LoginServlet" method="post">
                    <table style="width:320px;">
                        <tr>
                            <td>
                                <fmt:message bundle="${rb}" key="gebruikersnaam" />
                            </td>
                            <td>
                                <input type="text" id="email" name="email" style="width:205px;"/>  
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <fmt:message bundle="${rb}" key="wachtwoordlogin" />
                            </td>
                            <td><input type="password" id="wachtwoord" name="wachtwoord" style="width:205px;" /></td>
                        </tr>
                        <tr>
                            <td>
                                &nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td>

                            </td>
                            <td><button type="submit" style="float:right;" />Login</button></td>
                        </tr>
                    </table>

							Nog geen lid? Klik <a href="registratie.jsp">hier</a> om u gratis te registreren<br />
							of login met uw Facebook account.<br/> <br/>




                    <fb:profile-pic uid="loggedinuser" facebook-logo="true" linked="false" 
                                    width="80" height="80"></fb:profile-pic>













                </form>
                <script src="http://connect.facebook.net/en_US/all.js"></script>

                <fb:login-button autologoutlink="true" onlogin="OnRequestPermission(); VanFaceNaarP() "   >
                  <fmt:message bundle="${rb}" key="loginmetfacebook" /> </fb:login-button>

                <script language="javascript" type="text/javascript">


                    window.fbAsyncInit = function() {
                        // init the FB JS SDK
                        FB.init({
                            appId: '475117289226684', // App ID from the app dashboard

                            status: true, // Check Facebook Login status
                            xfbml: true                                  // Look for social plugins on the page
                        });

                        //Na het inloggen wordt deze door verwezen naar de volgende pagina
                        FB.Event.subscribe('auth.login', function(response) {

                            VanFaceNaarP();






                        });



                    }





// Met deze functie vargen we toestemming en vargen we wat zijn email is.
                    function getEmail() {
                        FB.login(function(response) {
                            if (response.session && response.perms) {
                                FB.api('/me', function(response) {

                                }
                                );
                            }
                        }, {perms: 'email'});
                    }









                    function VanFaceNaarP() {

                        FB.api("/me",
                                function(response) {
                                    document.getElementById("idp").innerHTML = response.id;
                                    document.getElementById("naamp").innerHTML = response.name;
                                    document.getElementById("voornaamp").innerHTML = response.first_name;
                                    document.getElementById("achternaamp").innerHTML = response.last_name;
                                    document.getElementById("sexp").innerHTML = response.gender;
                                    document.getElementById("emailp").innerHTML = response.email;

                                    document.getElementById("idh").value = document.getElementById("idp").innerHTML;
                                    document.getElementById("naamh").value = document.getElementById("naamp").innerHTML;
                                    document.getElementById("voornaamh").value = document.getElementById("voornaamp").innerHTML;
                                    document.getElementById("achternaamh").value = document.getElementById("achternaamp").innerHTML;
                                    document.getElementById("sexh").value = document.getElementById("sexp").innerHTML;
                                    document.getElementById("emailh").value = document.getElementById("emailp").innerHTML;
                                  
                                  document.getElementById('v').submit();


                                });


                    }

                </script>

                <form id="v" action="FacebookLoginServlet" method="get">

                    <input type="hidden" id="idh" name="id" value="" />
                    <input type="hidden" id="naamh" name="naam" value="" />
                    <input type="hidden" id="voornaamh" name="voornaam" value="" />
                    <input type="hidden" id="achternaamh" name="achternaam" value="" />
                    <input type="hidden" id="sexh" name="sex" value="" />
                    <input type="hidden" id="emailh" name="email" value="" />









                    <p hidden="true" id="idp" ></p> 
                    <p hidden="true"  type="hidden" id="naamp" ></p>
                    <p hidden="true"  id="voornaamp"></p> 
                    <p hidden="true"  id="achternaamp" ></p>
                    <p hidden="true"  id="sexp"></p> 
                    <p hidden="true"  id="emailp"></p> 

                    <br >


                   

                </form> 


            </div>

        </div>

    </body>
</html>



