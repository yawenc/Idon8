
<%@ page import="com.bertazoli.charity.Charity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'charity.label', default: 'Charity')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-charity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-charity" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list charity">
			
				<g:if test="${charityInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="charity.address.label" default="Address" /></span>
					
						<g:each in="${charityInstance.address}" var="a">
						<span class="property-value" aria-labelledby="address-label"><g:link controller="address" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${charityInstance?.effectiveDateOfStatus}">
				<li class="fieldcontain">
					<span id="effectiveDateOfStatus-label" class="property-label"><g:message code="charity.effectiveDateOfStatus.label" default="Effective Date Of Status" /></span>
					
						<span class="property-value" aria-labelledby="effectiveDateOfStatus-label"><g:formatDate date="${charityInstance?.effectiveDateOfStatus}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${charityInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="charity.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${charityInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${charityInstance?.registrationNumber}">
				<li class="fieldcontain">
					<span id="registrationNumber-label" class="property-label"><g:message code="charity.registrationNumber.label" default="Registration Number" /></span>
					
						<span class="property-value" aria-labelledby="registrationNumber-label"><g:fieldValue bean="${charityInstance}" field="registrationNumber"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:charityInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${charityInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
