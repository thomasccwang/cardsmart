package com.cardsmart

class GeocoderService {

    def geocodeAddress(String addr) {
		def base = "http://maps.googleapis.com/maps/api/geocode/xml?"
		def querystring = []
		querystring << "address=" + URLEncoder.encode(addr,"UTF-8")
		querystring << "sensor=false"
		def url = new URL(base + querystring.join("&"))
		def connection = url.openConnection()
		
		def result = [:]
		if (connection.responseCode == 200)
		{
			def xml = connection.content.text
			def geocode = new XmlSlurper().parseText(xml)
			result.lat = geocode.result.geometry.location.lat as String
			result.lng = geocode.result.geometry.location.lng as String
		}
		else{
			log.error("GeocoderService.geocodeAddress FAILED")
			log.error(url)
			log.error(connection.responseCode)
			log.error(connection.responseMessage)
		}
		return result
    }
}
