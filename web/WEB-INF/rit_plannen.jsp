<%-- 
    Document   : rit_plannen
    Created on : 23-mrt-2013, 19:59:48
    Author     : Jeroen Veldhuijzen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves - Rit plannen </title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>
		        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>

		<script type="text/javascript"
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAQ5JCTE_OQi2SCYXO6urNY17FW5DaOVvU&sensor=false">
		</script>
		<script type="text/javascript">
			//Zorg dat de lijn aan te passen is met de muis
			var rendererOptions = {
				draggable: true
			};

			var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
			var directionsService = new google.maps.DirectionsService();
			var map;

			/**
			 * Initialiseer de map en zet de instellingen in de mapOptions
			 * @@author Jeroen Veldhuijzen
			 * @returns {undefined}
			 * 
			 */
			function initialize() {

				var amsterdam = new google.maps.LatLng(52.360506, 4.919128);

				// Geeft de instellingen mee aan de map
				var mapOptions = {
					zoom: 7,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					center: amsterdam
				};

				map = new google.maps.Map(document.getElementById("mapcanvas"), mapOptions);
				directionsDisplay.setMap(map);
				google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
					computeTotalDistance(directionsDisplay.directions);
				});
				calcRoute();

			}

			/**
			 * @author Jeroen Veldhuijzen
			 * @returns {undefined}
			 */
			function calcRoute() {
				var start = document.getElementById("start").value;
				var end = document.getElementById("end").value;
				var request = {
					origin: start,
					destination: end,
					travelMode: google.maps.TravelMode.DRIVING
				};

				directionsService.route(request, function(result, status) {
					if (status == google.maps.DirectionsStatus.OK) {
						directionsDisplay.setDirections(result);
						console.log(result);

					}
				});
				setRouteInfo();
			}

			/**
			 * @author Jeroen Veldhuijzen
			 * @param {type} result
			 * @returns {undefined}
			 */
			function computeTotalDistance(result) {
				var total = 0;
				var myroute = result.routes[0];
				for (i = 0; i < myroute.legs.length; i++) {
					total += myroute.legs[i].distance.value;
				}
				total = total / 1000
				document.getElementById("total").innerHTML = total + " km";
				document.getElementById("hiddenafstand").value = total;
				setRouteinfo();
			}

			function setRouteinfo() {
				var startaddress;
				var endaddress;
				var waypoints;


				startaddress = directionsDisplay.directions.routes[0].legs[0].start_address;
				endaddress = directionsDisplay.directions.routes[0].legs[0].end_address;
				waypoints = directionsDisplay.directions.routes[0].legs[0].via_waypoints;

				//zet de waardes voor het overzicht
				document.getElementById("startadres").innerHTML = startaddress;
				document.getElementById("end").value = endaddress;

				//zet de waardes voor de invoervelden
				document.getElementById("eindadres").innerHTML = endaddress;
				document.getElementById("start").value = startaddress;

				//zet de waardes voor de verborgenvelden (gebruikt door servlet)
				document.getElementById("hiddenstart").value = startaddress;
				document.getElementById("hiddenend").value = endaddress;
				document.getElementById("hiddenwaypoints").value = waypoints;



			}




			function isChecked(blnchecked)
			{
				if (blnchecked)
				{
					document.getElementById("dagenCheckBox").style.display = "";

				}
				else
				{
					document.getElementById("dagenCheckBox").style.display = "none";
				}



			}
			function isCompleet() {
				var startadres = document.getElementById("start").value;
				var eindadres = document.getElementById("end").value;
				var begindatum = document.getElementById("begindatum").value;
				var tijd = document.getElementById("tijd").value;
				var einddatum = document.getElementById("einddatum").value;
				var herhaling = document.getElementById("herhaling");
				var maa = document.getElementsByName("ma");
				var din = document.getElementsByName("di");
				var woe = document.getElementsByName("wo");
				var don = document.getElementsByName("don");
				var vri = document.getElementsByName("vr");
				var zat = document.getElementsByName("za");
				var zon = document.getElementsByName("zo");


				if (startadres === "") {
					alert("Graag een bestaand startpunt invullen.");
					return false;
				} else if (eindadres === "") {
					alert("Graag een bestaand eindpunt invullen.");
					return false;
				} else if (begindatum === "") {
					alert("Graag een begindatum invullen.");
					return false;
				} else if (tijd === "") {
					alert("Graag een tijdstip invullen.");
					return false;
				}
				else if ((herhaling.checked) && (einddatum === "")) {
					alert("Graag een einddatum invullen.");
					return false;
				} //TODO: Invoegen controle op dagen

				return true;
			}
			
			 $(function() {
                            
                        $( "#begindatum" ).datepicker();
                        
                        
                        });


        </script>

	</head>
	<body onload="initialize();">

		<div class="background">

			<img src="${currentSessionUser.getAchtergrond()}" />

		</div>

		<div class="drvyesWrapper">

			<div class="logo">    
				<img src="images/Logo_Dryves.png" />
			</div>


			            <jsp:include page="/WEB-INF/navigatie.jsp"  flush="true">
              <jsp:param name="menu_active" value="mijndryves"></jsp:param>
              </jsp:include>

			<div class="contentPanel">


				<div class="invoerveld">
					<form action="RitPlannen" method="post" onsubmit="return isCompleet();">
						<fmt:message bundle="${rb}" key="startadres" /><br/>
						<input type="text" id="start" name="start" onchange="calcRoute();" style ="width: 350; float: right:"><br />
						<fmt:message bundle="${rb}" key="eindadres" /> <br/>
						<input type="text" id="end" name="end" onchange="calcRoute();" style ="width: 350; float: right:"> <br />	

						<fmt:message bundle="${rb}" key="begindatum" /><br/> <input type="date" id="begindatum" name="begindatum"> <br/>
						<fmt:message bundle="${rb}" key="tijd" /> <br/> <input type="text" id="tijd" name="tijd"> <br/><br/>
						<td> <fmt:message bundle="${rb}" key="herhaling" />
							<input type="checkbox" id="herhaling" name="herhaling" onclick="isChecked(this.checked);"> </td>
						<br/><br/>
						<div id="dagenCheckBox" style="display: none;">
							<fmt:message bundle="${rb}" key="selectherhaaldagen" /> <br/>
							<table>
								<td><input type="checkbox" name="ma" value="ma"> <fmt:message bundle="${rb}" key="ma" /> </input> </td>
								<td><input type="checkbox" name="di" value="di"> <fmt:message bundle="${rb}" key="di" /> </input> </td>
								<td><input type="checkbox" name="wo" value="wo"> <fmt:message bundle="${rb}" key="wo" /> </input> </td>
								<td><input type="checkbox" name="don" value="do"> <fmt:message bundle="${rb}" key="do" /> </input> </td>
								<td><input type="checkbox" name="vr" value="vr"> <fmt:message bundle="${rb}" key="vr" /> </input> </td>
								<td><input type="checkbox" name="za" value="za"> <fmt:message bundle="${rb}" key="za" /> </input> </td>
								<td><input type="checkbox" name="zo" value="zo"> <fmt:message bundle="${rb}" key="zo" /> </input> </td>
							</table>
							<br/>
							<fmt:message bundle="${rb}" key="einddatum" /> <input type="date" id="einddatum" name="einddatum"> </div>


						<br /> <br />

						<fmt:message bundle="${rb}" key="selectautogegevens" /> <br/>								
						<table><td><fmt:message bundle="${rb}" key="aantalzit" /> <select name="aantalZitplaatsen">	
									<option value="1"> 1 </option>
									<option value="2"> 2 </option> 
									<option value="3"> 3 </option>
									<option value="4"> 4 </option>
									<option value="5"> 5 </option>
									<option value="6"> 6 </option>
								</select></td>

							<td><fmt:message bundle="${rb}" key="soortbrandstof" /> <select id="soortBrandstof" name="soortBrandstof">
									<option value="benzine"> <fmt:message bundle="${rb}" key="benzine" /> </option>
									<option value="diesel"> <fmt:message bundle="${rb}" key="diesel" /> </option>
									<option value="gas/lpg"> <fmt:message bundle="${rb}" key="gas" /> </option>						
									<option value="electrisch"> <fmt:message bundle="${rb}" key="electrisch" /> </option>
									<option value="hybride"> <fmt:message bundle="${rb}" key="hybride" /> </option>
								</select></td></table> <br/><br/>

						<input type="checkbox" name="aanbieden"><fmt:message bundle="${rb}" key="directaanbieden" /></input>			
						<button type="submit"><fmt:message bundle="${rb}" key="klik" /></button>

						<input id="hiddenstart" name="hiddenstart" style="display: none;" ></input>
						<input id="hiddenend" name="hiddenend" style="display: none;"></input>
						<input id="hiddenwaypoints" name="hiddenwaypoints" style="display: none;"></input>
						<input id="hiddenafstand" name="hiddenafstand" style="display: none;"></input>

					</form>


				</div>


				<div class="mapcontent">
					<div id="mapcanvas"></div>
					<div id ="ritoverzicht"> 
						<table>
							<td><strong> <fmt:message bundle="${rb}" key="totaleafstand" /> </strong> </br>
								<div id="total" name="total"></div> </td> <br/>

							<td><strong><fmt:message bundle="${rb}" key="startadres" /></strong><br/>
								<div id="startadres" name="startadres"></div></td>

							<td><strong><fmt:message bundle="${rb}" key="eindadres" /></strong> <br/>
								<div id="eindadres" name="eindadres"></div></td>

						</table>



					</div> 
				</div> 
			</div>

		</div>

	</body>
</html>
