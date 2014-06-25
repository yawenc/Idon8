package com.bertazoli.charity

import com.bertazoli.charity.auth.User

class Donation {
	Integer percentageToKeep
	Date donationDate
	String transaction
	String feeAmountCurrency
	BigDecimal feeAmountValue
	String grossAmountCurrency
	BigDecimal grossAmountValue
	String paymentStatus
	String paymentType
	Boolean completed
	String paypalToken
	
	static constraints = {
		percentageToKeep blank: false, range: 0..30
		grossAmountValue blank: false, validator: {
			return ((int)it %5) == 0
		}
	}
	
	static belongsTo = [user:User, draw:Draw, charity:Charity]
	static hasMany = [tickets:Ticket]
}
