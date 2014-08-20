package com.bertazoli.charity

import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType;

import com.bertazoli.charity.auth.User

class Donation {
	Integer percentageToKeep
	Date donationDate
	String transaction
	CurrencyCodeType feeAmountCurrency
	BigDecimal feeAmountValue
	CurrencyCodeType grossAmountCurrency
	BigDecimal grossAmountValue
	Boolean completed
	String paypalToken
	PaymentCodeType paymentCode
	PaymentStatusCodeType paymentStatusCode
	
	static constraints = {
		percentageToKeep blank: false, range: 0..30
		grossAmountValue blank: false, validator: {
			if (it < 15) {
                return 'donation.grossAmountValue.equalOrGreaterThanFifteen.error'
            } else if (it.remainder(new BigDecimal(5)) != 0) {
                return 'donation.grossAmountValue.multipleOfFive.error'
            }
		}
	}

	static belongsTo = [user:User, draw:Draw, charity:Charity]
	static hasMany = [tickets:Ticket]
}
