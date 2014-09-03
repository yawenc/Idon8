package com.bertazoli.charity

import com.bertazoli.charity.auth.User
import com.bertazoli.charity.enums.FundRaisingStatus
import com.bertazoli.charity.util.DateUtil
import grails.plugin.springsecurity.annotation.Secured
import liquibase.util.MD5Util
import org.springframework.web.multipart.MultipartFile
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType

import java.nio.file.Files

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(["ROLE_USER"])
class FundRaisingController {
    def fileUploadService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
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

        setDefaultValues(fundRaisingInstance)

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

    def setDefaultValues(FundRaising fundRaisingInstance) {
        User user = User.findByUsername(springSecurityService.principal.username)
        fundRaisingInstance.user = user
        fundRaisingInstance.active = true
        fundRaisingInstance.status = FundRaisingStatus.CURRENT

        if (fundRaisingInstance.startDate) {
            fundRaisingInstance.startDate = DateUtil.toBeginingOfTheDay(fundRaisingInstance.startDate)
        }
        if (fundRaisingInstance.endDate) {
            fundRaisingInstance.endDate = DateUtil.toEndOfTheDay(fundRaisingInstance.endDate)
        }

        if (params.image) {
            MultipartFile file = params.image;
            if (!file.isEmpty()) {
                def validContents = ['image/png', 'image/jpeg', 'image/gif']
                if (!validContents.contains(file.getContentType())) {
                    fundRaisingInstance.clearErrors()
                    fundRaisingInstance.validate()
                    flash.message = message(code: 'fundRaising.invalidImageType.message')
                    render(view: 'create', model: [fundRaisingInstance:fundRaisingInstance])
                    return
                }
                fundRaisingInstance.fileName = fileUploadService.saveFile(file)
            }
        }
    }

    def getImage() {
        File image = fileUploadService.getImage(params.image)
        if (image) {
            response.contentType = Files.probeContentType(image.toPath())
            OutputStream out = response.outputStream

            byte[] buf = new byte[8192];
            InputStream is = new FileInputStream(image)
            int c=0

            while ((c=is.read(buf, 0, buf.length)) > 0) {
                out.write(buf, 0, c)
                out.flush()
            }

            out.close()
            is.close()
        } else {
            response.sendError(404)
        }
    }

    @Transactional
    @Secured(["ROLE_ADMIN"])
    def update(FundRaising fundRaisingInstance) {

        setDefaultValues(fundRaisingInstance)

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
