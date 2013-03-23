<%-- 
    Document   : index
    Created on : 11-mrt-2013, 19:59:48
    Author     : RickSpijker
--%>

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
            
Schrijf je per direct in en word lid! Ontdek de vele voordelen die de Dryves community te bieden heeft. 

<br /><br />
            <form action="/dryves/proces.jsp" method=post>
            <center>
                <table cellpadding=2 cellspacing=1 border="1" bgcolor="lightblue">
                    <th bgcolor="lightblue" colspan=2>
                        <font size=5>User Registration</font>
                        <br>
                        <font size=2 color="red"><sup>*</sup> Required Fields</font>
                    </th>
    <tr bgcolor="lightblue">
                        <td valign=top> 
                            <b>First Name<sup>*</sup></b> 
                            <br>
                            <input type="text" name="firstName" value="" size=20 maxlength=20></td>
                        <td  valign=top>
                            <b>Last Name<sup>*</sup></b>
                            <br>
                            <input type="text" name="lastName" value="" size=15 maxlength=20></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <b>E-Mail<sup>*</sup></b> 
                            <br>
                            <input type="text" name="email" value="" size=25  maxlength=125>
                            <br></td>
                        <td  valign=top>
                            <b>Zip Code<sup>*</sup></b> 
                            <br>
                            <input type="text" name="zip" value="" size=10  maxlength=8></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2>
                            <b>User Name<sup>*</sup></b>
                            <br>
                            <input type="text" name="userName" size=20 value=""  maxlength=10>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <b>Password<sup>*</sup></b> 
                            <br>
                            <input type="password" name="password1" size=10 value="" maxlength=10></td>
                        <td  valign=top>
                            <b>Confirm Password<sup>*</sup></b>
                            <br>
                            <input type="password" name="password2" size=10 value="" maxlength=10></td>
                    <br>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td  valign=top colspan=2>
                            <b>What Technology are you interested in?</b>
                            <br>
                            <input type="checkbox" name="faveTech" value="Java">Java    
                            <input type="checkbox" name="faveTech" value="JSP">JSP  
                            <input type="checkbox" name="faveTech" value="Struts 1.1">Struts 1.1<br>
                            <input type="checkbox" name="faveTech" value="Ajax">Ajax  
                            <input type="checkbox" name="faveTech" value="Struts 2.0 ">Struts 2.0  
                            <input type="checkbox" name="faveTech" value="Servlets">Servlets<br>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td  valign=top colspan=2>
                            <b>Would you like to receive e-mail notifications on our special 
                                sales?</b>
                            <br>
                            <input type="radio" name="notify" value="Yes" checked>Yes 

                            <input type="radio" name="notify" value="No" > No 
                            <br><br></td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td  align=center colspan=2>
                            <input type="submit" value="Submit"> <input type="reset"  
                                                                        value="Reset">
                        </td>
                    </tr>
                </table>
            </center>
        </form>
    </body>
</html>