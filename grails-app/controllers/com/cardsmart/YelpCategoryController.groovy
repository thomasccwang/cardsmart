package com.cardsmart

import org.springframework.dao.DataIntegrityViolationException

class YelpCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [yelpCategoryInstanceList: YelpCategory.list(params), yelpCategoryInstanceTotal: YelpCategory.count()]
    }

    def create() {
        [yelpCategoryInstance: new YelpCategory(params)]
    }

    def save() {
        def yelpCategoryInstance = new YelpCategory(params)
        if (!yelpCategoryInstance.save(flush: true)) {
            render(view: "create", model: [yelpCategoryInstance: yelpCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), yelpCategoryInstance.id])
        redirect(action: "show", id: yelpCategoryInstance.id)
    }

    def show(Long id) {
        def yelpCategoryInstance = YelpCategory.get(id)
        if (!yelpCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "list")
            return
        }

        [yelpCategoryInstance: yelpCategoryInstance]
    }

    def edit(Long id) {
        def yelpCategoryInstance = YelpCategory.get(id)
        if (!yelpCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "list")
            return
        }

        [yelpCategoryInstance: yelpCategoryInstance]
    }

    def update(Long id, Long version) {
        def yelpCategoryInstance = YelpCategory.get(id)
        if (!yelpCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (yelpCategoryInstance.version > version) {
                yelpCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'yelpCategory.label', default: 'YelpCategory')] as Object[],
                          "Another user has updated this YelpCategory while you were editing")
                render(view: "edit", model: [yelpCategoryInstance: yelpCategoryInstance])
                return
            }
        }

        yelpCategoryInstance.properties = params

        if (!yelpCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [yelpCategoryInstance: yelpCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), yelpCategoryInstance.id])
        redirect(action: "show", id: yelpCategoryInstance.id)
    }

    def delete(Long id) {
        def yelpCategoryInstance = YelpCategory.get(id)
        if (!yelpCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            yelpCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'yelpCategory.label', default: 'YelpCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
