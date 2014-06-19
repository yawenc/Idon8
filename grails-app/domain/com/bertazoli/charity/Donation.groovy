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
	
	static belongsTo = [user:User]
	static hasMany = [tickets:Ticket]
}
