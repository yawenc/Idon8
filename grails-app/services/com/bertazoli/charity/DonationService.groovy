package com.bertazoli.charity

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType
import urn.ebay.api.PayPalAPI.GetTransactionDetailsReq
import urn.ebay.api.PayPalAPI.GetTransactionDetailsRequestType
import urn.ebay.api.PayPalAPI.GetTransactionDetailsResponseType
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType
import urn.ebay.api.PayPalAPI.TransactionSearchReq
import urn.ebay.api.PayPalAPI.TransactionSearchRequestType
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType
import urn.ebay.apis.CoreComponentTypes.BasicAmountType
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType
import grails.transaction.Transactional

import java.math.RoundingMode
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.sql.Timestamp
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.datatype.DatatypeFactory
import urn.ebay.apis.eBLBaseComponents.*

import com.bertazoli.charity.Donation

import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat

@Transactional
class DonationService {
	
	def grailsApplication
	def grailsLinkGenerator
    def mailService
    def messageSource

	def initializeDonation(params) {
		def donationInstance = params.donationInstance
		def user = params.user
		Map<String, String> configurationMap = getConfigurationMap();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap)
		SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
		SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();
		
		details.returnURL = grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.returnUrl
		details.cancelURL = grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.cancelUrl
		details.buyerEmail = user.email
		
		List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
		PaymentDetailsItemType item = new PaymentDetailsItemType();
		BasicAmountType amt = new BasicAmountType();
		amt.setCurrencyID(CurrencyCodeType.CAD);
		amt.setValue(String.valueOf(donationInstance.grossAmountValue));
		item.setQuantity(1);
		item.setName("Donation");
		item.setAmount(amt);
		item.setItemCategory(ItemCategoryType.DIGITAL);
		item.setDescription("Donation to I-don8.org");
		lineItems.add(item);
		List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
		PaymentDetailsType paydtl = new PaymentDetailsType();
		paydtl.setPaymentAction(PaymentActionCodeType.SALE);
		paydtl.setOrderDescription("Donation to I-don8.org");
		BasicAmountType itemsTotal = new BasicAmountType();
		itemsTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
		paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType.CAD, String.valueOf(donationInstance.grossAmountValue)));
		paydtl.setPaymentDetailsItem(lineItems);
		paydtl.setItemTotal(itemsTotal);
		payDetails.add(paydtl);
		details.setPaymentDetails(payDetails);
		details.setNoShipping("1");
		details.setBrandName("I-don8.org");
		setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
		SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
		expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
		
		try {
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
			if (setExpressCheckoutResponse != null) {
				donationInstance.paypalToken = setExpressCheckoutResponse.token;
				if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					return grailsApplication.config.paypal.url + setExpressCheckoutResponse.getToken();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.transactionError
	}
	
	def doExpressCheckout(params) {
		def token = params.token
		def payerID = params.PayerID
		Map<String, String> configurationMap = getConfigurationMap();
		PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);
		
		def donationInstance = Donation.findByPaypalToken(token);
		DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
		DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
		details.setToken(token);
		details.setPayerID(payerID);
		details.setPaymentAction(PaymentActionCodeType.SALE);
		
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
		orderTotal.setCurrencyID(CurrencyCodeType.CAD);
		paymentDetails.setOrderTotal(orderTotal);

		BasicAmountType itemTotal = new BasicAmountType();
		itemTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
		itemTotal.setCurrencyID(CurrencyCodeType.CAD);
		paymentDetails.setItemTotal(itemTotal);

		List<PaymentDetailsItemType> paymentItems = new ArrayList<PaymentDetailsItemType>();
		PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();
		paymentItem.setName("Donation to I-don8.org");
		paymentItem.setQuantity(1);
		BasicAmountType amount = new BasicAmountType();
		amount.setValue(String.valueOf(donationInstance.grossAmountValue));
		amount.setCurrencyID(CurrencyCodeType.CAD);
		paymentItem.setAmount(amount);
		paymentItems.add(paymentItem);
		paymentDetails.setPaymentDetailsItem(paymentItems);
		
		List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
		payDetailType.add(paymentDetails);
		details.setPaymentDetails(payDetailType);

		doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
		DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
		doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);
		DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = null;
		
		try{
			doCheckoutPaymentResponseType = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (doCheckoutPaymentResponseType != null) {
			try {
				if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					Map<Object, Object> map = new LinkedHashMap<Object, Object>();
					map.put("Ack", doCheckoutPaymentResponseType.getAck());
					Iterator<PaymentInfoType> iterator = doCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo().iterator();
					while (iterator.hasNext()) {
						// there should be only one payment for now
						PaymentInfoType result = (PaymentInfoType) iterator.next();
						try {
							XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(result.getPaymentDate());
							Calendar c2 = cal.toGregorianCalendar();
							donationInstance.setDonationDate(new Timestamp(c2.getTime().getTime()));
							donationInstance.setTransaction(result.getTransactionID());

                            if (result.feeAmount) {
                                donationInstance.setFeeAmountCurrency(result.feeAmount.getCurrencyID());
                                donationInstance.setFeeAmountValue(Double.parseDouble(result.feeAmount.getValue()));
                            }

                            if (result.grossAmount) {
                                donationInstance.setGrossAmountCurrency(result.getGrossAmount().getCurrencyID());
                                donationInstance.setGrossAmountValue(Double.parseDouble(result.getGrossAmount().getValue()));
                            }

							donationInstance.setPaymentStatusCode(result.getPaymentStatus());
							donationInstance.setPaymentCode(result.getPaymentType());

                            if (donationInstance.paymentStatusCode == PaymentStatusCodeType.COMPLETED) {
                                donationInstance.setCompleted(true);
                                createUserTickets(donationInstance);
                            }

                            donationInstance.clearErrors()
                            donationInstance.validate()
							
							donationInstance.save flush:true

                            if (donationInstance.tickets) {
                                emailTicketsToUser(donationInstance)
                            }
						} catch (DatatypeConfigurationException e) {
							e.printStackTrace();
						}
					}
					return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.successUrl
				} else {
					return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.errorUrl
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.errorUrl
	}

    def emailTicketsToUser(Donation donation) {
        String body = ""
        for (Ticket ticket:donation.tickets) {
            body += ticket.ticketNumber + " \n<br/>"
        }

        mailService.sendMail {
            to donation.user.email
            subject "Your ticket numbers"
            html body
        }
    }

    def createUserTickets(donationInstance) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		for (int x=0; x<donationInstance.getGrossAmountValue();) {
			x=x+5; // for every five dollars we generate 1 ticket number
			def ticketNumber = createMD5(x+donationInstance.id+donationInstance.charity.id+donationInstance.draw.id+donationInstance.user.id+donationInstance.transaction+new Timestamp(new Date().getTime()))
			Ticket ticket = new Ticket('donation':donationInstance, 'ticketNumber':ticketNumber);
			tickets.add(ticket);
		}
		
		donationInstance.tickets = tickets
	}
	
	private String createMD5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(string.getBytes());
		byte[] mdbytes = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < mdbytes.length; i++) {
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
	def getConfigurationMap() {
		Map<String,String> configurationMap = new HashMap<String,String>();
		configurationMap.put("acct1.UserName", grailsApplication.config.paypal.username);
		configurationMap.put("acct1.Password", grailsApplication.config.paypal.password);
		configurationMap.put("acct1.Signature", grailsApplication.config.paypal.signature);
		if (grailsApplication.config.paypal.sandbox) {
			configurationMap.put("mode", "sandbox");
		}
		return configurationMap;
	}

    def calculateWinner(Draw draw) {
        // get all donations for the current draw
        def donations = Donation.findAllByDrawAndCompletedAndPaymentStatusCode(draw, true, PaymentStatusCodeType.COMPLETED)

        // get all the tickets for the current list of donations
        List<Ticket> tickets = new ArrayList<Ticket>();
        def BigDecimal totalPrize = new BigDecimal(0).setScale(2, RoundingMode.DOWN);
        donations.each {
            tickets.addAll(it.tickets);
            totalPrize = totalPrize.add(it.grossAmountValue.multiply(0.93))
        }

        // get the total number of tickets generated
        def quantity = tickets.size();

        // select a random number between 0 and the total number of tickets generated
        int selectedNumber = (int) (Math.random() * quantity);

        // there always must be one and only one winner
        Ticket winner = tickets.get(selectedNumber)

        // update draw with the winner
        draw.winner = winner;
        draw.save(flush: true)

        BigDecimal userPrize = new BigDecimal(0)
        BigDecimal charityPrize = new BigDecimal(0)
        userPrize = totalPrize.multiply(new BigDecimal(winner.donation.percentageToKeep)).divide(new BigDecimal(100)).setScale(2, RoundingMode.DOWN)
        charityPrize = totalPrize.subtract(userPrize)
        // notify winner

        NumberFormat format = new DecimalFormat("\$0.00")

        String t1 = format.format(winner.donation.grossAmountValue.doubleValue())
        String t2 = format.format(totalPrize.doubleValue())
        String t3 = format.format(userPrize.doubleValue())
        String t4 = format.format(charityPrize.doubleValue())

        mailService.sendMail {
            def body = """Congratulations, your donation of $t1 to $winner.donation.charity.name \n<br />
            has been selected and you and the charity will be receiveing your award soon. \n<br />
            This is the winner ticket number: $winner.ticketNumber \n<br />
            Total prize: $t2 \n<br />
            Total you choose to keep to your self: $winner.donation.percentageToKeep% \n<br />
            Charity prize: $t4 \n<br />
            Your prize: $t3 \n<br />
            """
            to winner.donation.user.email
            subject "Your are the winner"
            html body
        }
    }

    def checkPendingPayments() {
        def donations = Donation.findAllByPaymentStatusCodeNotEqual(PaymentStatusCodeType.COMPLETED);

        for (Donation donation : donations) {
            if (!donation.transaction.equalsIgnoreCase("NOT COMPLETED")) {
                PaymentTransactionType transaction = getPaymentTransaction(donation.transaction);
                if (transaction && transaction.paymentInfo && transaction.paymentInfo.paymentStatus == PaymentStatusCodeType.COMPLETED) {
                    def result = transaction.paymentInfo
                    // payment has been completed, update database

                    if (result.feeAmount) {
                        donation.setFeeAmountCurrency(result.feeAmount.getCurrencyID());
                        donation.setFeeAmountValue(Double.parseDouble(result.feeAmount.getValue()));
                    }

                    if (result.grossAmount) {
                        donation.setGrossAmountCurrency(result.getGrossAmount().getCurrencyID());
                        donation.setGrossAmountValue(Double.parseDouble(result.getGrossAmount().getValue()));
                    }

                    donation.setPaymentStatusCode(result.getPaymentStatus());
                    donation.setPaymentCode(result.getPaymentType());

                    if (donation.paymentStatusCode == PaymentStatusCodeType.COMPLETED) {
                        donation.setCompleted(true);
                        createUserTickets(donation);
                    }

                    donation.clearErrors()
                    donation.validate()

                    donation.save flush:true

                    if (donation.tickets) {
                        emailTicketsToUser(donation)
                    }
                }
            }
        }
    }

    def PaymentTransactionType getPaymentTransaction(String transaction) {
        def getTransactionDetailsReq = new GetTransactionDetailsReq();
        def getTransactionDetailsReqType = new GetTransactionDetailsRequestType();

        getTransactionDetailsReqType.setTransactionID(transaction);
        getTransactionDetailsReq.setGetTransactionDetailsRequest(getTransactionDetailsReqType);

        PayPalAPIInterfaceServiceService service = null;
        try {
            service = new PayPalAPIInterfaceServiceService(getConfigurationMap());
        } catch (IOException e) {
            log.error("Failed to get configuration")
        }
        def getTransactionDetailsResponseType = null;
        try {
            getTransactionDetailsResponseType = service.getTransactionDetails(getTransactionDetailsReq);
        } catch (Exception e) {
            log.error("Something went bad: " + e.message)
        }

        if (getTransactionDetailsResponseType.ack.value.equalsIgnoreCase("SUCCESS")) {
            return getTransactionDetailsResponseType.paymentTransactionDetails
        } else {
            getTransactionDetailsResponseType.errors.each {
                log.error(it.longMessage);
            }
            return null;
        }
    }

    def initializeFundRaisingDonation(params) {
        def donationInstance = params.fundRaisingDonation
        def user = params.user
        Map<String, String> configurationMap = getConfigurationMap();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap)
        SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
        SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

        details.returnURL = grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.fundRaisingReturnUrl
        details.cancelURL = grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.cancelUrl
        details.buyerEmail = user.email

        List<PaymentDetailsItemType> lineItems = new ArrayList<PaymentDetailsItemType>();
        PaymentDetailsItemType item = new PaymentDetailsItemType();
        BasicAmountType amt = new BasicAmountType();
        amt.setCurrencyID(CurrencyCodeType.CAD);
        amt.setValue(String.valueOf(donationInstance.grossAmountValue));
        item.setQuantity(1);
        item.setName("Donation");
        item.setAmount(amt);
        item.setItemCategory(ItemCategoryType.DIGITAL);
        item.setDescription("Donation to I-don8.org");
        lineItems.add(item);
        List<PaymentDetailsType> payDetails = new ArrayList<PaymentDetailsType>();
        PaymentDetailsType paydtl = new PaymentDetailsType();
        paydtl.setPaymentAction(PaymentActionCodeType.SALE);
        paydtl.setOrderDescription("Donation to I-don8.org");
        BasicAmountType itemsTotal = new BasicAmountType();
        itemsTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
        paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType.CAD, String.valueOf(donationInstance.grossAmountValue)));
        paydtl.setPaymentDetailsItem(lineItems);
        paydtl.setItemTotal(itemsTotal);
        payDetails.add(paydtl);
        details.setPaymentDetails(payDetails);
        details.setNoShipping("1");
        details.setBrandName("I-don8.org");
        setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
        SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
        expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);

        try {
            SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);
            if (setExpressCheckoutResponse != null) {
                donationInstance.paypalToken = setExpressCheckoutResponse.token;
                if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
                    return grailsApplication.config.paypal.url + setExpressCheckoutResponse.getToken();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.transactionError
    }

    def doFundRaisingExpressCheckout(params) {
        def token = params.token
        def payerID = params.PayerID
        Map<String, String> configurationMap = getConfigurationMap();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap);

        def donationInstance = FundRaisingDonation.findByPaypalToken(token);
        DoExpressCheckoutPaymentRequestType doCheckoutPaymentRequestType = new DoExpressCheckoutPaymentRequestType();
        DoExpressCheckoutPaymentRequestDetailsType details = new DoExpressCheckoutPaymentRequestDetailsType();
        details.setToken(token);
        details.setPayerID(payerID);
        details.setPaymentAction(PaymentActionCodeType.SALE);

        PaymentDetailsType paymentDetails = new PaymentDetailsType();
        BasicAmountType orderTotal = new BasicAmountType();
        orderTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
        orderTotal.setCurrencyID(CurrencyCodeType.CAD);
        paymentDetails.setOrderTotal(orderTotal);

        BasicAmountType itemTotal = new BasicAmountType();
        itemTotal.setValue(String.valueOf(donationInstance.grossAmountValue));
        itemTotal.setCurrencyID(CurrencyCodeType.CAD);
        paymentDetails.setItemTotal(itemTotal);

        List<PaymentDetailsItemType> paymentItems = new ArrayList<PaymentDetailsItemType>();
        PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();
        paymentItem.setName("Donation to I-don8.org");
        paymentItem.setQuantity(1);
        BasicAmountType amount = new BasicAmountType();
        amount.setValue(String.valueOf(donationInstance.grossAmountValue));
        amount.setCurrencyID(CurrencyCodeType.CAD);
        paymentItem.setAmount(amount);
        paymentItems.add(paymentItem);
        paymentDetails.setPaymentDetailsItem(paymentItems);

        List<PaymentDetailsType> payDetailType = new ArrayList<PaymentDetailsType>();
        payDetailType.add(paymentDetails);
        details.setPaymentDetails(payDetailType);

        doCheckoutPaymentRequestType.setDoExpressCheckoutPaymentRequestDetails(details);
        DoExpressCheckoutPaymentReq doExpressCheckoutPaymentReq = new DoExpressCheckoutPaymentReq();
        doExpressCheckoutPaymentReq.setDoExpressCheckoutPaymentRequest(doCheckoutPaymentRequestType);
        DoExpressCheckoutPaymentResponseType doCheckoutPaymentResponseType = null;

        try{
            doCheckoutPaymentResponseType = service.doExpressCheckoutPayment(doExpressCheckoutPaymentReq);
        }catch(Exception e){
            e.printStackTrace();
        }

        if (doCheckoutPaymentResponseType != null) {
            try {
                if (doCheckoutPaymentResponseType.getAck().toString().equalsIgnoreCase("SUCCESS")) {
                    Map<Object, Object> map = new LinkedHashMap<Object, Object>();
                    map.put("Ack", doCheckoutPaymentResponseType.getAck());
                    Iterator<PaymentInfoType> iterator = doCheckoutPaymentResponseType.getDoExpressCheckoutPaymentResponseDetails().getPaymentInfo().iterator();
                    while (iterator.hasNext()) {
                        // there should be only one payment for now
                        PaymentInfoType result = (PaymentInfoType) iterator.next();
                        try {
                            XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(result.getPaymentDate());
                            Calendar c2 = cal.toGregorianCalendar();
                            donationInstance.setDonationDate(new Timestamp(c2.getTime().getTime()));
                            donationInstance.setTransaction(result.getTransactionID());

                            if (result.feeAmount) {
                                donationInstance.setFeeAmountCurrency(result.feeAmount.getCurrencyID());
                                donationInstance.setFeeAmountValue(Double.parseDouble(result.feeAmount.getValue()));
                            }

                            if (result.grossAmount) {
                                donationInstance.setGrossAmountCurrency(result.getGrossAmount().getCurrencyID());
                                donationInstance.setGrossAmountValue(Double.parseDouble(result.getGrossAmount().getValue()));
                            }

                            donationInstance.setPaymentStatusCode(result.getPaymentStatus());
                            donationInstance.setPaymentCode(result.getPaymentType());

                            if (donationInstance.paymentStatusCode == PaymentStatusCodeType.COMPLETED) {
                                donationInstance.setCompleted(true);
                            }

                            donationInstance.clearErrors()
                            donationInstance.validate()

                            donationInstance.save flush:true

                            // TODO email user with donation details
                        } catch (DatatypeConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                    return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.successUrl
                } else {
                    return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.errorUrl
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return grailsLinkGenerator.serverBaseURL + grailsApplication.config.paypal.errorUrl
    }

    def List<PaymentTransactionSearchResultType> transactionSearch(Date date) {
        String dateString = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(date)
        TransactionSearchReq searchReq = new TransactionSearchReq();
        TransactionSearchRequestType requestType = new TransactionSearchRequestType()

        requestType.setStartDate(dateString)

        searchReq.setTransactionSearchRequest(requestType)
        Map<String, String> configurationMap = getConfigurationMap();
        PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(configurationMap)

        try {
            TransactionSearchResponseType responseType = service.transactionSearch(searchReq);
            if (responseType != null && responseType.ack == AckCodeType.SUCCESS) {
                return responseType.paymentTransactions
            }
        } catch (Exception e) {

        }
        return null
    }
}
