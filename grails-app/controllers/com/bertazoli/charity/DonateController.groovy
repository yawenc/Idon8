package com.bertazoli.charity

import grails.plugin.springsecurity.annotation.Secured;
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class DonateController {
	def charityService
	
	def index() {
		def charity
		if (params?.id) {
			charity = Charity.get(params.id)
		}
		
		[charity: charity]
	}
	
	def save(Donation donationInstance) {
		if (donationInstance == null) {
			notFound()
			return
		}
		
		if (donationInstance.hasErrors()) {
			respond donationInstance.errors, view:'index', controller: 'donate'
			return
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
