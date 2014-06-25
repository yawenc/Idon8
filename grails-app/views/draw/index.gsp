
<%@ page import="com.bertazoli.charity.Draw" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'draw.label', default: 'Draw')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-draw" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-draw" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="active" title="${message(code: 'draw.active.label', default: 'Active')}" />
					
						<g:sortableColumn property="endDate" title="${message(code: 'draw.endDate.label', default: 'End Date')}" />
					
						<g:sortableColumn property="startDate" title="${message(code: 'draw.startDate.label', default: 'Start Date')}" />
					
						<g:sortableColumn property="status" title="${message(code: 'draw.status.label', default: 'Status')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${drawInstanceList}" status="i" var="drawInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${drawInstance.id}">${fieldValue(bean: drawInstance, field: "active")}</g:link></td>
					
						<td><g:formatDate date="${drawInstance.endDate}" /></td>
					
						<td><g:formatDate date="${drawInstance.startDate}" /></td>
					
						<td>${fieldValue(bean: drawInstance, field: "status")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${drawInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
