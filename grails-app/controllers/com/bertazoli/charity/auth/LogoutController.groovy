package com.bertazoli.charity.auth
import grails.plugin.springsecurity.annotation.Secured;
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class LogoutController {
	def index = {
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
	}
}
