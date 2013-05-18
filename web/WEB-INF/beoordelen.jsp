<%-- 
    Document   : beoordelen
    Created on : May 11, 2013, 4:02:22 PM
    Author     : jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="background">

			<img src="${currentSessionUser.getAchtergrond()}" />

		</div>


		<div class="drvyesWrapper">

			<div class="logo">    
				<img src="images/Logo_Dryves.png" />
			</div>


			<jsp:include page="navigatie.jsp" flush="true">
                <jsp:param name="menu_active" value="mijndryves"></jsp:param>
            </jsp:include>

			<div class="contentPanel">
                            
				<fmt:message bundle="${rb}" key="beoordeelrit" /><br /><br />
				
				

				<div class="invoerveld">
					<form action="LidBeoordelen" method="post">
						<input name="aankoopnr" hidden="true" value="${sessieBeoordeling.aankoopnr}"/>
						<fmt:message bundle="${rb}" key="stiptheid" />
						<select name="stiptheid" > 
									<option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						<fmt:message bundle="${rb}" key="betrouwbaarheid" />
						<select name="betrouwbaarheid" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						<fmt:message bundle="${rb}" key="gezelligheid" />
						<select name="gezelligheid" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						<fmt:message bundle="${rb}" key="rijstijl" />
						<select name="rijstijl" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						<fmt:message bundle="${rb}" key="opmerking" />
						<textarea class="vrijtextinputveld" name="opmerking" maxlength="150" placeholder="<fmt:message bundle="${rb}" key="opmerkingveld" />"></textarea>
						<br/><br/>
						<button type="submit"/>
					</form>


				</div>


			</div>

		</div>

    </body>
</html>
