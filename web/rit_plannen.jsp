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
				// bij verandering geef ook de begin en eindadres en de waypoints
				setRouteinfo();
			}
			
			function setRouteinfo(){
				var startaddress;
				var endaddress;
				var waypoints;
				
				startaddress = directionsDisplay.directions.routes[0].legs[0].start_address ;
				endaddress = directionsDisplay.directions.routes[0].legs[0].end_address;
				waypoints = directionsDisplay.directions.routes[0].legs[0].via_waypoints;
				
				document.getElementById("start").innerHTML.replace(document.getElementById(start), startaddress);
				document.getElementById("end").innerHTML = endaddress;
				document.getElementById("waypoints").innerHTML = waypoints;
				
				
			}

			function saveWaypoints(){
			var wparray = [], wp;
				var route={}	
			var routeLeg = directionsDisplay.directions.routes[0].legs[0];
			route.start={'lat': directionsDisplay.start_location, 'lng': directionsDisplay.start_location}
			route.end={'lat': directionsDisplay.end_location, 'lng': directionsDisplay.end_location}
				wp = routeLeg.via_waypoints
				
				for(var i=0; i<wparray.length; i++){	
					wparray[i] = [wp[i].lat(),wp[i].lng()];
				}
				
			   
				//waypoints = wp;
				console.log(wp);	
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

				<div id="mapcanvas"></div>

				<div class="invoerveld">
					<form action="RitPlannen" method="get">
						<td>Start adres:</td> 
						<td><div id ="invoerveldadres"><input type="text" id="start" name="start" onchange="calcRoute();" style ="width: 350; float: right:"></td><br />
								<td>Eind adres:</td> 
								<td><input type="text" id="end" name="end" onchange="calcRoute();" style ="width: 350; float: right:"> </td><br />		
								<td>Tussenstations: </td>
								<td><input  id ="waypoints" name ="waypoints"> </text>  </td> <br /> <br />
								
								<div name ="aantalZitplaatsen">Aantal plaatsen: <select name="aantalZitplaatsen">	
										<option value="1"> 1 </option>
										<option value="1"> 2 </option> 
										<option value="1"> 3 </option>
										<option value="1"> 4 </option>
										<option value="1"> 5 </option>
									</select></div><br /> <br />

								Herhaling <br /> <br />

								<div id="dagenCheckBox"><input type="checkbox" name="ma" value="ma"> Ma </input> 
									<input type="checkbox" name="di" value="di"> Di </input> 
									<input type="checkbox" name="wo" value="wo"> Wo </input> 
									<input type="checkbox" name="do" value="do"> Do </input>
									<input type="checkbox" name="vr" value="vr"> Vr </input>
									<input type="checkbox" name="za" value="za"> Za </input>
									<input type="checkbox" name="zo" value="zo"> Zo </input> <br /> <br /> </div>

								<div class="invoerveld">Begindatum: <input type="date" id="begindatum" name="begindatum"> 
									Tijd: <input type="text" id="tijd" name="tijd"> 
									Einddatum: <input type="date" id="einddatum"> </div>
								<br /> <br />

								<div id="ritComboBox">Soort brandstof: <select id="soortBrandstof" name="soortBrandstof">
										<option value="benzine"> Benzine </option>
										<option value="diesel"> Diesel </option>
										<option value="gas/lpg"> Gas/LPG </option>						
										<option value="electrisch"> Electrisch </option>
										<option value="hybride"> Hybride </option>
									</select></div>

								<div style="float:right;"><strong><div id="total" name="total"> </div> Totaal aantal km </strong>
								</div>
								<input type="checkbox" name="aanbieden">Direct aanbieden</input>
								<button type="submit"> Klik </button>
					</form>

				</div>
				<div id="startadres"> <br/> <br/> </div>

			</div>

		</div>

	</body>
</html>
