package com.bertazoli.charity

import com.bertazoli.charity.enums.DrawStatus
import grails.converters.JSON
import org.joda.time.DateTime

import java.text.NumberFormat

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
	def donationService
	
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		def charity
		if (params?.id) {
			charity = Charity.get(params.id)
		}

        respond Donation.list(params), model:[charity: charity]
    }

    @Secured(["ROLE_ADMIN"])
    def show(Donation donationInstance) {
        respond donationInstance
    }

    @Secured(["ROLE_ADMIN"])
    def create() {
        respond new Donation(params)
    }

    def userFundRaising(FundRaising fundRaising, FundRaisingDonation fundRaisingDonation) {
        if (!fundRaising) {
            render "fund raising not found"
            return
        }

        if (!fundRaisingDonation) {
            fundRaisingDonation = new FundRaisingDonation()
        }

        if (!fundRaising.active) {
            render "fund raising is not active"
            return
        }

        DateTime endDate = new DateTime(fundRaising.endDate)
        DateTime now = new DateTime(new Date())
        if (now.isAfter(endDate)) {
            render "this fund raise has reached its end"
            return
        }

        ['fundRaising':fundRaising, 'fundRaisingDonation':fundRaisingDonation]
    }

    @Transactional
    def saveUserFundRaising(FundRaisingDonation fundRaisingDonation) {
        def FundRaising fundRaising
        if (params.fundRaising) {
            try {
                fundRaising = FundRaising.get(Long.parseLong(params.fundRaising))
            } catch (NumberFormatException e) {
                log(e)
            }
        }

        if (!fundRaising) {
            respond fundRaisingDonation.errors, view:'userFundRaising'
            return
        }

        fundRaisingDonation.completed = false
        fundRaisingDonation.donationDate = new Date()
        fundRaisingDonation.feeAmountCurrency = CurrencyCodeType.CAD
        fundRaisingDonation.feeAmountValue = -1;
        fundRaisingDonation.grossAmountCurrency = CurrencyCodeType.CAD
        fundRaisingDonation.paymentCode = PaymentCodeType.NONE
        fundRaisingDonation.paymentStatusCode = PaymentStatusCodeType.NONE
        fundRaisingDonation.paypalToken = "NOT COMPLETED"
        fundRaisingDonation.transaction = "NOT COMPLETED"
        User user = User.findByUsername(springSecurityService.principal.username)
        fundRaisingDonation.user = user
        fundRaisingDonation.fundRaising = fundRaising

        fundRaisingDonation.clearErrors()
        fundRaisingDonation.validate()

        if (fundRaisingDonation.hasErrors()) {
            render(view: 'userFundRaising', model: ['fundRaising':fundRaising, 'fundRaisingDonation':fundRaisingDonation])
            return
        }

        fundRaisingDonation.save flush: true
        def redirectURL = donationService.initializeFundRaisingDonation(['fundRaisingDonation':fundRaisingDonation, 'user':user])
        redirect(url: redirectURL)
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
		User user = User.findByUsername(springSecurityService.principal.username)
		donationInstance.user = user;

		donationInstance.validate();
		
        if (donationInstance.hasErrors()) {
            respond donationInstance.errors, view:'index'
            return
        }

		// save the temporary donation
        donationInstance.save flush:true

		// if passes validation then initializes communication with Paypal
		def redirectURL = donationService.initializeDonation(['donationInstance':donationInstance, 'user':user])
		redirect(url: redirectURL)

		/*
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'donation.label', default: 'Donation'), donationInstance.id])
                redirect donationInstance
            }
            '*' { respond donationInstance, [status: CREATED] }
        }
        */
    }

    def myDonations(Draw draw) {
        String currentDraw = params.currentDraw
        String act = params.act
        Draw temp = Draw.get(currentDraw)

        if (currentDraw && act && temp) {

            if (act.equalsIgnoreCase("previous")) {
                draw = Draw.findByStartDateLessThanAndActiveAndStatus(temp.startDate, Boolean.FALSE, DrawStatus.FINALIZED)
                if (!draw) {
                    draw = temp
                }
            } else {
                draw = Draw.findByStartDateGreaterThanAndActiveAndStatus(temp.endDate, Boolean.FALSE, DrawStatus.FINALIZED)
            }
        }

        if (!draw) {
            draw = drawService.getCurrentDraw()
        }
        def User user = User.findByUsername(springSecurityService.principal.username)
        def myDonations = Donation.findAllByUserAndDraw(user, draw)
        ['myDonations': myDonations, 'draw':draw]
    }

    @Secured(["ROLE_ADMIN"])
    def edit(Donation donationInstance) {
        respond donationInstance
    }

    @Transactional
    @Secured(["ROLE_ADMIN"])
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

    def emailSupport(Donation donation) {
        donationService.sendEmailToSupport(donation)
    }

    @Transactional
    @Secured(["ROLE_ADMIN"])
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
	
	@Transactional
	def doExpressCheckout(params) {
		def redirectURL = donationService.doExpressCheckout(params)
		redirect(url: redirectURL)
	}

    @Transactional
    def doFundRaisingExpressCheckout(params) {
        def redirectURL = donationService.doFundRaisingExpressCheckout(params)
        redirect(url: redirectURL)
    }
}
