
<%@ page import="com.bertazoli.charity.FundRaising" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fundRaising.label', default: 'FundRaising')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fundRaising" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fundRaising" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fundRaising">
			
				<g:if test="${fundRaisingInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="fundRaising.active.label" default="Active" /></span>
                    <span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${fundRaisingInstance?.active}" /></span>
				</li>
				</g:if>
			
				<g:if test="${fundRaisingInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="fundRaising.description.label" default="Description" /></span>
                    <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${fundRaisingInstance}" field="description"/></span>
				</li>
				</g:if>

                <g:if test="${fundRaisingInstance?.startDate}">
                    <li class="fieldcontain">
                        <span id="startDate-label" class="property-label"><g:message code="fundRaising.startDate.label" default="Start Date" /></span>
                        <span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${fundRaisingInstance?.startDate}" /></span>
                    </li>
                </g:if>

                <g:if test="${fundRaisingInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="fundRaising.endDate.label" default="End Date" /></span>
                    <span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${fundRaisingInstance?.endDate}" /></span>
				</li>
				</g:if>

                <g:if test="${fundRaisingInstance?.fileName}">
                <li class="fieldcontain">
                    <span id="image-label" class="property-label"><g:message code="fundRaising.image.label" default="Image" /></span>
                    <span class="property-value">
                        <img class="image" src="${createLink(controller: 'fundRaising', action: 'getImage', params: ['image': fundRaisingInstance.fileName])}" />
                    </span>
                </li>
                </g:if>
			</ol>
            <!--
			<g:form url="[resource:fundRaisingInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${fundRaisingInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			-->
		</div>
	</body>
</html>
