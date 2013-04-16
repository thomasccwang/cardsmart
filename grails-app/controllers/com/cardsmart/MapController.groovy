package com.cardsmart

import grails.converters.*

class MapController {

    def index() { }
	
	def jsonlist() {
		def results = [:]
		def rewardslist = []
		def catnameslist = []
		def yelpcategorieslist = []
		String catnamesstr = ""
		CardReward.list().each { it ->
			def reward = [:]
			reward.card = it.card.toString()
			reward.description = it.description
			reward.category = it.category.name
			rewardslist << reward
			if (catnameslist.find{c -> c == it.category.name} == null) catnameslist << it.category.name
		}
		results.rewards = rewardslist
		/*Card.list().each { it ->
			def jsoncard = [:]
			jsoncard.name = it.toString()
			def rewardslist = []
			it.rewards.each { item ->
				def jsonreward = [:]
				jsonreward.description = item.description
				jsonreward.category = item.category.name
				rewardslist << jsonreward
				if (catnameslist.find{c -> c == item.category.name } == null) catnameslist << item.category.name
			}
			jsoncard.rewards = rewardslist
			cardslist << jsoncard
		}
		results.cards = cardslist*/
		catnameslist.each { it ->
			catnamesstr += it
			if (it != catnameslist.last()) catnamesstr += ","
		}
		results.categories = catnamesstr
		
		YelpCategory.list().each { it ->
			def c = [:]
			c[it.name] = it.parent ? it.parent.id : 0
			yelpcategorieslist << c
		}
		results.yelpcategories = yelpcategorieslist
		
		render results as JSON
	}
}
