package com.cardsmart

import org.springframework.dao.DataIntegrityViolationException

class CardNetworkController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cardNetworkInstanceList: CardNetwork.list(params), cardNetworkInstanceTotal: CardNetwork.count()]
    }

    def create() {
        [cardNetworkInstance: new CardNetwork(params)]
    }

    def save() {
        def cardNetworkInstance = new CardNetwork(params)
        if (!cardNetworkInstance.save(flush: true)) {
            render(view: "create", model: [cardNetworkInstance: cardNetworkInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), cardNetworkInstance.id])
        redirect(action: "show", id: cardNetworkInstance.id)
    }

    def show(Long id) {
        def cardNetworkInstance = CardNetwork.get(id)
        if (!cardNetworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "list")
            return
        }

        [cardNetworkInstance: cardNetworkInstance]
    }

    def edit(Long id) {
        def cardNetworkInstance = CardNetwork.get(id)
        if (!cardNetworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "list")
            return
        }

        [cardNetworkInstance: cardNetworkInstance]
    }

    def update(Long id, Long version) {
        def cardNetworkInstance = CardNetwork.get(id)
        if (!cardNetworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cardNetworkInstance.version > version) {
                cardNetworkInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cardNetwork.label', default: 'CardNetwork')] as Object[],
                          "Another user has updated this CardNetwork while you were editing")
                render(view: "edit", model: [cardNetworkInstance: cardNetworkInstance])
                return
            }
        }

        cardNetworkInstance.properties = params

        if (!cardNetworkInstance.save(flush: true)) {
            render(view: "edit", model: [cardNetworkInstance: cardNetworkInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), cardNetworkInstance.id])
        redirect(action: "show", id: cardNetworkInstance.id)
    }

    def delete(Long id) {
        def cardNetworkInstance = CardNetwork.get(id)
        if (!cardNetworkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "list")
            return
        }

        try {
            cardNetworkInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cardNetwork.label', default: 'CardNetwork'), id])
            redirect(action: "show", id: id)
        }
    }
}
