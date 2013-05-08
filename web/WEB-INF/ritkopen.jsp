<%-- 
    Document   : ritkopen1
    Created on : Apr 29, 2013, 2:31:35 PM
    Author     : jeroen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${currentSessionUser.localeStr}" scope="session" />

<fmt:setBundle basename="ResourceBundles.Dryves" scope="request" var="rb" />
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
				map = new google.maps.Map(document.getElementById("mapcanvas"), mapOptions);

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
						setRouteInfo();
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
				document.getElementById("total").innerHTML = total;
				document.getElementById("hiddenafstand").value = total;
				setRouteinfo();
			}

			function addWaypoint(wplatlong) {
				geocoder = new google.maps.Geocoder();
				waypoints = [];
				waypoints.push({location: wplatlong, stopover: true});

				geocoder.geocode({'latLng': wplatlong}, function(results, status) {
					if (status == google.maps.GeocoderStatus.OK) {
						if (results[0]) {
							document.getElementById("oppikpunt").innerHTML = results[0].formatted_address;
							document.getElementById("pickup").value = results[0].formatted_address;

						}
					}
				});
				calcRoute();
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
				document.getElementById("eindadres").innerHTML = endaddress;

				//zet de waardes voor de invoervelden
				document.getElementById("start").value = startaddress;
				document.getElementById("end").value = endaddress;


				//zet de waardes voor de verborgenvelden (gebruikt door servlet)
				document.getElementById("hiddenstart").value = startaddress;
				document.getElementById("hiddenend").value = endaddress;
				document.getElementById("hiddenwaypoints").value = waypoints;

				var afstand = parseFloat(document.getElementById("total").value);
				var prijs = parseFloat(document.getElementById("prijs").value);

				var kostenberekening = prijs * afstand;
				document.getElementById("kosten").innerHTML = kostenberekening;

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

		<div id="warnings_panel" style="width:100%;height:10%;text-align:center">
			<b>Walking directions are in beta. Use caution – This route may be missing sidewalks or pedestrian paths.</b>
		</div>


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
					<form action="RitKopen" method="post" onsubmit="return isCompleet();">
                                                <br><fmt:message bundle="${rb}" key="ritnummer" /><br/>
						<input id="ritnr" name="ritnr" value="${sessieRit.ritnr}"></input>                                               
                                                <br><fmt:message bundle="${rb}" key="startadres" /><br/> 
						<input type="text" id="start" name="start" disabled="true" style ="width: 350; float: right:" value="${sessieRit.startpunt}"><br />
						<fmt:message bundle="${rb}" key="hierwilikopgehaaldworden" /><br/>
						<input type="text" id="pickup" style="width: 350; float: right:"><br />
						<fmt:message bundle="${rb}" key="eindadres" /> <br/>
						<input type="text" id="end" name="end" disabled="true" style ="width: 350; float: right:" value="${sessieRit.eindpunt}"> <br />	
						<fmt:message bundle="${rb}" key="begindatum" /><br/> <input type="date" id="begindatum" name="begindatum" value="${sessieRit.datum}"> <br/>
						<fmt:message bundle="${rb}" key="tijd" /> <br/> <input type="text" id="tijd" name="tijd"> <br/><br/>
						<fmt:message bundle="${rb}" key="kostendezerit" /><span id="kosten" name="kosten"></span><br/>

						<fmt:message bundle="${rb}" key="prijsperkm" /> <div id ="prijs" name="prijs">0.21</div>
						
						<input type="radio" name="betaalwijze" value ="ideal">iDEAL <br/>
						<input type="radio" name="betaalwijze" value ="creditcard">Credit Card <br/>
						<br/>
						<br/>
						
						
						
						<button action="submit"><strong><fmt:message bundle="${rb}" key="ritkopen" /></strong></button>

					</form>


				</div>


				<div class="mapcontent">
					<div id="mapcanvas"></div>
					<div id ="ritoverzicht"> 
						<table>
							<td><strong><fmt:message bundle="${rb}" key="totaleafstand" /></strong></br>
								<div id="total" name="total"></div> </td> <br/>

							<td><strong><fmt:message bundle="${rb}" key="oppikpunt" /></strong><br/>
								<div id="oppikpunt" name="oppikpunt"></div></td> <br/>
						</table>



					</div> 
				</div> 
			</div>

		</div>
	</body>
</html>
