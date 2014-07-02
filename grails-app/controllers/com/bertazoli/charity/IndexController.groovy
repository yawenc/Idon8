package com.bertazoli.charity

import com.bertazoli.charity.enums.CharityStatus;
import org.compass.core.engine.SearchEngineQueryParseException
import com.bertazoli.charity.enums.DrawStatus;

import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class IndexController {
	def charityService
	def searchableService

	def index() {
		def selectedCharities = charityService.getSelectedCharities()
		def notSelectedCharities = charityService.getNotSelectedCharities()
		def searchResult
		
		if (params.q?.trim()) {
			try {
				searchResult = searchableService.search("+active:true +"+params.q, params)
			} catch (SearchEngineQueryParseException ex) {
				return [parseException: true]
			}
		}
		
		[selectedCharities: selectedCharities, notSelectedCharities: notSelectedCharities, searchResult: searchResult]
	}
	
	def search() {
		if (!params.q?.trim()) {
			return [:]
		}
		try {
			return [searchResult: searchableService.search(params.q, params)]
		} catch (SearchEngineQueryParseException ex) {
			return [parseException: true]
		}
	}
}
