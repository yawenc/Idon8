package com.bertazoli.charity



import static org.springframework.http.HttpStatus.*

import com.bertazoli.charity.enums.CharityStatus;

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class CharityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	def charityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Charity.list(params), model:[charityInstanceCount: Charity.count()]
    }

    def show(Charity charityInstance) {
        respond charityInstance
    }

    def create() {
        respond new Charity(params)
    }

    @Transactional
    def save(Charity charityInstance) {
        if (charityInstance == null) {
            notFound()
            return
        }

        if (charityInstance.hasErrors()) {
            respond charityInstance.errors, view:'create'
            return
        }

        charityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'charity.label', default: 'Charity'), charityInstance.id])
                redirect charityInstance
            }
            '*' { respond charityInstance, [status: CREATED] }
        }
    }

    def edit(Charity charityInstance) {
        respond charityInstance
    }

    @Transactional
    def update(Charity charityInstance) {
        if (charityInstance == null) {
            notFound()
            return
        }

        if (charityInstance.hasErrors()) {
            respond charityInstance.errors, view:'edit'
            return
        }

        charityInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Charity.label', default: 'Charity'), charityInstance.id])
                redirect charityInstance
            }
            '*'{ respond charityInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Charity charityInstance) {

        if (charityInstance == null) {
            notFound()
            return
        }

        charityInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Charity.label', default: 'Charity'), charityInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    @Secured('IS_AUTHENTICATED_ANONYMOUSLY')
	def autoCompleteList() {
		render charityService.autoCompleteList(params) as JSON
	}

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'charity.label', default: 'Charity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
