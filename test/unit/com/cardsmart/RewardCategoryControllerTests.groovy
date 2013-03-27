package com.cardsmart



import org.junit.*
import grails.test.mixin.*

@TestFor(RewardCategoryController)
@Mock(RewardCategory)
class RewardCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rewardCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rewardCategoryInstanceList.size() == 0
        assert model.rewardCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rewardCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rewardCategoryInstance != null
        assert view == '/rewardCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rewardCategory/show/1'
        assert controller.flash.message != null
        assert RewardCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardCategory/list'

        populateValidParams(params)
        def rewardCategory = new RewardCategory(params)

        assert rewardCategory.save() != null

        params.id = rewardCategory.id

        def model = controller.show()

        assert model.rewardCategoryInstance == rewardCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardCategory/list'

        populateValidParams(params)
        def rewardCategory = new RewardCategory(params)

        assert rewardCategory.save() != null

        params.id = rewardCategory.id

        def model = controller.edit()

        assert model.rewardCategoryInstance == rewardCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardCategory/list'

        response.reset()

        populateValidParams(params)
        def rewardCategory = new RewardCategory(params)

        assert rewardCategory.save() != null

        // test invalid parameters in update
        params.id = rewardCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rewardCategory/edit"
        assert model.rewardCategoryInstance != null

        rewardCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rewardCategory/show/$rewardCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rewardCategory.clearErrors()

        populateValidParams(params)
        params.id = rewardCategory.id
        params.version = -1
        controller.update()

        assert view == "/rewardCategory/edit"
        assert model.rewardCategoryInstance != null
        assert model.rewardCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rewardCategory/list'

        response.reset()

        populateValidParams(params)
        def rewardCategory = new RewardCategory(params)

        assert rewardCategory.save() != null
        assert RewardCategory.count() == 1

        params.id = rewardCategory.id

        controller.delete()

        assert RewardCategory.count() == 0
        assert RewardCategory.get(rewardCategory.id) == null
        assert response.redirectedUrl == '/rewardCategory/list'
    }
}
