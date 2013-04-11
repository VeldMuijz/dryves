<%-- 
    Document   : rit_plannen
    Created on : 23-mrt-2013, 19:59:48
    Author     : Jeroen Veldhuijzen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dryves - Rit plannen </title>
        <link type="text/css" rel="stylesheet" href="css/dryver.css"/>

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

			function saveWaypoints() {
				var wparray = [], wp;
				var route = {}
				var routeLeg = directionsDisplay.directions.routes[0].legs[0];
				route.start = {'lat': directionsDisplay.start_location, 'lng': directionsDisplay.start_location}
				route.end = {'lat': directionsDisplay.end_location, 'lng': directionsDisplay.end_location}
				wp = routeLeg.via_waypoints

				for (var i = 0; i < wparray.length; i++) {
					wparray[i] = [wp[i].lat(), wp[i].lng()];
				}


				//waypoints = wp;
				console.log(wp);
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

        </script>

	</head>
	<body onload="initialize();">

		<div class="background">

			<img src="images/background1.jpg" />

		</div>

		<div class="drvyesWrapper">

			<div class="logo">    
				<img src="images/Logo_Dryves.png" />
			</div>


			<div class="navigation">

				<div style="float:right; margin-right: -1px;"><button  onclick="window.location = 'login.jsp';">Login</button></div>

				<button onclick="window.location = 'index.jsp';">Home</button><button onclick="window.location = 'watisdryves.jsp';">Wat is Dryves</button><button>FAQ</button>

			</div>

			<div class="contentPanel">


				<div class="invoerveld">
					<form action="RitPlannen" method="get">
						Start adres: <br/>
						<input type="text" id="start" name="start" onchange="calcRoute();" style ="width: 350; float: right:"><br />
						Eind adres: <br/>
						<input type="text" id="end" name="end" onchange="calcRoute();" style ="width: 350; float: right:"> <br />	

						Begindatum:<br/> <input type="date" id="begindatum" name="begindatum"> <br/>
						Tijd: <br/> <input type="text" id="tijd" name="tijd"> <br/><br/>
						<td> Herhaling 
							<input type="checkbox" id="herhaling" name="herhaling" onclick="isChecked(this.checked);"></td>
						<br/><br/>
						<div id="dagenCheckBox" style="display: none;">
							Selecteer hier uw herhaaldagen: <br/>
							<table>
								<td><input type="checkbox" name="ma" value="ma"> Ma </input> </td>
								<td><input type="checkbox" name="di" value="di"> Di </input> </td>
								<td><input type="checkbox" name="wo" value="wo"> Wo </input> </td>
								<td><input type="checkbox" name="do" value="do"> Do </input> </td>
								<td><input type="checkbox" name="vr" value="vr"> Vr </input> </td>
								<td><input type="checkbox" name="za" value="za"> Za </input> </td>
								<td><input type="checkbox" name="zo" value="zo"> Zo </input> </td>
							</table>
							<br/>
							Einddatum: <input type="date" id="einddatum"> </div>


						<br /> <br />

						Selecteer hier uw autogegevens die relevant zijn voor de rit: <br/>								
						<table><td>Aantal zitplaatsen: <select name="aantalZitplaatsen">	
									<option value="1"> 1 </option>
									<option value="1"> 2 </option> 
									<option value="1"> 3 </option>
									<option value="1"> 4 </option>
									<option value="1"> 5 </option>
								</select></td>

							<td>Soort brandstof: <select id="soortBrandstof" name="soortBrandstof">
									<option value="benzine"> Benzine </option>
									<option value="diesel"> Diesel </option>
									<option value="gas/lpg"> Gas/LPG </option>						
									<option value="electrisch"> Electrisch </option>
									<option value="hybride"> Hybride </option>
								</select></td></table> <br/><br/>

						<input type="checkbox" name="aanbieden">Direct aanbieden</input>			
						<button type="submit"> Klik </button>

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
							<td><strong> Totale afstand: </strong> </br>
								<div id="total" name="total"></div> </td> <br/>

							<td><strong>Startadres:</strong><br/>
								<div id="startadres" name="startadres"></div></td>

							<td><strong>Eindadres:</strong> <br/>
								<div id="eindadres" name="eindadres"></div></td>

						</table>



					</div> 
				</div> 
			</div>

		</div>

	</body>
</html>
