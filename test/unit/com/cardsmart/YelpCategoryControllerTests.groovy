package com.cardsmart



import org.junit.*
import grails.test.mixin.*

@TestFor(YelpCategoryController)
@Mock(YelpCategory)
class YelpCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/yelpCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.yelpCategoryInstanceList.size() == 0
        assert model.yelpCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.yelpCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.yelpCategoryInstance != null
        assert view == '/yelpCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/yelpCategory/show/1'
        assert controller.flash.message != null
        assert YelpCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/yelpCategory/list'

        populateValidParams(params)
        def yelpCategory = new YelpCategory(params)

        assert yelpCategory.save() != null

        params.id = yelpCategory.id

        def model = controller.show()

        assert model.yelpCategoryInstance == yelpCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/yelpCategory/list'

        populateValidParams(params)
        def yelpCategory = new YelpCategory(params)

        assert yelpCategory.save() != null

        params.id = yelpCategory.id

        def model = controller.edit()

        assert model.yelpCategoryInstance == yelpCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/yelpCategory/list'

        response.reset()

        populateValidParams(params)
        def yelpCategory = new YelpCategory(params)

        assert yelpCategory.save() != null

        // test invalid parameters in update
        params.id = yelpCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/yelpCategory/edit"
        assert model.yelpCategoryInstance != null

        yelpCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/yelpCategory/show/$yelpCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        yelpCategory.clearErrors()

        populateValidParams(params)
        params.id = yelpCategory.id
        params.version = -1
        controller.update()

        assert view == "/yelpCategory/edit"
        assert model.yelpCategoryInstance != null
        assert model.yelpCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/yelpCategory/list'

        response.reset()

        populateValidParams(params)
        def yelpCategory = new YelpCategory(params)

        assert yelpCategory.save() != null
        assert YelpCategory.count() == 1

        params.id = yelpCategory.id

        controller.delete()

        assert YelpCategory.count() == 0
        assert YelpCategory.get(yelpCategory.id) == null
        assert response.redirectedUrl == '/yelpCategory/list'
    }
}
