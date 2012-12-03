package eplat

import org.springframework.dao.DataIntegrityViolationException

class KeyValueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [keyValueInstanceList: KeyValue.list(params), keyValueInstanceTotal: KeyValue.count()]
    }

    def create() {
        [keyValueInstance: new KeyValue(params)]
    }

    def save() {
        def keyValueInstance = new KeyValue(params)
        if (!keyValueInstance.save(flush: true)) {
            render(view: "create", model: [keyValueInstance: keyValueInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), keyValueInstance.id])
        redirect(action: "show", id: keyValueInstance.id)
    }

    def show(Long id) {
        def keyValueInstance = KeyValue.get(id)
        if (!keyValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "list")
            return
        }

        [keyValueInstance: keyValueInstance]
    }

    def edit(Long id) {
        def keyValueInstance = KeyValue.get(id)
        if (!keyValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "list")
            return
        }

        [keyValueInstance: keyValueInstance]
    }

    def update(Long id, Long version) {
        def keyValueInstance = KeyValue.get(id)
        if (!keyValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (keyValueInstance.version > version) {
                keyValueInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'keyValue.label', default: 'KeyValue')] as Object[],
                          "Another user has updated this KeyValue while you were editing")
                render(view: "edit", model: [keyValueInstance: keyValueInstance])
                return
            }
        }

        keyValueInstance.properties = params

        if (!keyValueInstance.save(flush: true)) {
            render(view: "edit", model: [keyValueInstance: keyValueInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), keyValueInstance.id])
        redirect(action: "show", id: keyValueInstance.id)
    }

    def delete(Long id) {
        def keyValueInstance = KeyValue.get(id)
        if (!keyValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "list")
            return
        }

        try {
            keyValueInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'keyValue.label', default: 'KeyValue'), id])
            redirect(action: "show", id: id)
        }
    }
}
