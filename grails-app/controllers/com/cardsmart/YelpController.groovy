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
}
