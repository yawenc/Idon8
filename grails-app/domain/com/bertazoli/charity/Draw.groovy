package com.bertazoli.charity

import com.bertazoli.charity.enums.DrawStatus;

class Draw {
	Date startDate
	Date endDate
	DrawStatus status
	Boolean active
    Ticket winner

	static hasMany = [donations:Donation]

    static constraints = {
        winner nullable: true, blank: true
    }
}
