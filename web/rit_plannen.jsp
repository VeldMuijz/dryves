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
				//saveWaypoints();

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
			}
			
			function saveWaypoints (){
				var wparray=[], wp;
				var data={};	
				var routeLeg = directionsDisplay.directions.routes[1].legs[1];
				data.start={'lat': routeLeg.start_location(), 'lng': routeLeg.start_location()};
				data.end={'lat': routeLeg.end_location(), 'lng': routeLeg.end_location()};
				waypoint = routeLeg.via.waypoints;
				
				for(var i=0; i<wparray.length; i++){
					wparray[i] = [wp[i].lat(), wp[i].lng()]
				};
					
				data.waypoints = wp;
				console.log(wparray);			
				
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

			<div id="invoerveld">
				<form>
					<button onclick="saveWaypoints();"> klik</button>
					Start adres: <input type="text" id="start" onchange="calcRoute();" style ="width: 350; float: right:"><br />
					Eind adres: <input type="text" id="end" onchange="calcRoute();" style ="width: 350; float: right:"><br />
					Datum: <input type="text" id="datum"><br />
					Aantal plaatsen: <select>	
						<option value="1"> 1 </option>
						<option value="1"> 2 </option> 
						<option value="1"> 3 </option>
						<option value="1"> 4 </option>
						<option value="1"> 5 </option>
					</select><br /> <br />
					Herhaling <br /> <br />

					<input type="checkbox" name="ma" value="ma"> Ma </input> 
					<input type="checkbox" name="di" value="di"> Di </input> 
					<input type="checkbox" name="wo" value="wo"> Wo </input> 
					<input type="checkbox" name="do" value="do"> Do </input>
					<input type="checkbox" name="vr" value="vr"> Vr </input>
					<input type="checkbox" name="za" value="za"> Za </input>
					<input type="checkbox" name="zo" value="zo"> Zo </input> <br /> <br />
					Begindatum: <input type="date" id="begindatum"> 
					Einddatum: <input type="date" id="einddatum"> 
					
					
					<br /> <br />
					Soort brandstof: <select>
						<option value="benzine"> Benzine </option>
						<option value="diesel"> Diesel </option>
						<option value="gas/lpg"> Gas/LPG </option>						
						<option value="electrisch"> Electrisch </option>
						<option value="hybride"> Hybride </option>
					</select>
				</form>
				<div style="float:right;"><strong><div id="total"> </div> Totaal aantal km </strong>
				</div></div>

        </div>

	</div>

</body>
</html>
