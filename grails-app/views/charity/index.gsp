
<%@ page import="com.bertazoli.charity.Charity" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'charity.label', default: 'Charity')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-charity" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
        <g:form action="activateCharities">
            <g:select id="state" name="state.id" from="${com.bertazoli.charity.State.list()}" optionKey="id" required=""/>
            <g:submitButton name="activate" class="activate" value="${message(code: 'default.button.activate.label', default: 'Activate')}" />
        </g:form>
		<div id="list-charity" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="name" title="${message(code: 'charity.name.label', default: 'Name')}" />
						<g:sortableColumn property="registrationNumber" title="${message(code: 'charity.registrationNumber.label', default: 'Registration Number')}" />
						<g:sortableColumn property="status" title="${message(code: 'charity.status.label', default: 'Status')}" />
						<g:sortableColumn property="sanction" title="${message(code: 'charity.sanction.label', default: 'Sanction')}" />
						<g:sortableColumn property="designationCode" title="${message(code: 'charity.designationCode.label', default: 'Designation Code')}" />
						<g:sortableColumn property="category" title="${message(code: 'charity.category.label', default: 'Category')}" />
						<g:sortableColumn property="effectiveDateOfStatus" title="${message(code: 'charity.effectiveDateOfStatus.label', default: 'Effective Date Of Status')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${charityInstanceList}" status="i" var="charityInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link action="show" id="${charityInstance.id}">${fieldValue(bean: charityInstance, field: "name")}</g:link></td>
						<td>${fieldValue(bean: charityInstance, field: "registrationNumber")}</td>
						<td>${fieldValue(bean: charityInstance, field: "status")}</td>
						<td>${fieldValue(bean: charityInstance, field: "sanction")}</td>
						<td>${fieldValue(bean: charityInstance, field: "designationCode")}</td>
						<td>${fieldValue(bean: charityInstance, field: "category")}</td>
						<td><g:formatDate date="${charityInstance?.effectiveDateOfStatus}" type="date" /></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${charityInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
