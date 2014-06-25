<%@ page import="com.bertazoli.charity.Donation" %>



<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'percentageToKeep', 'error')} required">
	<label for="percentageToKeep">
		<g:message code="donation.percentageToKeep.label" default="Percentage To Keep" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="percentageToKeep" from="${0..30}" class="range" required="" value="${fieldValue(bean: donationInstance, field: 'percentageToKeep')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'grossAmountValue', 'error')} required">
	<label for="grossAmountValue">
		<g:message code="donation.grossAmountValue.label" default="Gross Amount Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="grossAmountValue" value="${fieldValue(bean: donationInstance, field: 'grossAmountValue')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'charity', 'error')} required">
	<label for="charity">
		<g:message code="donation.charity.label" default="Charity" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="charity" name="charity.id" from="${com.bertazoli.charity.Charity.list()}" optionKey="id" required="" value="${donationInstance?.charity?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'completed', 'error')} ">
	<label for="completed">
		<g:message code="donation.completed.label" default="Completed" />
		
	</label>
	<g:checkBox name="completed" value="${donationInstance?.completed}" />

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'donationDate', 'error')} required">
	<label for="donationDate">
		<g:message code="donation.donationDate.label" default="Donation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="donationDate" precision="day"  value="${donationInstance?.donationDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'draw', 'error')} required">
	<label for="draw">
		<g:message code="donation.draw.label" default="Draw" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="draw" name="draw.id" from="${com.bertazoli.charity.Draw.list()}" optionKey="id" required="" value="${donationInstance?.draw?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'feeAmountCurrency', 'error')} required">
	<label for="feeAmountCurrency">
		<g:message code="donation.feeAmountCurrency.label" default="Fee Amount Currency" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="feeAmountCurrency" required="" value="${donationInstance?.feeAmountCurrency}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'feeAmountValue', 'error')} required">
	<label for="feeAmountValue">
		<g:message code="donation.feeAmountValue.label" default="Fee Amount Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="feeAmountValue" value="${fieldValue(bean: donationInstance, field: 'feeAmountValue')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'grossAmountCurrency', 'error')} required">
	<label for="grossAmountCurrency">
		<g:message code="donation.grossAmountCurrency.label" default="Gross Amount Currency" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="grossAmountCurrency" required="" value="${donationInstance?.grossAmountCurrency}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'paymentStatus', 'error')} required">
	<label for="paymentStatus">
		<g:message code="donation.paymentStatus.label" default="Payment Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="paymentStatus" required="" value="${donationInstance?.paymentStatus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'paymentType', 'error')} required">
	<label for="paymentType">
		<g:message code="donation.paymentType.label" default="Payment Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="paymentType" required="" value="${donationInstance?.paymentType}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'paypalToken', 'error')} required">
	<label for="paypalToken">
		<g:message code="donation.paypalToken.label" default="Paypal Token" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="paypalToken" required="" value="${donationInstance?.paypalToken}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'tickets', 'error')} ">
	<label for="tickets">
		<g:message code="donation.tickets.label" default="Tickets" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${donationInstance?.tickets?}" var="t">
    <li><g:link controller="ticket" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="ticket" action="create" params="['donation.id': donationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'ticket.label', default: 'Ticket')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'transaction', 'error')} required">
	<label for="transaction">
		<g:message code="donation.transaction.label" default="Transaction" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="transaction" required="" value="${donationInstance?.transaction}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: donationInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="donation.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${com.bertazoli.charity.auth.User.list()}" optionKey="id" required="" value="${donationInstance?.user?.id}" class="many-to-one"/>

</div>

