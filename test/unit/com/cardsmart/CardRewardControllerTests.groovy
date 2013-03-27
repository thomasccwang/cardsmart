package com.cardsmart



import org.junit.*
import grails.test.mixin.*

@TestFor(CardRewardController)
@Mock(CardReward)
class CardRewardControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cardReward/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cardRewardInstanceList.size() == 0
        assert model.cardRewardInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cardRewardInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cardRewardInstance != null
        assert view == '/cardReward/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cardReward/show/1'
        assert controller.flash.message != null
        assert CardReward.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cardReward/list'

        populateValidParams(params)
        def cardReward = new CardReward(params)

        assert cardReward.save() != null

        params.id = cardReward.id

        def model = controller.show()

        assert model.cardRewardInstance == cardReward
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cardReward/list'

        populateValidParams(params)
        def cardReward = new CardReward(params)

        assert cardReward.save() != null

        params.id = cardReward.id

        def model = controller.edit()

        assert model.cardRewardInstance == cardReward
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cardReward/list'

        response.reset()

        populateValidParams(params)
        def cardReward = new CardReward(params)

        assert cardReward.save() != null

        // test invalid parameters in update
        params.id = cardReward.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cardReward/edit"
        assert model.cardRewardInstance != null

        cardReward.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cardReward/show/$cardReward.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cardReward.clearErrors()

        populateValidParams(params)
        params.id = cardReward.id
        params.version = -1
        controller.update()

        assert view == "/cardReward/edit"
        assert model.cardRewardInstance != null
        assert model.cardRewardInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cardReward/list'

        response.reset()

        populateValidParams(params)
        def cardReward = new CardReward(params)

        assert cardReward.save() != null
        assert CardReward.count() == 1

        params.id = cardReward.id

        controller.delete()

        assert CardReward.count() == 0
        assert CardReward.get(cardReward.id) == null
        assert response.redirectedUrl == '/cardReward/list'
    }
}
