package com.cardsmart

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.*

class CardRewardController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cardRewardInstanceList: CardReward.list(params), cardRewardInstanceTotal: CardReward.count()]
    }

    def create() {
        [cardRewardInstance: new CardReward(params)]
    }

    def save() {
        def cardRewardInstance = new CardReward(params)
        if (!cardRewardInstance.save(flush: true)) {
            render(view: "create", model: [cardRewardInstance: cardRewardInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cardReward.label', default: 'CardReward'), cardRewardInstance.id])
        redirect(action: "show", id: cardRewardInstance.id)
    }

    def show(Long id) {
        def cardRewardInstance = CardReward.get(id)
        if (!cardRewardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "list")
            return
        }

        [cardRewardInstance: cardRewardInstance]
    }

    def edit(Long id) {
        def cardRewardInstance = CardReward.get(id)
        if (!cardRewardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "list")
            return
        }

        [cardRewardInstance: cardRewardInstance]
    }

    def update(Long id, Long version) {
        def cardRewardInstance = CardReward.get(id)
        if (!cardRewardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cardRewardInstance.version > version) {
                cardRewardInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cardReward.label', default: 'CardReward')] as Object[],
                          "Another user has updated this CardReward while you were editing")
                render(view: "edit", model: [cardRewardInstance: cardRewardInstance])
                return
            }
        }

        cardRewardInstance.properties = params

        if (!cardRewardInstance.save(flush: true)) {
            render(view: "edit", model: [cardRewardInstance: cardRewardInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cardReward.label', default: 'CardReward'), cardRewardInstance.id])
        redirect(action: "show", id: cardRewardInstance.id)
    }

    def delete(Long id) {
        def cardRewardInstance = CardReward.get(id)
        if (!cardRewardInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "list")
            return
        }

        try {
            cardRewardInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cardReward.label', default: 'CardReward'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def jsonlist() {
		def results = [:]
		def cardslist = []
		def catnameslist = []
		def yelpcategorieslist = []
		String catnamesstr = ""
		Card.list().each { it ->
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
		results.cards = cardslist
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
	
	def map() {
	}
}
