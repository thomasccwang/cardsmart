package com.cardsmart

import groovy.json.*
import grails.converters.*

class YelpController {
	
	def yelpSearchService
	
    def index() {
		render "default page"
	}
	
	def search() {
		def result = yelpSearchService.search(params)
		render result
	}
	
	def formattedsearch() {
		def jsonText = yelpSearchService.search(params)
		def json = new JsonSlurper().parseText(jsonText)
		render json as JSON
	}
	
	def shortsearch() {
		def jsonText = yelpSearchService.search(params)
		def json = new JsonSlurper().parseText(jsonText)
		def results = [:]
		json.businesses.each() { it ->
			def props = [:]
			props.name = it.name
			props.lat = it.location.coordinate.latitude
			props.lng = it.location.coordinate.longitude
			results[(it.id)] = props
		}
		render results as JSON
	}
}
