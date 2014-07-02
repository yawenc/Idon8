<%@ page import="com.bertazoli.charity.Donation" %>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'percentageToKeep', 'error')} required">
	<label for="percentageToKeep">
		<g:message code="donation.percentageToKeep.label" default="Percentage To Keep" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="percentageToKeep" from="${0..30}" class="range" required="" value="${fieldValue(bean: donationInstance, field: 'percentageToKeep')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'grossAmountValue', 'error')} required">
	<label for=grossAmountValue>
		<g:message code="donation.grossAmountValue.label" default="Donation Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="grossAmountValue" required="" value="${fieldValue(bean: donationInstance, field: 'grossAmountValue')}" />
</div>