<%@ page import="com.bertazoli.charity.Donation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'donation.myDonations', default: 'Email sent')}" />
	</head>
	<body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>

        <div id="show-donation" class="content scaffold-show" role="main">
            <h1><g:message code="default.email.sent.label" default="Your email has been sent with success." /></h1>
        </div>
    </body>
</html>
