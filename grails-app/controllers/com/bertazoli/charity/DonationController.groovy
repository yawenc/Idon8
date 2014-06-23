package com.bertazoli.charity



import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_USER"])
class DonationController {
	static defaultAction = "create"
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Donation.list(params), model:[donationInstanceCount: Donation.count()]
    }

    def show(Donation donationInstance) {
        respond donationInstance
    }

    def create() {
        respond new Donation(params)
    }

    @Transactional
    def save(Donation donationInstance) {
        if (donationInstance == null) {
            notFound()
            return
        }

        if (donationInstance.hasErrors()) {
            respond donationInstance.errors, view:'create'
            return
        }

        donationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'donation.label', default: 'Donation'), donationInstance.id])
                redirect donationInstance
            }
            '*' { respond donationInstance, [status: CREATED] }
        }
    }

    def edit(Donation donationInstance) {
        respond donationInstance
    }

    @Transactional
    def update(Donation donationInstance) {
        if (donationInstance == null) {
            notFound()
            return
        }

        if (donationInstance.hasErrors()) {
            respond donationInstance.errors, view:'edit'
            return
        }

        donationInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Donation.label', default: 'Donation'), donationInstance.id])
                redirect donationInstance
            }
            '*'{ respond donationInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Donation donationInstance) {

        if (donationInstance == null) {
            notFound()
            return
        }

        donationInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Donation.label', default: 'Donation'), donationInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'donation.label', default: 'Donation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
