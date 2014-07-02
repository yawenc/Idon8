package com.bertazoli.charity



import static org.springframework.http.HttpStatus.*
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType;
import com.bertazoli.charity.auth.User
import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_USER"])
class DonationController {
	static defaultAction = "index"
	
	def charityService
	def drawService
	def springSecurityService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		def charity
		if (params?.id) {
			charity = Charity.get(params.id)
		}

        respond Donation.list(params), model:[charity: charity]
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
		
		donationInstance.completed = false
		donationInstance.donationDate = new Date()
		donationInstance.draw = drawService.getCurrentDraw()
		donationInstance.feeAmountCurrency = CurrencyCodeType.CAD
		donationInstance.feeAmountValue = -1;
		donationInstance.grossAmountCurrency = CurrencyCodeType.CAD
		donationInstance.paymentCode = PaymentCodeType.NONE
		donationInstance.paymentStatusCode = PaymentStatusCodeType.NONE
		donationInstance.paypalToken = "NOT COMPLETED"
		donationInstance.transaction = "NOT COMPLETED"
		donationInstance.paymentStatus = "NOT COMPLETED"
		User user = User.findByUsername(springSecurityService.principal.username)
		donationInstance.user = user;

		donationInstance.validate();
		
        if (donationInstance.hasErrors()) {
            respond donationInstance.errors, view:'index'
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
