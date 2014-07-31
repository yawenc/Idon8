<%@ page import="com.bertazoli.charity.Donation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'donation.myDonations', default: 'My Donations')}" />
	</head>
	<body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>

        <div id="show-donation" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:each in="${myDonations}" status="i" var="donation">
                <g:message code="donation.date.label" default="Donation date" />
                <g:formatDate date="${donation.donationDate}" formatName="donation.date.format" /><br/>
                <g:message code="donation.amount.label" default="Donation amount" />
                <g:formatNumber number="${donation.grossAmountValue}" formatName="default.currency.format" /><br/>
                <g:message code="charity.name.label" default="Charity name" />
                ${donation.charity.name}<br/>
                <g:if test="${donation.completed}">
                <g:message code="donation.ticket.numbers.label" default="Ticket numbers" /><br/>
                <g:each in="${donation.tickets}" var="ticket">
                    --> ${ticket.ticketNumber}<br/>
                </g:each>
                </g:if>
                <g:else>
                    <g:message code="donation.notCompleted.message" default="Donation not completed" /><br/>
                    <g:message code="donation.notCompleted.email1.message" default="Do you have questions about this donation?" />
                    <g:link action="emailSupport" id="${donation.id}">
                        <g:message code="donation.notCompleted.click.message" default="Click here to email us"/>
                    </g:link>
                    <g:message code="donation.notCompleted.email2.message" default=" and we'll check what happened" />
                </g:else>
                <br/>
            </g:each>
        </div>
    </body>
</html>
