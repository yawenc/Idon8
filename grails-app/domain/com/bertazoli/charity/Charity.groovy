package com.bertazoli.charity

import com.bertazoli.charity.enums.CharityCategory
import com.bertazoli.charity.enums.CharityDesignationCode
import com.bertazoli.charity.enums.CharitySanction
import com.bertazoli.charity.enums.CharityStatus

class Charity {

	String registrationNumber
	String name
	CharityStatus status
	CharitySanction sanction
	CharityDesignationCode designationCode
	Date effectiveDateOfStatus
	CharityCategory category
	
	static hasMany = [address:Address]
	
	static constraints = {
		status blank: true, nullable: true
		sanction blank: true, nullable: true
		designationCode blank: true, nullable: true
		category blank: false 
	}
}
