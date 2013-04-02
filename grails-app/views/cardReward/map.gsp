<%@ page import="com.cardsmart.CardReward" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<g:set var="entityName" value="${message(code: 'cardReward.label', default: 'CardReward')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
<g:javascript library='jquery' />
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBGkVdZwX63sL0NGMR1CBf44D7g6DZPUzo&sensor=true">
</script>
<script type="text/javascript">
var gMap;
var gInfowindow;
var gMarkersArray = [];
var gDefaultLocation = new google.maps.LatLng(37.7912814,-122.3986085);
var gInitialLocation;

function initialize() {
	var mapOptions = {
		//center: gStartLocation,
		zoom: 16,
		minZoom: 12,
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		mapTypeControl: true,
		mapTypeControlOptions: {position: google.maps.ControlPosition.TOP_RIGHT},
		zoomControl: true,
		zoomControlOptions: {position: google.maps.ControlPosition.RIGHT_TOP, style:google.maps.ZoomControlStyle.SMALL},
		panControl: false,
		streetViewControl: false
	};
	gMap = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

	google.maps.event.addListener(gMap, 'center_changed', function() {
	    // 2 seconds after the center of the map has changed, re-do search
	    window.setTimeout(function() {
	    	loadCategories();
	    }, 2000);
	  });

	google.maps.event.addListener(gMap, 'zoom_changed', function() {
	    // 2 seconds after the center of the map has changed, re-do search
	    window.setTimeout(function() {
	    	loadCategories();
	    }, 2000);
	  });
	  
	//loadCategories();
	handleUserGeolocation();
} // function - initialize()

function handleUserGeolocation() {
	if (navigator.geolocation)
    {
    	navigator.geolocation.getCurrentPosition(function(position) {
			gInitialLocation = new google.maps.LatLng(position.coords.latitude,position.coords.longitude);
			gMap.setCenter(gInitialLocation);
			loadCategories();
		});
    }
	else {
		alert ("Browser does not support geolocation");
		gInitialLocation = gDefaultLocation;
		gMap.setCenter(gInitialLocation);
		loadCategories();
	}
}

function deleteOverlays() {
	if (gMarkersArray) {
		for (i in gMarkersArray) {
			gMarkersArray[i].setMap(null);
		}
		gMarkersArray.length = 0;
	}
}

function loadCategories() {
	var url = "${createLink(action:'jsonlist')}";
	jQuery.getJSON(url, function(data){
		loadMatchingPlaces(data.categories)
	});
}

function getMapBounds() {
	var bounds = gMap.getBounds();
	var nelatlng = bounds.getNorthEast().toUrlValue();
	var swlatlng = bounds.getSouthWest().toUrlValue();
	return(swlatlng + "|" + nelatlng);
}

function loadMatchingPlaces(categories) {
	var url = "${createLink(controller:'yelp', action:'search')}?";
	url += "category_filter="+categories+"&bounds="+getMapBounds()+"&limit=10";
	jQuery.getJSON(url, function(data){
		var sidebar = document.getElementById('sidebar');
		sidebar.innerHTML = '';
		deleteOverlays();
		for (var i = 0; i < data.businesses.length; i++) {
			//alert(data.businesses[i].name);	
			var latlng = new google.maps.LatLng(data.businesses[i].lat,data.businesses[i].lng);
			var storename = data.businesses[i].name;
			var address = data.businesses[i].address;
			var marker = createStoreMarker(latlng,storename,address);
			var sidebarentry = createSidebarEntry(marker,storename,address);
			sidebar.appendChild(sidebarentry);	
		}
	});
}

function createStoreMarker(latlng, storename, address) {
	var html = '';
	html = '<b>' + storename + '</b><br/>' + address;
	var marker = new google.maps.Marker({position: latlng, map: gMap});
	gMarkersArray.push(marker);

	google.maps.event.addListener(marker, "click", function() {
		if (gInfowindow) gInfowindow.close();
		gInfowindow = new google.maps.InfoWindow({content: html});
		gInfowindow.open(gMap, marker);
	});	
	return marker;
} 

function createSidebarEntry(marker, storename, address) {
	var div = document.createElement('div');
	var html = '<b>' + storename + '</b> <br/>' + address;
	div.innerHTML = html;
	div.style.cursor = 'pointer';
	div.style.marginBottom = '5px'; 
	google.maps.event.addDomListener(div, 'click', function() {
		google.maps.event.trigger(marker, 'click');
	});
	google.maps.event.addDomListener(div, 'mouseover', function() {
		div.style.backgroundColor = '#eee';
	});
	google.maps.event.addDomListener(div, 'mouseout', function() {
		div.style.backgroundColor = '#fff';
	});
	return div;
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>
<body>
<a href="#show-cardReward" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
	</ul>
</div>
<div id="sidebar" style="width: 300px; height: 640px; float:left;"></div>
<div id="map-canvas" style="width: 640px; height: 640px; float:right;"></div>

</body>
</html>