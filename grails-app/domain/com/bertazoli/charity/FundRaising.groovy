package com.bertazoli.charity

import com.bertazoli.charity.auth.User
import com.bertazoli.charity.enums.FundRaisingStatus
import org.joda.time.DateTime
import org.joda.time.DateTimeComparator
import org.joda.time.Days

class FundRaising {
    String description
    Date startDate
    Date endDate
    Boolean active
    FundRaisingStatus status
    String fileName

    BigDecimal totalDonated
    byte[] image

    static transients = ['totalDonated', 'image']

    static constraints = {
        startDate blank: false, validator: { value, obj ->
            DateTime d1 = new DateTime(obj.startDate)
            DateTime d2 = new DateTime(new Date())
            if (DateTimeComparator.getDateOnlyInstance().compare(d2,d1) > 0) {
                return "fundraising.fundRaisingStartDateCantBePast.error"
            }
        }

        endDate blank: false, validator: { value, obj ->
            DateTime d1 = new DateTime(obj.startDate)
            DateTime d2 = new DateTime(value)

            if (d1.isAfter(d2)) {
                return "fundraising.fundRaisingStartDateCantBeAfterEndDate.error"
            }

            if (Days.daysBetween(d1, d2).days > 60) {
                return "fundraising.fundRaisingCantRunForThatLong.error"
            }
        }

        fileName blank: true, nullable: true
    }

    static belongsTo = [user:User]
    static hasMany = [fundRaisingDonation:FundRaisingDonation]
}
