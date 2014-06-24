<%@ page import="com.bertazoli.charity.Donation" %>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'grossAmountValue', 'error')} required">
	<label for="grossAmountValue">
		<g:message code="donation.grossAmountValue.label" default="Gross Amount Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="grossAmountValue" value="${fieldValue(bean: donationInstance, field: 'grossAmountValue')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'percentageToKeep', 'error')} required">
	<label for="percentageToKeep">
		<g:message code="donation.percentageToKeep.label" default="Percentage To Keep" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="percentageToKeep" type="number" value="${donationInstance.percentageToKeep}" required=""/>

</div>