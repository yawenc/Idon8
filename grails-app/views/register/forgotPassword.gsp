<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I-don8.org</title>
</head>
<body>

<g:if test="${flash.error}">
    <div class="error" role="status">${flash.error}</div>
</g:if>

<g:form action="forgotPassword" autocomplete="off" name="forgotPasswordForm">
    <g:if test='${emailSent}'>
        <g:message code='spring.security.ui.forgotPassword.sent'/>
    </g:if>

    <g:else>
        <h4><g:message code='spring.security.ui.forgotPassword.description'/></h4>

        <div class="fieldcontain">
            <label for="username">
                <g:message code="spring.security.ui.forgotPassword.usernameOrEmail" default="Username or Email" />
                <span class="required-indicator">*</span>
            </label>
            <g:textField name="username" size="25" />
        </div>

        <g:submitButton name="formPasswordForm" value="${message(code: 'spring.security.ui.forgotPassword.submit')}" />
    </g:else>
</g:form>

<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
