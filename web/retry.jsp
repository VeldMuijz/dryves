<jsp:useBean id="formHandler" class="test.FormBean" scope="request"/>
<html> 
    <body>
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
                            <input type="text" name="Voornaam" 
                                   value='<%=formHandler.getFirstName()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("Voornaam")%></font>
                        </td>
                        <td  valign=top>
                            <B>Achternaam<sup>*</sup></B>
                            <br>
                            <input type="text" name="Achternaam" 
                                   value='<%=formHandler.getLastName()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("Achternaam")%></font>
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
                            <input type="text" name="Postcode" value='<%=formHandler.getZip()%>' size=5  
                                   maxlength=6>
                            <br><font size=2 color=red><%=formHandler.getErrorMsg("Postcode")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2> 
                            <B>Gebruikersnaam<sup>*</sup></B>
                            <br>
                            <input type="text" name="Gebruikersnaam" size=10 
                                   value='<%=formHandler.getUserName()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("Gebruikersnaam")%></font>
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



