<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I-don8.org</title>
</head>
<body>
    <div><g:link controller="donation" action="myDonations"><g:message code="mySpace.myDonations.label" default="My donations"/></g:link></div>
    <div><g:link controller="fundRaising"><g:message code="mySpace.manageMyFundRaising.label" default="Manage my fund raising" /></g:link></div>
    <sec:ifAllGranted roles="ROLE_ADMIN">
        <div><g:link controller="donation" action="transactionSearch"><g:message code="mySpace.transactionSearch.label" default="Transaction Search" /></g:link></div>
    </sec:ifAllGranted>
</body>
</html>
