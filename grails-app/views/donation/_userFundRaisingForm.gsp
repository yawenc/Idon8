<%@ page import="com.bertazoli.charity.Donation" %>

<div class="fieldcontain ${hasErrors(bean: fundRaisingDonation, field: 'grossAmountValue', 'error')} required">
	<label for="grossAmountValue">
		<g:message code="donation.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="grossAmountValue" type="text" value="${fieldValue(bean: fundRaisingDonation, field: 'grossAmountValue')}" />
</div>