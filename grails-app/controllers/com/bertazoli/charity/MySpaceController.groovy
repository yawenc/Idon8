package com.bertazoli.charity

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_USER"])
class MySpaceController {

    def index() {}
}
