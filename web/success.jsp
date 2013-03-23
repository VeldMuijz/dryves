<jsp:useBean id="formHandler" class="test.FormBean" scope="request"/>
<html>
    <body>
    <center>
        <table cellpadding=1 cellspacing=1 border="1" >
            <th bgcolor="lightblue" colspan=2>
                <font size=5>User Registration Successfull!</font>
            </th>
            <font size=4>
            <tr bgcolor="lightblue">
                <td valign=top> 
                    <b>First Name</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="firstName"/>
                </td>
                <td valign=top>
                    <b>Last Name</b>
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
                    <b>Zip Code</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="zip"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td valign=top colspan=2>
                    <b>User Name</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="userName"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td colspan=2 valign=top>
                    <b>What Technology are you interested in?</b>
                    <br>
                    <%
                      String[] faveTech = formHandler.getFaveTech();
                      if (!faveTech[0].equals("1")) {
                        out.println("<ul>");
                        for (int i=0; i<faveTech.length; i++)  
                          out.println("<li>"+faveTech[i]);
                        out.println("</ul>");
                      } else out.println("Nothing was selected");
                    %>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td colspan=2 valign=top>
                    <b>Would you like to receive e-mail notifications on our special 
                        sales?</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="notify"/>
                </td>
            </tr>
            </font>
        </table>
    </center>
</body>
</html>

