package com.bertazoli.charity

import grails.transaction.Transactional
import com.bertazoli.charity.enums.*
import org.joda.time.DateTime

@Transactional
class DrawService {

    def getCurrentDraw() {
		def currentDate = new Date();
		def currentDraw = Draw.findByStartDateLessThanEqualsAndEndDateGreaterThanEqualsAndStatusAndActive(currentDate, currentDate, DrawStatus.CURRENT, true)
		currentDraw
    }

    def createCurrentDraw() {
        if (!getCurrentDraw()) {
            Date today = new Date()
            Calendar cal = GregorianCalendar.getInstance(TimeZone.getTimeZone("Canada/Eastern"))
            cal.setTime(today)

            cal.set(Calendar.DATE, 1)
            cal.set(Calendar.HOUR, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            Date startDate = cal.getTime()

            cal.set(Calendar.HOUR, 23)
            cal.set(Calendar.MINUTE, 59)
            cal.set(Calendar.SECOND, 59)
            cal.set(Calendar.MILLISECOND, 999)
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
            Date endDate = cal.getTime()

            Draw draw = new Draw(startDate: startDate, endDate: endDate, status: DrawStatus.CURRENT, active: true)
            draw.save(flush: true)
            println("Draw created")
        } else {
            println("Draw already exists")
        }
    }
}
