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
			;
			var directionsService = new google.maps.DirectionsService();
			var directionsRoute = new google.maps.DirectionsRoute();
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

				map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
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
				total = total / 1000.
				document.getElementById("total").innerHTML = total + " km";
			}




        </script>
	</script>

</head>
<body>

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



        </div>

	</div>

</body>
</html>
