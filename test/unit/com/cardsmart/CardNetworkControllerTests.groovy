package com.cardsmart



import org.junit.*
import grails.test.mixin.*

@TestFor(CardNetworkController)
@Mock(CardNetwork)
class CardNetworkControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cardNetwork/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cardNetworkInstanceList.size() == 0
        assert model.cardNetworkInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cardNetworkInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cardNetworkInstance != null
        assert view == '/cardNetwork/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cardNetwork/show/1'
        assert controller.flash.message != null
        assert CardNetwork.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cardNetwork/list'

        populateValidParams(params)
        def cardNetwork = new CardNetwork(params)

        assert cardNetwork.save() != null

        params.id = cardNetwork.id

        def model = controller.show()

        assert model.cardNetworkInstance == cardNetwork
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cardNetwork/list'

        populateValidParams(params)
        def cardNetwork = new CardNetwork(params)

        assert cardNetwork.save() != null

        params.id = cardNetwork.id

        def model = controller.edit()

        assert model.cardNetworkInstance == cardNetwork
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cardNetwork/list'

        response.reset()

        populateValidParams(params)
        def cardNetwork = new CardNetwork(params)

        assert cardNetwork.save() != null

        // test invalid parameters in update
        params.id = cardNetwork.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cardNetwork/edit"
        assert model.cardNetworkInstance != null

        cardNetwork.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cardNetwork/show/$cardNetwork.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cardNetwork.clearErrors()

        populateValidParams(params)
        params.id = cardNetwork.id
        params.version = -1
        controller.update()

        assert view == "/cardNetwork/edit"
        assert model.cardNetworkInstance != null
        assert model.cardNetworkInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cardNetwork/list'

        response.reset()

        populateValidParams(params)
        def cardNetwork = new CardNetwork(params)

        assert cardNetwork.save() != null
        assert CardNetwork.count() == 1

        params.id = cardNetwork.id

        controller.delete()

        assert CardNetwork.count() == 0
        assert CardNetwork.get(cardNetwork.id) == null
        assert response.redirectedUrl == '/cardNetwork/list'
    }
}
