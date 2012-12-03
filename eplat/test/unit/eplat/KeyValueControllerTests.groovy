package eplat



import org.junit.*
import grails.test.mixin.*

@TestFor(KeyValueController)
@Mock(KeyValue)
class KeyValueControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/keyValue/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.keyValueInstanceList.size() == 0
        assert model.keyValueInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.keyValueInstance != null
    }

    void testSave() {
        controller.save()

        assert model.keyValueInstance != null
        assert view == '/keyValue/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/keyValue/show/1'
        assert controller.flash.message != null
        assert KeyValue.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/keyValue/list'

        populateValidParams(params)
        def keyValue = new KeyValue(params)

        assert keyValue.save() != null

        params.id = keyValue.id

        def model = controller.show()

        assert model.keyValueInstance == keyValue
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/keyValue/list'

        populateValidParams(params)
        def keyValue = new KeyValue(params)

        assert keyValue.save() != null

        params.id = keyValue.id

        def model = controller.edit()

        assert model.keyValueInstance == keyValue
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/keyValue/list'

        response.reset()

        populateValidParams(params)
        def keyValue = new KeyValue(params)

        assert keyValue.save() != null

        // test invalid parameters in update
        params.id = keyValue.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/keyValue/edit"
        assert model.keyValueInstance != null

        keyValue.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/keyValue/show/$keyValue.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        keyValue.clearErrors()

        populateValidParams(params)
        params.id = keyValue.id
        params.version = -1
        controller.update()

        assert view == "/keyValue/edit"
        assert model.keyValueInstance != null
        assert model.keyValueInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/keyValue/list'

        response.reset()

        populateValidParams(params)
        def keyValue = new KeyValue(params)

        assert keyValue.save() != null
        assert KeyValue.count() == 1

        params.id = keyValue.id

        controller.delete()

        assert KeyValue.count() == 0
        assert KeyValue.get(keyValue.id) == null
        assert response.redirectedUrl == '/keyValue/list'
    }
}
