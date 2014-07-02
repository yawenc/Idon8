package com.bertazoli.charity.auth

import grails.plugin.springsecurity.annotation.Secured;
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegistrationCode
import com.bertazoli.charity.user.UserDetails
import org.codehaus.groovy.grails.commons.ApplicationHolder as AH
import grails.plugin.springsecurity.SpringSecurityUtils;

@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
	def index = {
		[command: new RegisterCommand()]
	}
	
	def register(RegisterCommand command) {

		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}

		String salt = saltSource instanceof NullSaltSource ? null : command.username
		def userDetails = new UserDetails(firstName: command.firstName, lastName: command.lastName, dateOfBirth: command.dateOfBirth)
		def user = lookupUserClass().newInstance(email: command.email, username: command.username, accountLocked: true, enabled: true, userDetails:userDetails)

		RegistrationCode registrationCode = springSecurityUiService.register(user, command.password, salt)
		if (registrationCode == null || registrationCode.hasErrors()) {
			// null means problem creating the user
			flash.error = message(code: 'spring.security.ui.register.miscError')
			flash.chainedParams = params
			redirect action: 'index'
			return
		}

		String url = generateLink('verifyRegistration', [t: registrationCode.token])

		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.register.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user, url: url])
		}
		mailService.sendMail {
			to command.email
			from conf.ui.register.emailFrom
			subject conf.ui.register.emailSubject
			html body.toString()
		}

		render view: 'index', model: [emailSent: true]
	}
}

class RegisterCommand {
	
	String username
	String email
	String password
	String password2
	String firstName
	String lastName
	Date dateOfBirth

	static constraints = {
		username blank: false, validator: { value, command ->
			if (value) {
				def User = AH.application.getDomainClass(
					SpringSecurityUtils.securityConfig.userLookup.userDomainClassName).clazz
				if (User.findByUsername(value)) {
					return 'registerCommand.username.unique'
				}
			}
		}
		email blank: false, email: true
		password blank: false, minSize: 8, maxSize: 64, validator: RegisterController.passwordValidator
		password2 validator: RegisterController.password2Validator
		firstName blank: false
		lastName blank: false
	}
}