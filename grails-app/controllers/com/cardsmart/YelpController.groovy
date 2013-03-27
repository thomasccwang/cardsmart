package com.cardsmart

import groovy.json.*
import grails.converters.*

class YelpController {

	def yelpSearchService
    
	def index() {
		render "default page"
	}
	
	def search() {
		def results = yelpSearchService.search(params)
		render results as JSON
	}
}
