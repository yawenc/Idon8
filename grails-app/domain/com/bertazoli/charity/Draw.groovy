package com.bertazoli.charity

import com.bertazoli.charity.enums.DrawStatus;

class Draw {
	Date startDate
	Date endDate
	DrawStatus status
	Boolean active

	static hasMany = [donations:Donation]
    static hasOne = [winner:Ticket]

    static constraints = {
        winner nullable: true, blank: true
    }
}
