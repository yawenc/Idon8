package com.bertazoli.charity

import grails.transaction.Transactional
import com.bertazoli.charity.enums.*

@Transactional
class DrawService {

    def getCurrentDraw() {
		def currentDate = new Date();
		def currentDraw = Draw.findByStartDateLessThanEqualsAndEndDateGreaterThanEqualsAndStatusAndActive(currentDate, currentDate, DrawStatus.CURRENT, true)
		currentDraw
    }
}
