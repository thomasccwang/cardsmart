package com.cardsmart

class YelpController {
	
	def yelpSearchService
	
	def grailsApplication
	
    def index() {
		render "default page"
	}
	
	def search() {
		def result = yelpSearchService.search(params)
		render result
	}
}
