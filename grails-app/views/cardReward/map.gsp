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
	handleUserGeolocation();

	var reloadLink = document.getElementById('reload-link');
	reloadLink.onclick = loadCategories;
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
		loadMatchingPlaces(data.rewards, data.categories, data.yelpcategories)
	});
	return false;
}

function getMapBounds() {
	var bounds = gMap.getBounds();
	var nelatlng = bounds.getNorthEast().toUrlValue();
	var swlatlng = bounds.getSouthWest().toUrlValue();
	return(swlatlng + "|" + nelatlng);
}

function getRewardsForCategory(rewards, reward_cards, category_id, yelpcategories) {
	// match the store's category to rewards category
	for (var j = 0; j<rewards.length; j++) {
		if (rewards[j].category == category_id) {
			// todo : only add card reward if not already in list..
			//alert(rewards[j].card);
			var added = false;
			for (var k = 0; k < reward_cards.length; k++) {
				if ( (rewards[j].card == reward_cards[k].card) && 
					 (rewards[j].description == reward_cards[k].description)) {
					 //alert(rewards[j].card + ' already in list');
					 added = true;
				}
				//alert(reward_cards[k].card);
			}
			if (!added) reward_cards.push(rewards[j]);
			return;
		}
	}
	// match the store's category parent to rewards category			
	for (var i = 0; i < yelpcategories.length; i++) {
		for (var key in yelpcategories[i]) {
			if (category_id == key) {
				if (yelpcategories[i][key] == 0) { // top level category
					return;
				}
				else { // has parent category
					for (var parentkey in yelpcategories[yelpcategories[i][key]-1]) {
						return getRewardsForCategory(rewards, reward_cards, parentkey, yelpcategories);
					}					
				}
			}
		}
	}
}

function loadMatchingPlaces(rewards, categories, yelpcategories) {
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
			var categories_str = '';
			var categories_arr = [];
			var reward_cards = [];// full of Reward maps
			for (var j = 0; j < data.businesses[i].categories.length; j++) {
				for (var key in data.businesses[i].categories[j]) {
					categories_arr.push(key); // the long Yelp category name
					getRewardsForCategory(rewards, reward_cards, data.businesses[i].categories[j][key], yelpcategories)
					//categories_arr.push(data.businesses[i].categories[j][key]); // the short Yelp category identifier
				}
			}
			categories_str = categories_arr.join(', ');
					
			var marker = createStoreMarker(latlng,storename,address,categories_str,reward_cards);
			var sidebarentry = createSidebarEntry(marker,storename,address,categories_str);
			sidebar.appendChild(sidebarentry);	
		}
	});
}

function createStoreMarker(latlng, storename, address, storecategories, reward_cards) {
	var html = '';
	html = '<b>' + storename + '</b><br/>Category: ' + storecategories + '<br/>' + address + '<br/>';
	for (var i=0; i<reward_cards.length; i++) {
		html += '<b>' + reward_cards[i].card + '</b><br/>'+ reward_cards[i].description + '<br/>';
	}
	var marker = new google.maps.Marker({position: latlng, map: gMap});
	gMarkersArray.push(marker);

	google.maps.event.addListener(marker, "click", function() {
		if (gInfowindow) gInfowindow.close();
		gInfowindow = new google.maps.InfoWindow({content: html});
		gInfowindow.open(gMap, marker);
	});	
	return marker;
} 

function createSidebarEntry(marker, storename, address, storecategories) {
	var div = document.createElement('div');
	var html = '<b>' + storename + '</b><br/>Category: ' + storecategories + '<br/>' + address;
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
		<li><a href="${createLink(action: 'map')}" id="reload-link">Update Map</a></li>
	</ul>
</div>
<div id="sidebar" style="width: 300px; height: 640px; float:left;"></div>
<div id="map-canvas" style="width: 640px; height: 640px; float:right;"></div>

</body>
</html>
