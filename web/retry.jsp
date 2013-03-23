<jsp:useBean id="formHandler" class="test.FormBean" scope="request"/>
<html> 
    <body>
        <form action="proces.jsp" method=post>
            <center>
                <table cellpadding=4 cellspacing=2 border=0>
                    <th bgcolor="lightblue" colspan=2>
                        <font size=5>User Registration</font>
                        <br>
                        <font size=2 color="red"><sup>*</sup> Required Fields </font>
                    </th>
                    <tr bgcolor="lightblue">
                        <td valign=top> 
                            <B>First Name<sup>*</sup></B> 
                            <br>
                            <input type="text" name="firstName" 
                                   value='<%=formHandler.getFirstName()%>' size=15 maxlength=20>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("firstName")%></font>
                        </td>
                        <td  valign=top>
                            <B>Last Name<sup>*</sup></B>
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
                            <B>Zip Code<sup>*</sup></B> 
                            <br>
                            <input type="text" name="zip" value='<%=formHandler.getZip()%>' size=5  
                                   maxlength=6>
                            <br><font size=2 color=red><%=formHandler.getErrorMsg("zip")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top colspan=2> 
                            <B>User Name<sup>*</sup></B>
                            <br>
                            <input type="text" name="userName" size=10 
                                   value='<%=formHandler.getUserName()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("userName")%></font>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td valign=top>
                            <B>Password<sup>*</sup></B> 
                            <br>
                            <input type="password" name="password1" size=10 
                                   value='<%=formHandler.getPassword1()%>'  maxlength=10>
                            <br><font size=2 
                                      color=red><%=formHandler.getErrorMsg("password1")%></font>
                        </td>
                        <td  valign=top>
                            <B>Confirm Password<sup>*</sup></B>
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
                            <B>What Technology are you interested in?</B>
                            <br>
                            <input type="checkbox" name="faveTech" value="Java"<%=formHandler.isCbSelected("Java")%>>Java    
                            <input type="checkbox" name="faveTech" value="JSP" 
                                   <%=formHandler.isCbSelected("JSP")%>>JSP  
                            <input type="checkbox" name="faveTech" value="Struts 1.1" 
                                   <%=formHandler.isCbSelected("Struts 1.1")%>>Struts 1.1<br>
                            <input type="checkbox" name="faveTech" value="Ajax" 
                                   <%=formHandler.isCbSelected("Ajax")%>>Ajax  
                            <input type="checkbox" name="faveTech" value="Struts 2.0" 
                                   <%=formHandler.isCbSelected("Struts 2.0")%>>Struts 2.0  
                            <input type="checkbox" name="faveTech" value="Servlets" 
                                   <%=formHandler.isCbSelected("Servlets")%>>Servlets<br>
                        </td>
                    </tr>
                    <tr bgcolor="lightblue">
                        <td colspan=2 valign=top>
                            <B>Would you like to receive e-mail notifications on our special 
                                sales?</B>
                            <br>
                            <input type="radio" name="notify" value="Yes" 
                                   <%=formHandler.isRbSelected("Yes")%>>Yes       
                            <input type="radio" name="notify" value="No" 
                                   <%=formHandler.isRbSelected("No")%>> No 
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


