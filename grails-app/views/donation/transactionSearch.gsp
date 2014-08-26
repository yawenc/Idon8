<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
	</head>
	<body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>

        <div id="show-donation" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:form action="transactionSearch">
                <g:jqDatePicker name="transactionStartDate" />
                <g:submitButton name="send" value="${message(code: 'transactionSearch.search.label')}" />
            </g:form>

            <table>
                <thead>
                <tr>
                    <th><g:message code="transaction.payerDisplayName.label" default="Payer name" /></th>
                    <th><g:message code="transaction.date.label" default="Date" /></th>
                    <th><g:message code="transaction.payer.label" default="Payer" /></th>
                    <th><g:message code="transaction.transactionid.label" default="Transaction ID" /></th>
                    <th><g:message code="transaction.status.label" default="Status" /></th>
                    <th><g:message code="transaction.grossAmount.label" default="Gross Amount" /></th>
                    <th><g:message code="transaction.feeAmount.label" default="Fee Amount" /></th>
                    <th><g:message code="transaction.netAmount.label" default="Net Amount" /></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${transactionList}" status="i" var="transaction">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${fieldValue(bean: transaction, field: "payerDisplayName")}</td>
                        <td><g:formatDate format="${message(code: "default.date.format")}" date="${java.util.Date.parse("yyyy-MM-dd'T'HH:mm:ss",transaction.timestamp)}" /></td>
                        <td>${fieldValue(bean: transaction, field: "payer")}</td>
                        <td>${fieldValue(bean: transaction, field: "transactionID")}</td>
                        <td>${fieldValue(bean: transaction, field: "status")}</td>
                        <td>${fieldValue(bean: transaction, field: "grossAmount.value")}</td>
                        <td>${fieldValue(bean: transaction, field: "feeAmount.value")}</td>
                        <td>${fieldValue(bean: transaction, field: "netAmount.value")}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>
