package com.bertazoli.charity.auth

import com.bertazoli.charity.user.UserDetails
import grails.plugin.springsecurity.annotation.Secured;
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured(["ROLE_ADMIN"])
class UserController extends grails.plugin.springsecurity.ui.UserController {
    def springSecurityService
    def saltSource

	@Override
	def update() {
		String passwordFieldName = SpringSecurityUtils.securityConfig.userLookup.passwordPropertyName

		def user = findById()
		if (!user) return
		if (!versionCheck('user.label', 'User', user, [user: user])) {
			return
		}

		def oldPassword = user."$passwordFieldName"
		user.properties = params
		if (params.password && !params.password.equals(oldPassword)) {
			String salt = saltSource instanceof NullSaltSource ? null : params.username
			user."$passwordFieldName" = springSecurityUiService.encodePassword(params.password, salt)
		}

		if (!user.userDetails) {
			user.userDetails = new UserDetails()
		}
		
		if (params.firstname) {
			user.userDetails.firstName = params.firstname;
		}
		
		if (params.lastname) {
			user.userDetails.lastName = params.lastname;
		}
		
		if (params.dateofbirth) {
			user.userDetails.dateOfBirth = params.dateofbirth
		}

		if (!user.save(flush: true) && !user.userDetails.save(flush: true)) {
			render view: 'edit', model: buildUserModel(user)
			return
		}

		String usernameFieldName = SpringSecurityUtils.securityConfig.userLookup.usernamePropertyName

		lookupUserRoleClass().removeAll user
		addRoles user
		userCache.removeUserFromCache user[usernameFieldName]
		flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])}"
		redirect action: 'edit', id: user.id
	}
}
