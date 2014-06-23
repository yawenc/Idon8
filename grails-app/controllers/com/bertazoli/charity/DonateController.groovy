package com.bertazoli.charity

import grails.plugin.springsecurity.annotation.Secured;
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class DonateController {

	def index() {
		render view: 'index'
	}
}
