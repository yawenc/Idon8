package com.bertazoli.charity.auth

import grails.plugin.springsecurity.annotation.Secured;

@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
}
