package com.bertazoli.charity

import com.bertazoli.charity.enums.CharityStatus;
import com.bertazoli.charity.enums.DrawStatus;

import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class IndexController {

	def index() {
		def selectedCharities;
		def notSelectedCharities;
		
		// get 10 random charities that have been selected
		def currentDate = new Date();
		def currentDraw = Draw.findByStartDateLessThanEqualsAndEndDateGreaterThanEqualsAndStatusAndActive(currentDate, currentDate, DrawStatus.CURRENT, true)
		def donations = Donation.findAllByDraw(currentDraw, [max: 10])
		def charityIds = new ArrayList<Integer>();
		for (donation in donations) {
			charityIds.add(donation.charity.id)
		}
		selectedCharities = Charity.getAll(charityIds)
		
		// get 10 random charities that have not been selected
//		notSelectedCharities = Charity.findAll()
	}
}
