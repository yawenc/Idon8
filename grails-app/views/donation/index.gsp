
<%@ page import="com.bertazoli.charity.Donation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'donation.label', default: 'Donation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-donation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-donation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="completed" title="${message(code: 'donation.completed.label', default: 'Completed')}" />
						<g:sortableColumn property="donationDate" title="${message(code: 'donation.donationDate.label', default: 'Donation Date')}" />
						<g:sortableColumn property="feeAmountCurrency" title="${message(code: 'donation.feeAmountCurrency.label', default: 'Fee Amount Currency')}" />
						<g:sortableColumn property="feeAmountValue" title="${message(code: 'donation.feeAmountValue.label', default: 'Fee Amount Value')}" />
						<g:sortableColumn property="grossAmountCurrency" title="${message(code: 'donation.grossAmountCurrency.label', default: 'Gross Amount Currency')}" />
						<g:sortableColumn property="grossAmountValue" title="${message(code: 'donation.grossAmountValue.label', default: 'Gross Amount Value')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${donationInstanceList}" status="i" var="donationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${donationInstance.id}">${fieldValue(bean: donationInstance, field: "completed")}</g:link></td>
						<td><g:formatDate date="${donationInstance.donationDate}" /></td>
						<td>${fieldValue(bean: donationInstance, field: "feeAmountCurrency")}</td>
						<td>${fieldValue(bean: donationInstance, field: "feeAmountValue")}</td>
						<td>${fieldValue(bean: donationInstance, field: "grossAmountCurrency")}</td>
						<td>${fieldValue(bean: donationInstance, field: "grossAmountValue")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${donationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
