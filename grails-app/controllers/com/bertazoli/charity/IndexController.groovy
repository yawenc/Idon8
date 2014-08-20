package com.bertazoli.charity

import com.bertazoli.charity.enums.CharityStatus
import grails.converters.JSON;
import org.compass.core.engine.SearchEngineQueryParseException
import com.bertazoli.charity.enums.DrawStatus;

import grails.plugin.springsecurity.annotation.Secured;
import grails.transaction.Transactional
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType

@Transactional(readOnly = true)
@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
class IndexController {
	def charityService
    def drawService
    def messageSource

	def index() {
		def selectedCharities = charityService.getSelectedCharities()
		def notSelectedCharities = charityService.getNotSelectedCharities()
        def BigDecimal totalDonated = new BigDecimal(0)
        def total = getTotalDonated()
        if (total) {
            totalDonated = total.get(0)
        }

		["selectedCharities": selectedCharities, "notSelectedCharities": notSelectedCharities, "totalDonated": totalDonated]
	}

    def percentageToKeep() {
        def currentDraw = drawService.getCurrentDraw();
        def result = Donation.executeQuery("SELECT concat(percentageToKeep,'%'), count(*) FROM Donation where draw=? and completed=true group by percentageToKeep", currentDraw)
        def response
        if (result) {
            response = result as JSON;
        } else {
            response = '[["0%"],[0]]'
        }
        render "[$response]"
    }

    def totalDonated() {
        render getTotalDonated() as JSON;
    }

    def List<Donation> getTotalDonated() {
        def currentDraw = drawService.getCurrentDraw()
        Donation.executeQuery("SELECT sum(grossAmountValue)*0.93 FROM Donation where draw=? and completed=true", currentDraw)
    }
}
