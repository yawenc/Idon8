package com.bertazoli.charity

import com.bertazoli.charity.enums.CharityStatus;
import org.compass.core.engine.SearchEngineQueryParseException
import com.bertazoli.charity.enums.DrawStatus;

import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('ROLE_ADMIN')
class AdminController {
	def charityService
	def searchableService
	def grailsApplication

	def index() {
		def selectedCharities = charityService.getSelectedCharities()
		def notSelectedCharities = charityService.getNotSelectedCharities()
		def searchResult
		
		[selectedCharities: selectedCharities, notSelectedCharities: notSelectedCharities, searchResult: searchResult]
	}
}
