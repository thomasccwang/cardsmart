package com.cardsmart

import org.springframework.dao.DataIntegrityViolationException

class RewardCategoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rewardCategoryInstanceList: RewardCategory.list(params), rewardCategoryInstanceTotal: RewardCategory.count()]
    }

    def create() {
        [rewardCategoryInstance: new RewardCategory(params)]
    }

    def save() {
        def rewardCategoryInstance = new RewardCategory(params)
        if (!rewardCategoryInstance.save(flush: true)) {
            render(view: "create", model: [rewardCategoryInstance: rewardCategoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), rewardCategoryInstance.id])
        redirect(action: "show", id: rewardCategoryInstance.id)
    }

    def show(Long id) {
        def rewardCategoryInstance = RewardCategory.get(id)
        if (!rewardCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "list")
            return
        }

        [rewardCategoryInstance: rewardCategoryInstance]
    }

    def edit(Long id) {
        def rewardCategoryInstance = RewardCategory.get(id)
        if (!rewardCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "list")
            return
        }

        [rewardCategoryInstance: rewardCategoryInstance]
    }

    def update(Long id, Long version) {
        def rewardCategoryInstance = RewardCategory.get(id)
        if (!rewardCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rewardCategoryInstance.version > version) {
                rewardCategoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rewardCategory.label', default: 'RewardCategory')] as Object[],
                          "Another user has updated this RewardCategory while you were editing")
                render(view: "edit", model: [rewardCategoryInstance: rewardCategoryInstance])
                return
            }
        }

        rewardCategoryInstance.properties = params

        if (!rewardCategoryInstance.save(flush: true)) {
            render(view: "edit", model: [rewardCategoryInstance: rewardCategoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), rewardCategoryInstance.id])
        redirect(action: "show", id: rewardCategoryInstance.id)
    }

    def delete(Long id) {
        def rewardCategoryInstance = RewardCategory.get(id)
        if (!rewardCategoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "list")
            return
        }

        try {
            rewardCategoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rewardCategory.label', default: 'RewardCategory'), id])
            redirect(action: "show", id: id)
        }
    }
}
