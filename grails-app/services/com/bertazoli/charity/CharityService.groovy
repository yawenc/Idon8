package com.bertazoli.charity

import grails.transaction.Transactional

@Transactional
class CharityService {
	def drawService

    def getSelectedCharities(Draw draw) {
		def selectedCharities;
		
		// get 10 random charities that have been selected
		def currentDraw = drawService.getCurrentDraw() 
		def donations = Donation.findAllByDrawAndCompleted(currentDraw, true, [max: 10])
		def Set<Integer> charityIds = new HashSet<Integer>();
		for (donation in donations) {
			charityIds.add(donation.charity.id)
		}
		selectedCharities = Charity.getAll(charityIds)
		return selectedCharities
    }
	
	def getNotSelectedCharities(Integer max) {
		def selectedCharities;
		selectedCharities = Charity.executeQuery("from Charity where id not in (SELECT charity from Donation where completed = true) and active = true order by rand()", [max: max ?: 10])
		return selectedCharities
	}
	
	def autoCompleteList(params) {
		def query = {
			or {
				ilike("name", "%"+params.term+"%")
				ilike("registrationNumber", "%"+params.term+"%")
			}
			and {
				eq("active", true)
			}
            maxResults(20)
		}
		def clist = Charity.createCriteria().list(query)
		def charityList = []
		clist.each {
			def charityMap = [:]
			charityMap.put("id", it.id)
			charityMap.put("name", it.name)
			charityMap.put("value", it.name)
			charityList.add(charityMap)
		}
		
		return charityList
	}
}
