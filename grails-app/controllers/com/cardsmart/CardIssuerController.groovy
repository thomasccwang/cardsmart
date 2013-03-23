package com.cardsmart

import org.springframework.dao.DataIntegrityViolationException

class CardIssuerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [cardIssuerInstanceList: CardIssuer.list(params), cardIssuerInstanceTotal: CardIssuer.count()]
    }

    def create() {
        [cardIssuerInstance: new CardIssuer(params)]
    }

    def save() {
        def cardIssuerInstance = new CardIssuer(params)
        if (!cardIssuerInstance.save(flush: true)) {
            render(view: "create", model: [cardIssuerInstance: cardIssuerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), cardIssuerInstance.id])
        redirect(action: "show", id: cardIssuerInstance.id)
    }

    def show(Long id) {
        def cardIssuerInstance = CardIssuer.get(id)
        if (!cardIssuerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "list")
            return
        }

        [cardIssuerInstance: cardIssuerInstance]
    }

    def edit(Long id) {
        def cardIssuerInstance = CardIssuer.get(id)
        if (!cardIssuerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "list")
            return
        }

        [cardIssuerInstance: cardIssuerInstance]
    }

    def update(Long id, Long version) {
        def cardIssuerInstance = CardIssuer.get(id)
        if (!cardIssuerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (cardIssuerInstance.version > version) {
                cardIssuerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cardIssuer.label', default: 'CardIssuer')] as Object[],
                          "Another user has updated this CardIssuer while you were editing")
                render(view: "edit", model: [cardIssuerInstance: cardIssuerInstance])
                return
            }
        }

        cardIssuerInstance.properties = params

        if (!cardIssuerInstance.save(flush: true)) {
            render(view: "edit", model: [cardIssuerInstance: cardIssuerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), cardIssuerInstance.id])
        redirect(action: "show", id: cardIssuerInstance.id)
    }

    def delete(Long id) {
        def cardIssuerInstance = CardIssuer.get(id)
        if (!cardIssuerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "list")
            return
        }

        try {
            cardIssuerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cardIssuer.label', default: 'CardIssuer'), id])
            redirect(action: "show", id: id)
        }
    }
}
