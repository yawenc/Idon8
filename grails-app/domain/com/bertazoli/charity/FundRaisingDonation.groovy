package com.bertazoli.charity

import com.bertazoli.charity.auth.User
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType
import urn.ebay.apis.eBLBaseComponents.PaymentCodeType
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType

class FundRaisingDonation {
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
        grossAmountValue blank: false, validator: {
            if (it < 15) {
                return 'donation.grossAmountValue.equalOrGreaterThanFifteen.error'
            }
        }
    }

    static belongsTo = [fundRaising:FundRaising, user:User]
}
