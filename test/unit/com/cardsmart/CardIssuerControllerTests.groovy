package com.cardsmart



import org.junit.*
import grails.test.mixin.*

@TestFor(CardIssuerController)
@Mock(CardIssuer)
class CardIssuerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cardIssuer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cardIssuerInstanceList.size() == 0
        assert model.cardIssuerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cardIssuerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cardIssuerInstance != null
        assert view == '/cardIssuer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cardIssuer/show/1'
        assert controller.flash.message != null
        assert CardIssuer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cardIssuer/list'

        populateValidParams(params)
        def cardIssuer = new CardIssuer(params)

        assert cardIssuer.save() != null

        params.id = cardIssuer.id

        def model = controller.show()

        assert model.cardIssuerInstance == cardIssuer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cardIssuer/list'

        populateValidParams(params)
        def cardIssuer = new CardIssuer(params)

        assert cardIssuer.save() != null

        params.id = cardIssuer.id

        def model = controller.edit()

        assert model.cardIssuerInstance == cardIssuer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cardIssuer/list'

        response.reset()

        populateValidParams(params)
        def cardIssuer = new CardIssuer(params)

        assert cardIssuer.save() != null

        // test invalid parameters in update
        params.id = cardIssuer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cardIssuer/edit"
        assert model.cardIssuerInstance != null

        cardIssuer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cardIssuer/show/$cardIssuer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cardIssuer.clearErrors()

        populateValidParams(params)
        params.id = cardIssuer.id
        params.version = -1
        controller.update()

        assert view == "/cardIssuer/edit"
        assert model.cardIssuerInstance != null
        assert model.cardIssuerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cardIssuer/list'

        response.reset()

        populateValidParams(params)
        def cardIssuer = new CardIssuer(params)

        assert cardIssuer.save() != null
        assert CardIssuer.count() == 1

        params.id = cardIssuer.id

        controller.delete()

        assert CardIssuer.count() == 0
        assert CardIssuer.get(cardIssuer.id) == null
        assert response.redirectedUrl == '/cardIssuer/list'
    }
}
