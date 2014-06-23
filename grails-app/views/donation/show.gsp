
<%@ page import="com.bertazoli.charity.Donation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'donation.label', default: 'Donation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-donation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-donation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list donation">
			
				<g:if test="${donationInstance?.completed}">
				<li class="fieldcontain">
					<span id="completed-label" class="property-label"><g:message code="donation.completed.label" default="Completed" /></span>
					
						<span class="property-value" aria-labelledby="completed-label"><g:formatBoolean boolean="${donationInstance?.completed}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.donationDate}">
				<li class="fieldcontain">
					<span id="donationDate-label" class="property-label"><g:message code="donation.donationDate.label" default="Donation Date" /></span>
					
						<span class="property-value" aria-labelledby="donationDate-label"><g:formatDate date="${donationInstance?.donationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.feeAmountCurrency}">
				<li class="fieldcontain">
					<span id="feeAmountCurrency-label" class="property-label"><g:message code="donation.feeAmountCurrency.label" default="Fee Amount Currency" /></span>
					
						<span class="property-value" aria-labelledby="feeAmountCurrency-label"><g:fieldValue bean="${donationInstance}" field="feeAmountCurrency"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.feeAmountValue}">
				<li class="fieldcontain">
					<span id="feeAmountValue-label" class="property-label"><g:message code="donation.feeAmountValue.label" default="Fee Amount Value" /></span>
					
						<span class="property-value" aria-labelledby="feeAmountValue-label"><g:fieldValue bean="${donationInstance}" field="feeAmountValue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.grossAmountCurrency}">
				<li class="fieldcontain">
					<span id="grossAmountCurrency-label" class="property-label"><g:message code="donation.grossAmountCurrency.label" default="Gross Amount Currency" /></span>
					
						<span class="property-value" aria-labelledby="grossAmountCurrency-label"><g:fieldValue bean="${donationInstance}" field="grossAmountCurrency"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.grossAmountValue}">
				<li class="fieldcontain">
					<span id="grossAmountValue-label" class="property-label"><g:message code="donation.grossAmountValue.label" default="Gross Amount Value" /></span>
					
						<span class="property-value" aria-labelledby="grossAmountValue-label"><g:fieldValue bean="${donationInstance}" field="grossAmountValue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.paymentStatus}">
				<li class="fieldcontain">
					<span id="paymentStatus-label" class="property-label"><g:message code="donation.paymentStatus.label" default="Payment Status" /></span>
					
						<span class="property-value" aria-labelledby="paymentStatus-label"><g:fieldValue bean="${donationInstance}" field="paymentStatus"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.paymentType}">
				<li class="fieldcontain">
					<span id="paymentType-label" class="property-label"><g:message code="donation.paymentType.label" default="Payment Type" /></span>
					
						<span class="property-value" aria-labelledby="paymentType-label"><g:fieldValue bean="${donationInstance}" field="paymentType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.paypalToken}">
				<li class="fieldcontain">
					<span id="paypalToken-label" class="property-label"><g:message code="donation.paypalToken.label" default="Paypal Token" /></span>
					
						<span class="property-value" aria-labelledby="paypalToken-label"><g:fieldValue bean="${donationInstance}" field="paypalToken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.percentageToKeep}">
				<li class="fieldcontain">
					<span id="percentageToKeep-label" class="property-label"><g:message code="donation.percentageToKeep.label" default="Percentage To Keep" /></span>
					
						<span class="property-value" aria-labelledby="percentageToKeep-label"><g:fieldValue bean="${donationInstance}" field="percentageToKeep"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.tickets}">
				<li class="fieldcontain">
					<span id="tickets-label" class="property-label"><g:message code="donation.tickets.label" default="Tickets" /></span>
					
						<g:each in="${donationInstance.tickets}" var="t">
						<span class="property-value" aria-labelledby="tickets-label"><g:link controller="ticket" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.transaction}">
				<li class="fieldcontain">
					<span id="transaction-label" class="property-label"><g:message code="donation.transaction.label" default="Transaction" /></span>
					
						<span class="property-value" aria-labelledby="transaction-label"><g:fieldValue bean="${donationInstance}" field="transaction"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${donationInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="donation.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${donationInstance?.user?.id}">${donationInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:donationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${donationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
