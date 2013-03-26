package com.cardsmart

import groovy.json.*
import grails.converters.*

class YelpController {

	def yelpSearchService
    
	def index() {
		render "default page"
	}
	
	def shortsearch() {
		def jsonText = yelpSearchService.search(params)
		def json = new JsonSlurper().parseText(jsonText)
		def results = [:]
		def businesslist = []
		json.businesses.each() { it ->
			def business = [:]
			def categorieslist = []
			String address = ""
			business.name = it.name
			business.lat = it.location.coordinate.latitude
			business.lng = it.location.coordinate.longitude
			it.location.address.each() { item ->
				address = address + item + " "
			}
			if (address.length() > 0) address = address.substring(0, address.length()-1)
			business.address = address + ", " + it.location.city + ", " + it.location.state_code
			it.categories.each() { item ->
				categorieslist << item[1]
			}
			business.categories = categorieslist
			businesslist << business
		}
		results.businesses = businesslist
		render results as JSON
	}
}
