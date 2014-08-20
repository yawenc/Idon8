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
            <h3 style="padding-bottom: 15px;">
                <g:message code="mydonations.drawStartDate.label" default="Draw start date: {0}" args="${draw.startDate}" />
                <g:message code="mydonations.drawEndDate.label" default="Draw end date: {0}" args="${draw.endDate}" />
            </h3>
            <g:link action="myDonations" controller="donation" params="[currentDraw:draw.id, act:'previous']" >Previous</g:link>
            <g:link action="myDonations" controller="donation" params="[currentDraw:draw.id, act:'next']" >Next</g:link>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:each in="${myDonations}" status="i" var="donation">
                <div style="border-bottom: thin dashed #CCCCCC">
                    <div style="padding-bottom: 5px;">
                        <g:message code="donation.date.label" default="Donation date" />:
                        <g:formatDate date="${donation.donationDate}" formatName="donation.date.format" />
                    </div>

                    <div style="padding-bottom: 5px;">
                        <g:message code="donation.amount.label" default="Donation amount" />:
                        <g:formatNumber number="${donation.grossAmountValue}" formatName="default.currency.format" />
                    </div>

                    <div style="padding-bottom: 5px;">
                        <g:message code="charity.name.label" default="Charity name" />:
                        ${donation.charity.name}
                    </div>

                    <g:if test="${donation.completed}">
                    <div style="padding-bottom: 5px;">
                        <g:message code="donation.ticket.numbers.label" default="Ticket numbers" /><br/>
                    </div>
                    <g:each in="${donation.tickets}" var="ticket">
                        <h5 style="font-family: monospace;">${ticket.ticketNumber.substring(0,10)}</h5>
                    </g:each>
                    </g:if>
                    <g:else>
                        <div style="padding-bottom: 5px;">
                            <g:message code="donation.notCompleted.message" default="Donation not completed" />
                        </div>
                        <div style="padding-bottom: 5px;">
                            <g:message code="donation.notCompleted.email1.message" default="Do you have questions about this donation?" />
                            <g:link action="emailSupport" id="${donation.id}">
                                <g:message code="donation.notCompleted.click.message" default="Click here to email us"/>
                            </g:link>
                            <g:message code="donation.notCompleted.email2.message" default=" and we'll check what happened" />
                        </div>
                    </g:else>
                </div>
            </g:each>
        </div>
    </body>
</html>
