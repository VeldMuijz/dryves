<%-- 
    Document   : ritkopen1
    Created on : Apr 29, 2013, 2:31:35 PM
    Author     : jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<script type="text/javascript"
				src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAQ5JCTE_OQi2SCYXO6urNY17FW5DaOVvU&sensor=false">
		</script>
		<script type="text/javascript">
			var map;
			var directionsDisplay;
			var directionsService;
			var stepDisplay;
			var markerArray = [];
			var marker;
			var waypoints = [];


			function initialize() {
				// Instantiate a directions service.
				directionsService = new google.maps.DirectionsService();

				// Create a map and center it on Manhattan.
				var amsterdam = new google.maps.LatLng(52.360506, 4.919128);
				var mapOptions = {
					zoom: 13,
					mapTypeId: google.maps.MapTypeId.ROADMAP,
					center: amsterdam
				}
				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

				// Create a renderer for directions and bind it to the map.
				var rendererOptions = {
					map: map,
					draggable: false
				}
				directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);

				google.maps.event.addListener(directionsDisplay, 'directions_changed', function() {
					computeTotalDistance(directionsDisplay.directions);
				});

				google.maps.event.addListener(map, 'click', function(event) {
					addWaypoint(event.latLng);
				});

				calcRoute();
			}

			function calcRoute() {

				// First, clear out any existing markerArray
				// from previous calculations.
				for (i = 0; i < markerArray.length; i++) {
					markerArray[i].setMap(null);
				}


				// Retrieve the start and end locations and create
				// a DirectionsRequest using DRIVING directions.
				var start = document.getElementById("start").value;
				var end = document.getElementById("end").value;
				var request = {
					origin: start,
					waypoints: waypoints,
					optimizeWaypoints: true,
					destination: end,
					travelMode: google.maps.TravelMode.DRIVING
				};

				// Route the directions and pass the result to a
				// function to create markers for each step.
				directionsService.route(request, function(result, status) {
					if (status == google.maps.DirectionsStatus.OK) {
						var warnings = document.getElementById("warnings_panel");
						warnings.innerHTML = "" + result.routes[0].warnings + "";
						directionsDisplay.setDirections(result);
						// showSteps(result);
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
					
					total = myroute.legs[1].distance.value;
					total = total / 1000;
					document.getElementById("total").innerHTML = total + " km";
					document.getElementById("hiddenafstand").value = total;
					setRouteinfo();
				}

			function addWaypoint(wplatlong) {
				waypoints = [];
				waypoints.push({location: wplatlong, stopover: true});
				calcRoute();
			}

//function showSteps(directionResult) {
//  // For each step, place a marker, and add the text to the marker's
//  // info window. Also attach the marker to an array so we
//  // can keep track of it and remove it when calculating new
//  // routes.
//  var myRoute = directionResult.routes[0].legs[0];
//
//  for (var i = 0; i < myRoute.steps.length; i++) {
//      var marker = new google.maps.Marker({
//        position: myRoute.steps[i].start_point,
//        map: map
//      });
//      attachInstructionText(marker, myRoute.steps[i].instructions);
//      markerArray[i] = marker;
//	  
//  }
//}



//function attachInstructionText(marker, text) {
//  google.maps.event.addListener(marker, 'click', function() {
//    stepDisplay.setContent(text);
//    stepDisplay.open(map, marker);
//  });
//}

			google.maps.event.addDomListener(window, 'load', initialize);
		</script>
    </head>
    <body onload="initialize();">
        <h1>Hello World!</h1>

		<div>
			<strong>Start: </strong>
			<select id="start">
				<option value="Poseidonsingel 14, almere, Nederland">Penn Station</option>
				<option value="grand central station, new york, ny">Grand Central Station</option>
				<option value="625 8th Avenue New York NY 10018">Port Authority Bus Terminal</option>
				<option value="staten island ferry terminal, new york, ny">Staten Island Ferry Terminal</option>
				<option value="101 E 125th Street, New York, NY">Harlem - 125th St Station</option>
			</select>
			<strong>End: </strong>
			<select id="end" onchange="calcRoute();">
				<option value="Indigohof 9 14, almere, Nederland"">City Hall</option>
				<option value="W 49th St & 5th Ave, New York, NY 10020">Rockefeller Center</option>
				<option value="moma, New York, NY">MOMA</option>
				<option value="350 5th Ave, New York, NY, 10118">Empire State Building</option>
				<option value="253 West 125th Street, New York, NY">Apollo Theatre</option>
				<option value="1 Wall St, New York, NY">Wall St</option>
			</select>
			<input id="waypoints"></input>
		</div>
		<div id="warnings_panel" style="width:100%;height:10%;text-align:center">
			<b>Walking directions are in beta. Use caution – This route may be missing sidewalks or pedestrian paths.</b>
		</div>

		<div id="map-canvas" style="width:800px; height: 500px;"></div>
		<div id="total" name="total"></div>
	</body>
</html>
