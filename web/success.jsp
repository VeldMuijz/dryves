<jsp:useBean id="formHandler" class="test.FormBean" scope="request"/>
<html>
    <body>
    <center>
        <table cellpadding=1 cellspacing=1 border="1" >
            <th bgcolor="lightblue" colspan=2>
                <font size=5>Registratie succesvol!</font>
            </th>
            <font size=4>
            <tr bgcolor="lightblue">
                <td valign=top> 
                    <b>First Name</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="firstName"/>
                </td>
                <td valign=top>
                    <b>Achternaam</b>
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
                    <b>postcode</b> 
                    <br>
                    <jsp:getProperty name="formHandler" property="postcode"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td valign=top colspan=2>
                    <b>Gebruikersnaam</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="userName"/>
                </td>
            </tr>
            <tr bgcolor="lightblue">
                <td colspan=2 valign=top>
                    <b>In welke regio zal er veel gebruik worden gemaakt van Dryves?</b>
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
                    <b>Wil je onze nieuwsbrief ontvangen?</b>
                    <br>
                    <jsp:getProperty name="formHandler" property="notify"/>
                </td>
            </tr>
            </font>
        </table>
    </center>
</body>
</html>

