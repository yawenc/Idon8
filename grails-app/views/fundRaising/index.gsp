
<%@ page import="com.bertazoli.charity.FundRaising" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fundRaising.label', default: 'FundRaising')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fundRaising" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fundRaising" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
                        <g:sortableColumn property="active" title="${message(code: 'fundRaising.active.label', default: 'Active')}" />
						<g:sortableColumn property="description" title="${message(code: 'fundRaising.description.label', default: 'Description')}" />
						<g:sortableColumn property="startDate" title="${message(code: 'fundRaising.startDate.label', default: 'Start Date')}" />
                        <g:sortableColumn property="endDate" title="${message(code: 'fundRaising.endDate.label', default: 'End Date')}" />
                        <th><g:message code="index.donation.totalDonated" default="Total donated" /></th>
                        <th><g:message code="fundraising.status.label" default="Status" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${fundRaisingInstanceList}" status="i" var="fundRaisingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${fieldValue(bean: fundRaisingInstance, field: "active")}</td>
						<td><g:link action="show" id="${fundRaisingInstance.id}">${fieldValue(bean: fundRaisingInstance, field: "description")}</g:link></td>
                        <td><g:formatDate date="${fundRaisingInstance.startDate}" /></td>
						<td><g:formatDate date="${fundRaisingInstance.endDate}" /></td>
                        <td><g:formatNumber number="${fundRaisingInstance.totalDonated}" formatName="default.currency.format" /></td>
                        <td>
                            <g:if test="${fundRaisingInstance.status == com.bertazoli.charity.enums.FundRaisingStatus.CURRENT}">
                                <a id="shareFundRaising${fundRaisingInstance.id}" href="${createLink(controller: 'donation', action: 'userFundRaising', id: "${fundRaisingInstance.id}")}">Share</a>
                                <script>
                                    $("#shareFundRaising${fundRaisingInstance.id}").click(function () {
                                        console.log("Share on facebook and/or email --> " + "${createLink(controller: 'donation', action: 'userFundRaising', id: "${fundRaisingInstance.id}", absolute: true)}")
                                    });
                                </script>
                            </g:if>
                            <g:else>
                                <g:message code="redeem" default="Redeem" />
                            </g:else>
                        </td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fundRaisingInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
