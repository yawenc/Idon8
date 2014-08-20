package com.bertazoli.charity

import com.bertazoli.charity.auth.User
import com.bertazoli.charity.enums.FundRaisingStatus
import com.bertazoli.charity.util.DateUtil
import grails.plugin.springsecurity.annotation.Secured
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_USER"])
class FundRaisingController {
    def fundRaisingService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def fundRaisingInstanceList = FundRaising.list(params)

        for (FundRaising fundRaising : fundRaisingInstanceList) {
            BigDecimal totalDonated = new BigDecimal(0)
            for (FundRaisingDonation donation : fundRaising.fundRaisingDonation) {
                if (donation.completed && donation.paymentStatusCode == PaymentStatusCodeType.COMPLETED) {
                    totalDonated = totalDonated.add(donation.grossAmountValue)
                }
            }
            fundRaising.totalDonated = totalDonated.multiply(new BigDecimal(0.93))
        }

        respond fundRaisingInstanceList, model:[fundRaisingInstanceCount: FundRaising.count()]
    }

    def show(FundRaising fundRaisingInstance) {
        User user = User.findByUsername(springSecurityService.principal.username)

        if (!fundRaisingInstance || fundRaisingInstance.user.id != user.id) {
            notFound()
            return
        }
        respond fundRaisingInstance
    }

    def create() {
        respond new FundRaising(params)
    }

    @Transactional
    def save(FundRaising fundRaisingInstance) {
        if (fundRaisingInstance == null) {
            notFound()
            return
        }

        User user = User.findByUsername(springSecurityService.principal.username)
        fundRaisingInstance.user = user
        fundRaisingInstance.active = true
        fundRaisingInstance.status = FundRaisingStatus.CURRENT
        fundRaisingInstance.startDate = DateUtil.toBeginingOfTheDay(fundRaisingInstance.startDate)
        fundRaisingInstance.endDate = DateUtil.toEndOfTheDay(fundRaisingInstance.endDate)
        fundRaisingInstance.clearErrors()
        fundRaisingInstance.validate()

        if (fundRaisingInstance.hasErrors()) {
            respond fundRaisingInstance.errors, view:'create'
            return
        }

        fundRaisingInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'fundRaising.label', default: 'FundRaising'), fundRaisingInstance.id])
                redirect fundRaisingInstance
            }
            '*' { respond fundRaisingInstance, [status: CREATED] }
        }
    }

    @Secured(["ROLE_ADMIN"])
    def edit(FundRaising fundRaisingInstance) {
        respond fundRaisingInstance
    }

    @Transactional
    @Secured(["ROLE_ADMIN"])
    def update(FundRaising fundRaisingInstance) {
        def f = request.getFile('image')
        if (f.empty) {
            flash.message = 'file cannot be empty'
            render (view: 'edit')
            return
        }

        f.transferTo(new File('/tmp/upload.jpg'))

        if (fundRaisingInstance == null) {
            notFound()
            return
        }

        User user = User.findByUsername(springSecurityService.principal.username)
        fundRaisingInstance.user = user
        fundRaisingInstance.active = true
        fundRaisingInstance.startDate = DateUtil.toBeginingOfTheDay(fundRaisingInstance.startDate)
        fundRaisingInstance.endDate = DateUtil.toEndOfTheDay(fundRaisingInstance.endDate)
        fundRaisingInstance.clearErrors()
        fundRaisingInstance.validate()

        if (fundRaisingInstance.hasErrors()) {
            respond fundRaisingInstance.errors, view:'edit'
            return
        }

        fundRaisingInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'FundRaising.label', default: 'FundRaising'), fundRaisingInstance.id])
                redirect fundRaisingInstance
            }
            '*'{ respond fundRaisingInstance, [status: OK] }
        }
    }

    @Transactional
    @Secured(["ROLE_ADMIN"])
    def delete(FundRaising fundRaisingInstance) {

        if (fundRaisingInstance == null) {
            notFound()
            return
        }

        fundRaisingInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'FundRaising.label', default: 'FundRaising'), fundRaisingInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'fundRaising.label', default: 'FundRaising'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
