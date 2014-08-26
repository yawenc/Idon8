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
				<!-- <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>-->
			</ul>
		</div>
		<div id="list-donation" class="content scaffold-list" role="main">
			<h1><g:message code="donation.make.label"/></h1>
            <h2>${fundRaising.user?.userDetails?.firstName} ${fundRaising.user?.userDetails?.lastName}</h2>
            <div>
                <div class="inline-block">
                    <g:if test="${fundRaising.fileName}">
                        <span class="property-value">
                            <img class="image" src="${createLink(controller: 'fundRaising', action: 'getImage', params: ['image': fundRaising.fileName])}" />
                        </span>
                    </g:if>
                </div>
                <div class="inline-block vertical-align-top">
                    <span>${fundRaising?.description}</span>
                </div>
            </div>

			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${fundRaisingDonation}">
			<ul class="errors" role="alert">
				<g:eachError bean="${fundRaisingDonation}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			
			<g:form action="saveUserFundRaising" >
                <g:hiddenField name="fundRaising" value="${fundRaising?.id}" />
				<fieldset class="form">
					<g:render template="userFundRaisingForm"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.donate.label', default: 'Donate')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
