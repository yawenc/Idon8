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
                <g:formatNumber number="${donation.grossAmountValue}" type="number" formatName="default.currency.format" /><br/>
                <g:message code="charity.name.label" default="Charity name" />
                ${donation.charity.name}<br/>
                <g:message code="donation.ticket.numbers.label" default="Ticket numbers" /><br/>
                <g:each in="${donation.tickets}" var="ticket">
                    --> ${ticket.ticketNumber}<br/>
                </g:each>
                <br/>
            </g:each>
        </div>
    </body>
</html>