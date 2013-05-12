<%-- 
    Document   : beoordelen
    Created on : May 11, 2013, 4:02:22 PM
    Author     : jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
				
				<h2>Beoordeling van een rit</h2>
				

				<div class="invoerveld">
					<form action="LidBeoordelen" method="post">
						<input name="aankoopnr" hidden="true" value="${sessieBeoordeling.aankoopnr}"/>
						Stiptheid
						<select name="stiptheid" > 
									<option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						Betrouwbaarheid
						<select name="betrouwbaarheid" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						Gezelligheid
						<select name="gezelligheid" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						Rijstijl
						<select name="rijstijl" > <option value="5"> 5 </option>
									<option value="4"> 4 </option> 
									<option value="3"> 3 </option>
									<option value="2"> 2 </option>
									<option value="1"> 1 </option> 
						</select>
						<br/><br/>
						Opmerking
						<textarea class="vrijtextinputveld" name="opmerking" maxlength="150" placeholder="Hier kunt u een opmerking kwijt van 150 tekens"></textarea>
						<br/><br/>
						<button type="submit"/>
					</form>


				</div>


			</div>

		</div>

    </body>
</html>
